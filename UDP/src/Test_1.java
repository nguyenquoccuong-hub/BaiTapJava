import javax.swing.*;

public class Test_1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatClientUDP client = new ChatClientUDP();
            client.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    client.sendLeaveMessage();
                    super.windowClosing(e);
                }
            });
        });
    }

}
