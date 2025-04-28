import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer extends JFrame {
    private static final int PORT = 12345;
    private static Set<PrintWriter> clients = Collections.synchronizedSet(new HashSet<>());
    private static Map<PrintWriter, String> clientNames = Collections.synchronizedMap(new HashMap<>());
    private JTextArea logArea;

    public ChatServer() {
        setTitle("Chat Server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);

        startServer();
    }

    private void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            logArea.append("Server đang chạy trên port " + PORT + "...\n");

            while (true) {
                Socket socket = serverSocket.accept();
                String clientAddress = socket.getInetAddress().getHostAddress();
                logArea.append("Client đã kết nối: " + clientAddress + "\n");

                Thread clientThread = new Thread(new ClientHandler(socket, clientAddress));
                clientThread.start();
            }
        } catch (IOException e) {
            logArea.append("Lỗi server: " + e.getMessage() + "\n");
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String clientAddress;
        private String clientName;

        public ClientHandler(Socket socket, String clientAddress) {
            this.socket = socket;
            this.clientAddress = clientAddress;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

                // Đọc tên client từ dòng đầu tiên
                clientName = in.readLine();
                if (clientName == null || clientName.trim().isEmpty()) {
                    clientName = "Khách";
                }
                clientName = clientName.trim();

                synchronized (clients) {
                    clients.add(out);
                    clientNames.put(out, clientName);
                    logArea.append("Client " + clientName + " đã tham gia. Số client: " + clients.size() + "\n");
                    // Gửi thông báo đến tất cả client (bao gồm client vừa kết nối)
                    broadcast(clientName + " đã tham gia cuộc trò chuyện", null);
                }

                String message;
                while ((message = in.readLine()) != null) {
                    logArea.append("Tin nhắn từ " + clientName + ": " + message + "\n");
                    broadcast(message, out); // Truyền PrintWriter của client gửi để loại trừ
                }
            } catch (IOException e) {
                logArea.append("Client " + clientName + " ngắt kết nối: " + e.getMessage() + "\n");
            } finally {
                synchronized (clients) {
                    clients.remove(out);
                    clientNames.remove(out);
                    logArea.append("Client " + clientName + " đã rời. Số client: " + clients.size() + "\n");
                    // Gửi thông báo đến các client còn lại
                    broadcast(clientName + " đã rời cuộc trò chuyện", null);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    logArea.append("Lỗi: " + e.getMessage() + "\n");
                }
            }
        }

        private void broadcast(String message, PrintWriter sender) {
            synchronized (clients) {
                for (PrintWriter client : clients) {
                    if (sender == null || client != sender) { // Không gửi lại cho client gửi nếu sender không null
                        client.println(message);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatServer());
    }
}