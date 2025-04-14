package gui;

import algorithm.AESAlgorithm;
import algorithm.Encryptable;
import algorithm.RSAAlgorithm;
import multithread.EncryptDecryptTask;
import security.HashUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class MainGUI extends JFrame {

    private JTextArea inputArea, outputArea;
    private JComboBox<String> algorithmBox;
    private JTextField passwordField;
    private JTextArea hashResultArea;

    // Chúng ta sẽ tạo keyPair cho RSA ở đây
    private KeyPair rsaKeyPair;

    public MainGUI() {
        setTitle("Ứng dụng mã hoá đơn giản");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo key pair RSA
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            rsaKeyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Mã hoá / Giải mã", createEncryptPanel());
        tabs.addTab("Mật khẩu (SHA-256)", createPasswordTab());

        add(tabs);
    }

    private JPanel createEncryptPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        inputArea = new JTextArea(4, 40);
        outputArea = new JTextArea(4, 40);
        outputArea.setEditable(false);

        algorithmBox = new JComboBox<>(new String[]{"AES", "RSA"});

        JButton encryptBtn = new JButton("Mã hoá");
        JButton decryptBtn = new JButton("Giải mã");

        encryptBtn.addActionListener(this::handleEncrypt);
        decryptBtn.addActionListener(this::handleDecrypt);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Nhập nội dung:"), BorderLayout.NORTH);
        topPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(3, 1));
        bottomPanel.add(new JScrollPane(outputArea));
        bottomPanel.add(algorithmBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(encryptBtn);
        buttonPanel.add(decryptBtn);
        bottomPanel.add(buttonPanel);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createPasswordTab() {
        JPanel panel = new JPanel(new BorderLayout());

        passwordField = new JTextField();
        JButton hashBtn = new JButton("Băm mật khẩu");
        hashResultArea = new JTextArea(5, 40);
        hashResultArea.setEditable(false);

        hashBtn.addActionListener(e -> {
            try {
                String pass = passwordField.getText();
                String hashed = HashUtil.hashPassword(pass);
                hashResultArea.setText(hashed);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        panel.add(new JLabel("Nhập mật khẩu:"), BorderLayout.NORTH);
        panel.add(passwordField, BorderLayout.CENTER);
        panel.add(hashBtn, BorderLayout.EAST);
        panel.add(new JScrollPane(hashResultArea), BorderLayout.SOUTH);

        return panel;
    }

    private void handleEncrypt(ActionEvent e) {
        String text = inputArea.getText();
        String algoName = (String) algorithmBox.getSelectedItem();
        try {
            Encryptable algo = getAlgorithm(algoName);
            Thread t = new EncryptDecryptTask(algo, text, true, outputArea);
            t.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleDecrypt(ActionEvent e) {
        String text = inputArea.getText();
        String algoName = (String) algorithmBox.getSelectedItem();
        try {
            Encryptable algo = getAlgorithm(algoName);
            Thread t = new EncryptDecryptTask(algo, text, false, outputArea);
            t.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private Encryptable getAlgorithm(String name) throws Exception {
        if (name.equals("AES")) {
            return new AESAlgorithm(); // AES sử dụng khoá đối xứng
        } else if (name.equals("RSA")) {
            return new RSAAlgorithm(rsaKeyPair.getPrivate(), rsaKeyPair.getPublic()); // RSA sử dụng khoá công khai và riêng tư
        } else {
            throw new IllegalArgumentException("Unsupported algorithm: " + name);
        }
    }

//    // Getter cho outputArea để cập nhật kết quả từ EncryptDecryptTask
//    public static JTextArea getOutputArea() {
//        return outputArea;
//    }
}
