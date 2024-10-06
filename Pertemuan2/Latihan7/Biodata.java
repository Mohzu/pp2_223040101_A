package Pertemuan2.Latihan7;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Biodata extends JFrame {
    private JTextField textFieldNama, textFieldHP;
    private JRadioButton radioLakiLaki, radioPerempuan;
    private JCheckBox checkBoxWNA;
    private JTextArea txtOutput;

    public Biodata() {
        // Set default close operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 600);
        this.setTitle("Aplikasi Biodata");
        this.setLayout(null); // Menggunakan layout null untuk penataan manual

        // Label Nama
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 20, 350, 20);
        this.add(labelNama);

        // TextField Nama
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 50, 350, 30);
        this.add(textFieldNama);

        // Label Nomor HP
        JLabel labelHP = new JLabel("Nomor HP:");
        labelHP.setBounds(15, 90, 350, 20);
        this.add(labelHP);

        // TextField Nomor HP
        textFieldHP = new JTextField();
        textFieldHP.setBounds(15, 120, 350, 30);
        this.add(textFieldHP);

        // Label Jenis Kelamin
        JLabel labelJenisKelamin = new JLabel("Jenis Kelamin:");
        labelJenisKelamin.setBounds(15, 150, 100, 20);
        this.add(labelJenisKelamin);

        // Radio Button Laki-Laki
        radioLakiLaki = new JRadioButton("Laki-Laki");
        radioLakiLaki.setBounds(15, 180, 350, 30);
        this.add(radioLakiLaki);

        // Radio Button Perempuan
        radioPerempuan = new JRadioButton("Perempuan");
        radioPerempuan.setBounds(15, 210, 350, 30);
        this.add(radioPerempuan);

        // Group untuk radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(radioLakiLaki);
        group.add(radioPerempuan);

        // Checkbox WNA
        checkBoxWNA = new JCheckBox("Warga Negara Asing");
        checkBoxWNA.setBounds(15, 240, 350, 30);
        this.add(checkBoxWNA);

        // Tombol Simpan
        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 280, 100, 30);
        this.add(buttonSimpan);

        // TextArea untuk output
        txtOutput = new JTextArea();
        txtOutput.setBounds(15, 330, 350, 150);
        txtOutput.setEditable(false);
        this.add(txtOutput);

        // ActionListener untuk tombol Simpan
        buttonSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simpanBiodata();
            }
        });

        // Set visibility
        this.setVisible(true);
    }

    // Method untuk menyimpan dan menampilkan biodata
    private void simpanBiodata() {
        String nama = textFieldNama.getText();  
        String nomorHP = textFieldHP.getText();
        String jenisKelamin = radioLakiLaki.isSelected() ? "Laki-Laki" : radioPerempuan.isSelected() ? "Perempuan" : "Tidak Dipilih";
        String wna = checkBoxWNA.isSelected() ? "Ya" : "Bukan";

        // Menampilkan output
        txtOutput.append("Nama       : " + nama + "\n");
        txtOutput.append("Nomor HP   : " + nomorHP + "\n");
        txtOutput.append("Jenis Kelamin : " + jenisKelamin + "\n");
        txtOutput.append("WNA        : " + wna + "\n");
        txtOutput.append("============================================\n");

        // Mengosongkan input setelah simpan
        textFieldNama.setText("");
        textFieldHP.setText("");
        radioLakiLaki.setSelected(false);
        radioPerempuan.setSelected(false);
        checkBoxWNA.setSelected(false);
    }

    public static void main(String[] args) {
        new Biodata();
    }
}

