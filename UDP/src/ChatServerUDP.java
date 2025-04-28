import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ChatServerUDP extends JFrame {
    private static final int PORT = 12345;
    private DatagramSocket serverSocket;
    private JTextArea logArea;
    private Set<ClientInfo> clients = Collections.synchronizedSet(new HashSet<>());

    public ChatServerUDP() {
        setTitle("UDP Chat Server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);


        startServer();
    }

    private void startServer() {
        try {
            serverSocket = new DatagramSocket(PORT);
            logArea.append("Server đang chạy trên port " + PORT + "...\n");

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(receivePacket);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                ClientInfo clientInfo = new ClientInfo(clientAddress, clientPort);

                // Thêm client vào set trước
                clients.add(clientInfo);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
                logArea.append("Tin nhắn từ " + clientAddress + ":" + clientPort + " - " + message + "\n");

                // Sau đó mới broadcast
                broadcast(message, clientInfo);
            }

        } catch (Exception e) {
            logArea.append("Lỗi server: " + e.getMessage() + "\n");
        }
    }

    private void broadcast(String message, ClientInfo sender) {
        byte[] data = message.getBytes();
        synchronized (clients) {
            for (ClientInfo client : clients) {
                try {
                    DatagramPacket sendPacket = new DatagramPacket(data, data.length, client.address, client.port);
                    serverSocket.send(sendPacket);
                } catch (Exception e) {
                    logArea.append("Lỗi gửi tin: " + e.getMessage() + "\n");
                }
            }
        }
    }

    private static class ClientInfo {
        InetAddress address;
        int port;

        ClientInfo(InetAddress address, int port) {
            this.address = address;
            this.port = port;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ClientInfo)) return false;
            ClientInfo other = (ClientInfo) o;
            return port == other.port && address.equals(other.address);
        }

        @Override
        public int hashCode() {
            return address.hashCode() + port;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatServerUDP::new);
    }
}
