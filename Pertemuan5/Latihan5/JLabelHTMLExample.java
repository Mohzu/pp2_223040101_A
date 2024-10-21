package Pertemuan5.Latihan5;

import javax.swing.*;

public class JLabelHTMLExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JLabel HTML Example");

        // Membuat JLabel dengan teks HTML
        JLabel label = new JLabel("<html><b>Teks Tebal</b>, <i>Teks Miring</i>, dan <u>Teks Garis Bawah</u></html>");

        // Menambahkan JLabel ke JFrame
        frame.add(label);

        // Konfigurasi frame
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

