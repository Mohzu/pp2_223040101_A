package Pertemuan1.Tugas1;

import javax.swing.*;

public class HelloZuhdi {
    private static void createAndShowGUI() {

        JFrame frame = new JFrame("HelloZuhdi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello,Zuhdi");
        frame.getContentPane().add(label);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

