package Pertemuan2.Latihan4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AplikasiBiodata extends JFrame {
    
    // Variabel instance
    private JLabel namaLabel;
    private JTextField namaInput;
    private JLabel teleponLabel;
    private JTextField teleponInput;
    private JButton tambahButton;
    private JTextArea outputArea;

    public AplikasiBiodata() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Aplikasi Biodata Teman");
        
        // Inisialisasi komponen
        namaLabel = new JLabel("Nama:");
        namaLabel.setBounds(50, 30, 120, 25);
        
        namaInput = new JTextField();
        namaInput.setBounds(180, 30, 200, 25);
        
        teleponLabel = new JLabel("Nomor Telepon:");
        teleponLabel.setBounds(50, 70, 120, 25);
        
        teleponInput = new JTextField();
        teleponInput.setBounds(180, 70, 200, 25);

        tambahButton = new JButton("Simpan Biodata");
        tambahButton.setBounds(130, 110, 150, 30);
        
        outputArea = new JTextArea();
        outputArea.setBounds(50, 150, 320, 200); 
        outputArea.setEditable(false); // Agar JTextArea tidak dapat diedit
        
        // Menambahkan aksi pada tombol
        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanBiodata();
            }
        });

        // Menambahkan komponen ke JFrame
        this.add(namaLabel);
        this.add(namaInput);
        this.add(teleponLabel);
        this.add(teleponInput);
        this.add(tambahButton);
        this.add(outputArea);
        
        setSize(400, 400);
        setLayout(null); // Menggunakan null layout
    }

    // Method private untuk menyimpan biodata
    private void simpanBiodata() {
        String nama = namaInput.getText().trim();
        String telepon = teleponInput.getText().trim();

        if (!nama.isEmpty() && !telepon.isEmpty()) {
            outputArea.append("Nama: " + nama + "\n");
            outputArea.append("Nomor Telepon: " + telepon + "\n");
            outputArea.append("===================================\n"); // Separator antara biodata
            
            // Mengosongkan field input setelah data dimasukkan
            namaInput.setText("");
            teleponInput.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Nama dan nomor telepon harus diisi!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplikasiBiodata frame = new AplikasiBiodata();
            frame.setVisible(true);
        });
    }
}
