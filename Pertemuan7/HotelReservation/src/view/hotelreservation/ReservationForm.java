package Pertemuan7.HotelReservation.src.view.hotelreservation;

import Pertemuan7.HotelReservation.src.dao.ReservationDAO;
import Pertemuan7.HotelReservation.src.model.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReservationForm extends JPanel {
    private ReservationDAO reservationDAO = new ReservationDAO();

    private JTextField nameField, dateField;
    private JComboBox<String> roomTypeCombo;
    private JCheckBox wifiCheck, breakfastCheck;
    private JSpinner guestCountSpinner;
    private JList<String> preferenceList;
    private JSlider durationSlider;
    private JRadioButton balconyRoomRadio, noBalconyRoomRadio;
    private JTextArea reviewArea;
    private JButton submitButton, updateButton, deleteButton;

    private Reservation selectedReservation = null;
    private DefaultTableModel tableModel;
    private String[] paymentOptions = {"Cash", "Kartu Kredit", "Transfer"};
    private JTable reservationTable;

    public ReservationForm() {
        setLayout(new BorderLayout(10, 10));

        // Panel Utama, Input, dan Tabel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel Input di Kiri
        JPanel inputPanel = createInputPanel();

        // Panel Tabel di Kanan
        JPanel tablePanel = createTablePanel();

        // Tambahkan Input Panel dan Tabel ke Main Panel
        mainPanel.add(inputPanel, BorderLayout.WEST);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Panel Tombol di Bawah
        JPanel buttonPanel = createButtonPanel();

        // Menambahkan Panel ke Form
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Memuat data tabel awal
        updateTable();

     
    // Menambahkan listener untuk memilih baris di tabel
    reservationTable.getSelectionModel().addListSelectionListener(e -> {
        int selectedRow = reservationTable.getSelectedRow();
        if (selectedRow >= 0) {
            // Mengambil nama atau data lain dari baris yang dipilih
            String name = (String) tableModel.getValueAt(selectedRow, 0);

            // Mencari objek Reservation berdasarkan nama atau data lainnya
            selectedReservation = reservationDAO.getReservationByName(name);
            if (selectedReservation != null) {
                setSelectedReservation(selectedReservation); // Mengatur data ke form
            }
         }
        });
    }
    private JPanel createInputPanel() {
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
        preferenceList = new JList<>(paymentOptions);
        preferenceList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        gbc.gridx = 1;
        inputPanel.add(new JScrollPane(preferenceList), gbc);

        // Catatan Tambahan
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Catatan Tambahan:"), gbc);
        reviewArea = new JTextArea(3, 20);
        reviewArea.setLineWrap(true);
        reviewArea.setWrapStyleWord(true);
        gbc.gridx = 1;
        inputPanel.add(new JScrollPane(reviewArea), gbc);

        return inputPanel;
    }

    private JPanel createTablePanel() {
        String[] columnNames = {"Nama", "Tanggal", "Jenis Kamar", "Preferensi Kamar", "Fasilitas", "Jumlah Tamu", "Durasi Menginap", "Metode Pembayaran", "Catatan Tambahan"};
        tableModel = new DefaultTableModel(columnNames, 0);
        reservationTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(reservationTable);
        tableScrollPane.setPreferredSize(new Dimension(400, 200));

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        return tablePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        submitButton = new JButton("Kirim Reservasi");
        updateButton = new JButton("Update Reservasi");
        deleteButton = new JButton("Hapus Reservasi");

        buttonPanel.add(submitButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        submitButton.addActionListener(e -> {
            Reservation reservation = getFormData();
            reservationDAO.addReservation(reservation);
            JOptionPane.showMessageDialog(this, "Reservasi berhasil!");
            updateTable();
        });

       // Update Button Action Listener
        updateButton.addActionListener(e -> {
        if (selectedReservation != null) {
            // Mengambil data yang sudah diubah dari form
            Reservation updatedReservation = getFormData();
            updatedReservation.setId(selectedReservation.getId()); // ID tetap diperlukan untuk identifikasi objek (jika masih diperlukan di DAO)

            // Memperbarui data berdasarkan nama
            reservationDAO.updateReservation(updatedReservation);  // Update data di DAO
            JOptionPane.showMessageDialog(this, "Reservasi berhasil diperbarui!");
            updateTable(); // Memperbarui tabel setelah update
            resetForm(); // Reset form setelah update
        }
      });

        // Delete Button Action Listener
        deleteButton.addActionListener(e -> {
            if (selectedReservation != null) {
                // Menghapus data berdasarkan nama
                reservationDAO.deleteReservation(selectedReservation.getName());
                JOptionPane.showMessageDialog(this, "Reservasi berhasil dihapus!");
                updateTable(); // Memperbarui tabel setelah delete
                resetForm(); // Reset form setelah delete
         }
        });


        return buttonPanel;
    }

   private void updateTable() {
    // Membersihkan data lama di tabel
    tableModel.setRowCount(0);

    // Mengambil data reservasi terbaru dari DAO
    List<Reservation> reservations = reservationDAO.getAllReservations();
    for (Reservation reservation : reservations) {
        String facilities = (reservation.isWifi() ? "WiFi " : "") + (reservation.isBreakfast() ? "Sarapan" : "");
        String paymentMethods = String.join(", ", reservation.getPaymentMethods()); // Menggabungkan pembayaran yang dipilih menjadi satu string
        tableModel.addRow(new Object[]{
            reservation.getName(),
            reservation.getDate(),
            reservation.getRoomType(),
            reservation.getRoomPreference(),
            facilities,
            reservation.getGuestCount(),
            reservation.getDuration(),
            paymentMethods,  // Menampilkan metode pembayaran sebagai string
            reservation.getReview()
        });
    }
}


    private void setSelectedReservation(Reservation reservation) {
        selectedReservation = reservation;
        nameField.setText(reservation.getName());
        dateField.setText(reservation.getDate());
        roomTypeCombo.setSelectedItem(reservation.getRoomType());
        balconyRoomRadio.setSelected("Kamar dengan Balkon".equals(reservation.getRoomPreference()));
        noBalconyRoomRadio.setSelected("Kamar Tanpa Balkon".equals(reservation.getRoomPreference()));
        wifiCheck.setSelected(reservation.isWifi());
        breakfastCheck.setSelected(reservation.isBreakfast());
        guestCountSpinner.setValue(reservation.getGuestCount());
        durationSlider.setValue(reservation.getDuration());
        // Memperbaiki pemilihan preferensi pembayaran berdasarkan data yang disimpan
        preferenceList.setSelectedIndices(reservation.getPaymentMethods().stream()
            .map(payment -> java.util.Arrays.asList(paymentOptions).indexOf(payment))
            .mapToInt(Integer::intValue)
            .toArray());
        reviewArea.setText(reservation.getReview());
    }

    private Reservation getFormData() {
        String name = nameField.getText();
        String date = dateField.getText();
        String roomType = (String) roomTypeCombo.getSelectedItem();
        String roomPreference = balconyRoomRadio.isSelected() ? "Kamar dengan Balkon" : "Kamar Tanpa Balkon";
        boolean wifi = wifiCheck.isSelected();
        boolean breakfast = breakfastCheck.isSelected();
        int guestCount = (Integer) guestCountSpinner.getValue();
        int duration = durationSlider.getValue();
        List<String> paymentMethods = preferenceList.getSelectedValuesList(); // Pastikan ini list String
        String review = reviewArea.getText();

        Reservation reservation = new Reservation();
        reservation.setName(name);
        reservation.setDate(date);
        reservation.setRoomType(roomType);
        reservation.setRoomPreference(roomPreference);
        reservation.setWifi(wifi);
        reservation.setBreakfast(breakfast);
        reservation.setGuestCount(guestCount);
        reservation.setDuration(duration);
        reservation.setPaymentMethods(paymentMethods);  // Simpan sebagai List<String>
        reservation.setReview(review);

        return reservation;
    }

    public void resetForm() {
        // Reset form input
        nameField.setText("");
        dateField.setText("");
        roomTypeCombo.setSelectedIndex(0);
        balconyRoomRadio.setSelected(false);
        noBalconyRoomRadio.setSelected(false);
        wifiCheck.setSelected(false);
        breakfastCheck.setSelected(false);
        guestCountSpinner.setValue(1);
        durationSlider.setValue(1);
        preferenceList.clearSelection();
        reviewArea.setText("");
    }
}
