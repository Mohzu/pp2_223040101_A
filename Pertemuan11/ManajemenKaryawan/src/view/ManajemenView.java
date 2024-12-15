/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Moch Zuhdi Maulana N
 */


import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ManajemenView extends JFrame {
    private JTextField txtName = new JTextField(20);
    private JTextField txtPosition = new JTextField(20);
    private JTextField txtSalary = new JTextField(20);
    private JTextField txtStatus = new JTextField(20);  
    private JButton btnAdd = new JButton("Add Employee");
    private JButton btnRefresh = new JButton("Refresh");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");
    private int selectedRow = -1;
    private JTable employeeTable;
    private DefaultTableModel tableModel;

    public ManajemenView() {
        setTitle("Employee Management");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel untuk input form
        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.add(new JLabel("Name:"));
        panel.add(txtName);
        panel.add(new JLabel("Position:"));
        panel.add(txtPosition);
        panel.add(new JLabel("Gaji:"));
        panel.add(txtSalary);
        panel.add(new JLabel("Status Karyawan:"));  // Label untuk status
        panel.add(txtStatus);  // Input field untuk status

        // Panel untuk tombol-tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        panel.add(buttonPanel);

        // Tabel dengan kolom yang diperbarui
        String[] columnNames = {"ID", "Name", "Position", "Gaji", "Status Karyawan"};
        tableModel = new DefaultTableModel(columnNames, 0);
        employeeTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(employeeTable);

        // Seleksi baris pada tabel dan menampilkan detail karyawan di form
        employeeTable.getSelectionModel().addListSelectionListener(e -> {
            selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
                String id = (String) tableModel.getValueAt(selectedRow, 0);
                txtName.setText((String) tableModel.getValueAt(selectedRow, 1));
                txtPosition.setText((String) tableModel.getValueAt(selectedRow, 2));
                txtSalary.setText((String) tableModel.getValueAt(selectedRow, 3));
                txtStatus.setText((String) tableModel.getValueAt(selectedRow, 4));  // Menampilkan status karyawan
            }
        });

        // Menambahkan panel dan tabel ke frame
        add(panel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    // Mendapatkan ID karyawan yang dipilih
    public String getSelectedEmployeeId() {
        if (selectedRow != -1) {
            return (String) tableModel.getValueAt(selectedRow, 0); 
        }
        return null;
    }

    // Mengambil input dari form
    public String getNameInput() {
        return txtName.getText();
    }

    public String getPositionInput() {
        return txtPosition.getText();
    }

    public String getSalaryInput() {
        return txtSalary.getText();
    }

    public String getStatusInput() {
        return txtStatus.getText();  // Mengambil input untuk status
    }

    // Menambahkan karyawan ke dalam tabel
    public void addEmployeeToTable(String id, String name, String position, String salary, String status) {
        String[] row = {id, name, position, salary, status};  // Menambahkan status karyawan
        tableModel.addRow(row);
    }

    // Menampilkan daftar karyawan dalam tabel
    public void setEmployeeList(String[][] employees) {
        tableModel.setRowCount(0);
        for (String[] employee : employees) {
            tableModel.addRow(employee);
        }
    }

    // Menambahkan listener untuk tombol-tombol
    public void addAddEmployeeListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }

    public void addUpdateEmployeeListener(ActionListener listener) {
        btnUpdate.addActionListener(listener);
    }

    public void addDeleteEmployeeListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }
}

