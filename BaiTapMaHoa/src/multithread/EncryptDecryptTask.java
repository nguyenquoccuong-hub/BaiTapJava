package multithread;

import algorithm.Encryptable;

import javax.swing.*;

public class EncryptDecryptTask extends Thread {

    private Encryptable algorithm;
    private String input;
    private boolean isEncrypt;
    private JTextArea outputArea;

    public EncryptDecryptTask(Encryptable algorithm, String input, boolean isEncrypt, JTextArea outputArea) {
        this.algorithm = algorithm;
        this.input = input;
        this.isEncrypt = isEncrypt;
        this.outputArea = outputArea;
    }

    @Override
    public void run() {
        try {
            String result;
            if (isEncrypt) {
                result = algorithm.encrypt(input);
                SwingUtilities.invokeLater(() -> outputArea.setText("Mã hoá:\n" + result));
            } else {
                result = algorithm.decrypt(input);
                SwingUtilities.invokeLater(() -> outputArea.setText("Giải mã:\n" + result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


