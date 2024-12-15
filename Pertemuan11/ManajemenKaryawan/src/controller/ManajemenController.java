/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Moch Zuhdi Maulana N
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.List;
import model.*;
import org.apache.ibatis.session.SqlSession;
import view.ManajemenView;

public class ManajemenController {
    private ManajemenView view;
    private ManajemenMapper mapper;

    public ManajemenController(ManajemenView view, ManajemenMapper mapper) {
        this.view = view;
        this.mapper = mapper;

        this.view.addAddEmployeeListener(new AddEmployeeListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addDeleteEmployeeListener(new DeleteEmployeeListener());
        this.view.addUpdateEmployeeListener(new UpdateEmployeeListener());
    }

    class AddEmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String position = view.getPositionInput();
            String salary = view.getSalaryInput();
            String status = view.getStatusInput();  // Mengambil input status

            // Validasi status karyawan
            if (status.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Status cannot be empty.");
                return;
            }

            try (SqlSession session = MyBatisUtil.getSqlSession()) {
                ManajemenMapper mapper = session.getMapper(ManajemenMapper.class);

                if (!name.isEmpty() && !position.isEmpty() && !salary.isEmpty() && !status.isEmpty()) {
                    double salaryDouble = Double.parseDouble(salary);

                    Manajemen employee = new Manajemen();
                    employee.setName(name);
                    employee.setPosition(position);
                    employee.setSalary(salaryDouble);
                    employee.setStatus(status);  // Mengatur status karyawan

                    mapper.insertEmployee(employee);
                    session.commit();
                    JOptionPane.showMessageDialog(view, "Employee added successfully!");
                } else {
                    JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                }
            } 
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setEmployeeList(new String[0][0]);

            List<Manajemen> employees = mapper.getAllEmployees();

            String[][] employeeArray = employees.stream()
                .map(employee -> new String[] {
                    String.valueOf(employee.getId()),
                    employee.getName(),
                    employee.getPosition(),
                    String.valueOf(employee.getSalary()),
                    employee.getStatus()  // Menampilkan status karyawan
                })
                .toArray(String[][]::new);

            // Debugging output
            for (Manajemen employee : employees) {
                System.out.println("Employee: " + employee.getName() + ", Status: " + employee.getStatus());
            }
            
            for (String[] row : employeeArray) {
                System.out.println("Row data: " + String.join(", ", row));  // Debugging untuk data tabel
            }

            view.setEmployeeList(employeeArray);
        }
    }

    class DeleteEmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedId = view.getSelectedEmployeeId();
            if (selectedId != null) {
                try (SqlSession session = MyBatisUtil.getSqlSession()) {
                    ManajemenMapper mapper = session.getMapper(ManajemenMapper.class);

                    // Delete employee based on ID
                    mapper.deleteEmployeeById(Integer.parseInt(selectedId));
                    session.commit(); // Commit transaction

                    JOptionPane.showMessageDialog(view, "Employee deleted successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select an employee to delete.");
            }
        }
    }

    class UpdateEmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedId = view.getSelectedEmployeeId();
            if (selectedId != null) {
                String name = view.getNameInput();
                String position = view.getPositionInput();
                String salary = view.getSalaryInput();
                String status = view.getStatusInput();  // Mengambil input status

                // Validasi status karyawan
                if (status.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Status cannot be empty.");
                    return;
                }

                try (SqlSession session = MyBatisUtil.getSqlSession()) {
                    ManajemenMapper mapper = session.getMapper(ManajemenMapper.class);

                    if (!name.isEmpty() && !position.isEmpty() && !salary.isEmpty() && !status.isEmpty()) {
                        double salaryDouble = Double.parseDouble(salary);

                        Manajemen employee = new Manajemen();
                        employee.setId(Integer.parseInt(selectedId));
                        employee.setName(name);
                        employee.setPosition(position);
                        employee.setSalary(salaryDouble);
                        employee.setStatus(status);  // Mengatur status karyawan

                        mapper.updateEmployee(employee);
                        session.commit(); // Commit transaction
                        JOptionPane.showMessageDialog(view, "Employee updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "Please enter a valid number for salary.");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select an employee to update.");
            }
        }
    }
}
