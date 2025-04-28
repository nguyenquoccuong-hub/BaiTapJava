import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient extends JFrame {
    private JTextArea messageArea;
    private JTextField messageField;
    private JButton sendButton;
    private JTextField ipField;
    private JTextField portField;
    private JButton connectButton;
    private String clientName;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ChatClient() {
        // Yêu cầu nhập tên client
        clientName = JOptionPane.showInputDialog(this, "Nhập tên của bạn:", "Tên Client", JOptionPane.PLAIN_MESSAGE);
        if (clientName == null || clientName.trim().isEmpty()) {
            clientName = "Khách";
        }
        clientName = clientName.trim();

        setTitle("Chat Client - " + clientName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
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

        setVisible(true);
    }

    private void connectToServer() {
        try {
            String ip = ipField.getText();
            int port = Integer.parseInt(portField.getText());
            socket = new Socket(ip, port);
            SwingUtilities.invokeLater(() -> messageArea.append("Đã kết nối đến server!\n"));

            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

            // Gửi tên client đến server ngay sau khi kết nối
            out.println(clientName);

            new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        String finalMessage = message;
                        SwingUtilities.invokeLater(() -> messageArea.append(finalMessage + "\n"));
                    }
                    SwingUtilities.invokeLater(() -> messageArea.append("Mất kết nối với server!\n"));
                } catch (IOException e) {
                    SwingUtilities.invokeLater(() -> messageArea.append("Lỗi khi nhận tin nhắn: " + e.getMessage() + "\n"));
                }
            }).start();
        } catch (IOException e) {
            SwingUtilities.invokeLater(() -> messageArea.append("Lỗi kết nối: " + e.getMessage() + "\n"));
        } catch (NumberFormatException e) {
            SwingUtilities.invokeLater(() -> messageArea.append("Port không hợp lệ!\n"));
        }
    }

    private void sendMessage() {
        if (out == null) {
            SwingUtilities.invokeLater(() -> messageArea.append("Lỗi: Chưa kết nối đến server!\n"));
            return;
        }
        String message = messageField.getText();
        if (message.isEmpty()) {
            SwingUtilities.invokeLater(() -> messageArea.append("Vui lòng nhập tin nhắn!\n"));
            return;
        }

        // Gửi tin nhắn với định dạng <clientName>:<message>
        String fullMessage = clientName + ": " + message;
        out.println(fullMessage);
        SwingUtilities.invokeLater(() -> {
            messageArea.append(fullMessage + "\n");
            messageField.setText("");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatClient());
    }
}