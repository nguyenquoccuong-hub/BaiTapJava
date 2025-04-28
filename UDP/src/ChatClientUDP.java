import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class ChatClientUDP extends JFrame {
    private JTextArea messageArea;
    private JTextField messageField;
    private JButton sendButton;
    private JTextField ipField;
    private JTextField portField;
    private JButton connectButton;
    private String clientName;

    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;

    public ChatClientUDP() {
        // Yêu cầu nhập tên client
        clientName = JOptionPane.showInputDialog(this, "Nhập tên của bạn:", "Tên Client", JOptionPane.PLAIN_MESSAGE);
        if (clientName == null || clientName.trim().isEmpty()) {
            clientName = "Khách";
        }
        clientName = clientName.trim();

        setTitle("UDP Chat Client - " + clientName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel connectPanel = new JPanel(new FlowLayout());
        ipField = new JTextField("127.0.0.1", 15);
        portField = new JTextField("12345", 5);
        connectButton = new JButton("Kết nối");
        connectPanel.add(new JLabel("IP:"));
        connectPanel.add(ipField);
        connectPanel.add(new JLabel("Port:"));
        connectPanel.add(portField);
        connectPanel.add(connectButton);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);

        JPanel inputPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        sendButton = new JButton("Gửi");
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(connectPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        connectButton.addActionListener(e -> connectToServer());
        sendButton.addActionListener(e -> sendMessage());
        messageField.addActionListener(e -> sendMessage());

        sendButton.setEnabled(false);

        setVisible(true);
    }

    private void connectToServer() {
        try {
            serverAddress = InetAddress.getByName(ipField.getText());
            serverPort = Integer.parseInt(portField.getText());
            socket = new DatagramSocket();
            messageArea.append("Đã kết nối đến server!\n");
            sendButton.setEnabled(true);

            // GỬI GÓI "JOIN" NGAY SAU KHI KẾT NỐI
            sendJoinMessage();

            new Thread(this::receiveMessages).start();
        } catch (Exception e) {
            messageArea.append("Lỗi kết nối: " + e.getMessage() + "\n");
        }
    }

    private void sendJoinMessage() {
        try {
            String joinMessage = clientName + " đã tham gia phòng chat.";
            byte[] data = joinMessage.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, serverPort);
            socket.send(packet);
        } catch (Exception e) {
            messageArea.append("Lỗi gửi gói JOIN: " + e.getMessage() + "\n");
        }
    }


    private void sendMessage() {
        if (socket == null || serverAddress == null) {
            messageArea.append("Chưa kết nối server!\n");
            return;
        }

        String text = messageField.getText().trim();
        if (text.isEmpty()) {
            return;
        }

        String fullMessage = clientName + ": " + text;

        try {
            byte[] data = fullMessage.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, serverPort);
            socket.send(packet);

            messageField.setText(""); // Clear ô nhập, KHÔNG append vào messageArea nữa
        } catch (Exception e) {
            messageArea.append("Lỗi gửi tin nhắn: " + e.getMessage() + "\n");
        }
    }


    private void receiveMessages() {
        try {
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String received = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
                SwingUtilities.invokeLater(() -> messageArea.append(received + "\n"));
            }
        } catch (Exception e) {
            messageArea.append("Ngắt kết nối server!\n");
        }
    }

    public void sendLeaveMessage() {
        try {
            if (socket != null && serverAddress != null) {
                String leaveMessage = clientName + " đã rời phòng chat.";
                byte[] data = leaveMessage.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, serverPort);
                socket.send(packet);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi gửi gói rời phòng: " + e.getMessage());
        }
    }


}
