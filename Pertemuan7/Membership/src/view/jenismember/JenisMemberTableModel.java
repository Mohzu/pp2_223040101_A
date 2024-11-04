/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan7.Membership.src.view.jenismember;

/**
 *
 * @author Moch Zuhdi Maulana N
 */

import Pertemuan7.Membership.src.model.JenisMember;
import javax.swing.table.AbstractTableModel; 
import java.util.List;

class JenisMemberTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama"};
        private List<JenisMember> data;

    public JenisMemberTableModel(List<JenisMember> data) {
        this.data = data;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    
    public int getRowCount() {
        return data.size();
    }

    
    public String getColumnName(int col) {
        return columnNames[col];
    }

    
    public Object getValueAt(int row, int col) {
        JenisMember rowItem = data.get(row);
        String value = "";
        switch (col) {
            case 0:
                value = rowItem.getNama();
                break;
        }
        return value;
    }

    public boolean isCellEditable(int row, int col) {
        return false; // Tidak dapat diedit
    }

    public void add(JenisMember value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}
