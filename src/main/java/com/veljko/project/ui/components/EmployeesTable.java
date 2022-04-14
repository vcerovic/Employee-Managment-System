package com.veljko.project.ui.components;

import com.veljko.project.ui.services.Colors;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.Font;
import java.util.EventObject;

public class EmployeesTable extends JTable {

    private final DefaultTableModel model;

    public EmployeesTable(DefaultTableModel model) {
        this.model = model;
        this.setFocusable(false);
        this.setRowSelectionAllowed(false);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.setRowHeight(25);
        this.setGridColor(Colors.PRIMARY_COLOR.getColor());
        this.removeEditor();
        this.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.setForeground(Colors.PRIMARY_COLOR.getColor());
        this.setModel(model);
    }

    @Override
    public boolean editCellAt(int row, int column, EventObject e) {
        return false;
    }

    public void addRowToTable(Object[] row) {
        model.addRow(row);
    }

    public void clearTable() {
        model.setRowCount(0);
    }

    public void changeColWidth() {
        if (this.getColumnCount() != 0) {
            TableColumnModel tableColumnModel = this.getColumnModel();

            tableColumnModel.getColumn(0).setPreferredWidth(40);
            tableColumnModel.getColumn(1).setPreferredWidth(120);
            tableColumnModel.getColumn(2).setPreferredWidth(185);
            tableColumnModel.getColumn(3).setPreferredWidth(35);
            tableColumnModel.getColumn(4).setPreferredWidth(100);
        }
    }
}
