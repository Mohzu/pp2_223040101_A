/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan6.Tugas;

/**
 *
 * @author Moch Zuhdi Maulana N
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelReservationApp extends JFrame {
    private DefaultTableModel tableModel;
    private JTextField nameField, dateField;
    private JComboBox<String> roomTypeCombo;
    private JCheckBox wifiCheck, breakfastCheck;
    private JSpinner guestCountSpinner;
    private JList<String> preferenceList;
    private JSlider durationSlider;
    private JRadioButton balconyRoomRadio, noBalconyRoomRadio;
    private JTextArea reviewArea;

    public HotelReservationApp() {
        setTitle("Aplikasi Reservasi Hotel");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Setup Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItemReset = new JMenuItem("Reset");
        JMenuItem menuItemExit = new JMenuItem("Exit");

        menuItemReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        menuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });

        menu.add(menuItemReset);
        menu.add(menuItemExit);
        menuBar.add(menu);
        setJMenuBar(menuBar); // Set menu bar to the frame

        // Panel Utama dengan BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel Input di Kiri
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nama
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Nama:"), gbc);
        nameField = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        // Tanggal
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Tanggal Reservasi:"), gbc);
        dateField = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(dateField, gbc);

        // Jenis Kamar
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Jenis Kamar:"), gbc);
        String[] roomTypes = {"Standard", "Deluxe", "Suite"};
        roomTypeCombo = new JComboBox<>(roomTypes);
        gbc.gridx = 1;
        inputPanel.add(roomTypeCombo, gbc);

        // Preferensi Kamar
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Preferensi Kamar:"), gbc);
        balconyRoomRadio = new JRadioButton("Kamar dengan Balkon");
        noBalconyRoomRadio = new JRadioButton("Kamar Tanpa Balkon");
        ButtonGroup roomPreferenceGroup = new ButtonGroup();
        roomPreferenceGroup.add(balconyRoomRadio);
        roomPreferenceGroup.add(noBalconyRoomRadio);
        gbc.gridx = 1;
        inputPanel.add(balconyRoomRadio, gbc);
        gbc.gridy++;
        inputPanel.add(noBalconyRoomRadio, gbc);

        // Fasilitas Tambahan
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Fasilitas Tambahan:"), gbc);
        wifiCheck = new JCheckBox("WiFi");
        breakfastCheck = new JCheckBox("Sarapan");
        gbc.gridx = 1;
        inputPanel.add(wifiCheck, gbc);
        gbc.gridy++;
        inputPanel.add(breakfastCheck, gbc);

        // Jumlah Tamu
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Jumlah Tamu:"), gbc);
        guestCountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        gbc.gridx = 1;
        inputPanel.add(guestCountSpinner, gbc);

        // Durasi Menginap
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Durasi Menginap: "), gbc);
        durationSlider = new JSlider(1, 10);
        durationSlider.setMajorTickSpacing(1);
        durationSlider.setPaintTicks(true);
        durationSlider.setPaintLabels(true);
        gbc.gridx = 1;
        inputPanel.add(durationSlider, gbc);

        // Metode Pembayaran
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Metode Pembayaran: "), gbc);
        String[] paymentOptions = {"Cash", "Kartu Kredit", "Transfer"};
        preferenceList = new JList<>(paymentOptions);
        preferenceList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        gbc.gridx = 1;
        inputPanel.add(new JScrollPane(preferenceList), gbc); // Menggunakan JScrollPane untuk JList

        // Catatan Tambahan
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Catatan Tambahan:"), gbc);
        reviewArea = new JTextArea(3, 20); // area teks untuk catatan tambahan
        reviewArea.setLineWrap(true); // Mengatur teks agar otomatis membungkus
        reviewArea.setWrapStyleWord(true); // Membungkus kata
        gbc.gridx = 1;
        inputPanel.add(reviewArea, gbc);

        // Tombol Kirim
        gbc.gridy++;
        JButton submitButton = new JButton("Kirim Reservasi");
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String date = dateField.getText();
            String roomType = (String) roomTypeCombo.getSelectedItem();
            String roomPreference = balconyRoomRadio.isSelected() ? "Kamar dengan Balkon" : "Kamar Tanpa Balkon";
            String facilities = (wifiCheck.isSelected() ? "WiFi " : "") +
                                (breakfastCheck.isSelected() ? "Sarapan " : "");
            int guestCount = (Integer) guestCountSpinner.getValue();
            int duration = durationSlider.getValue();
            String paymentMethods = String.join(", ", preferenceList.getSelectedValuesList());
            String review = reviewArea.getText();

            // Update the table model
            tableModel.addRow(new Object[]{name, date, roomType, roomPreference, facilities, guestCount, duration, paymentMethods, review});
        });

        gbc.gridx = 1;
        inputPanel.add(submitButton, gbc);

        // Panel Tabel di Kanan
        String[] columnNames = {"Nama", "Tanggal", "Jenis Kamar", "Preferensi Kamar", "Fasilitas", "Jumlah Tamu", "Durasi Menginap", "Metode Pembayaran",
        "Catatan Tambahan"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable reservationTable = new JTable(tableModel);

        // Scroll Pane untuk Tabel
        JScrollPane tableScrollPane = new JScrollPane(reservationTable);
        tableScrollPane.setPreferredSize(new Dimension(400, 200));

        // Tambahkan Panel Input dan Tabel ke Main Panel
        mainPanel.add(inputPanel, BorderLayout.WEST);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Tambahkan Main Panel ke Frame
        add(mainPanel);
        
    }

    private void resetForm() {
    nameField.setText(""); // Reset nama
    dateField.setText(""); // Reset tanggal reservasi
    roomTypeCombo.setSelectedIndex(0); // Reset combo box ke jenis kamar pertama
    balconyRoomRadio.setSelected(false); // Reset radio button kamar balkon
    noBalconyRoomRadio.setSelected(false); // Reset radio button kamar tanpa balkon
    wifiCheck.setSelected(false); // Reset checkbox WiFi
    breakfastCheck.setSelected(false); // Reset checkbox sarapan
    guestCountSpinner.setValue(1); // Reset spinner jumlah tamu ke nilai default 1
    preferenceList.clearSelection(); // Reset list preferensi pembayaran
    durationSlider.setValue(1); // Reset slider durasi menginap ke nilai default 1
    reviewArea.setText(""); // Reset area teks catatan tambahan

    // Reset tabel
    tableModel.setRowCount(0); // Hapus semua baris di tabel
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HotelReservationApp().setVisible(true);
        });
    }
}
