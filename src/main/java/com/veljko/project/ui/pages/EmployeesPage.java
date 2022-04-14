package com.veljko.project.ui.pages;

import com.veljko.project.ui.components.EmployeesTable;
import com.veljko.project.ui.components.HeadingLabel;
import com.veljko.project.database.EmployeeDAO;
import com.veljko.project.database.model.Employee;
import com.veljko.project.ui.services.Colors;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EmployeesPage extends JPanel {

    private final EmployeesTable employeesTable;

    public EmployeesPage() {
        this.setName("EmployeesPage");
        this.add(new HeadingLabel("All employees", Colors.PRIMARY_COLOR.getColor()));

        this.employeesTable = new EmployeesTable(new DefaultTableModel(new String[]{"ID", "Name", "Address", "Age", "Salary"}, 0));
        JScrollPane scrollPane = new JScrollPane(employeesTable);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(scrollPane);

        employeesTable.changeColWidth();
    }

    public void fetchEmployees() {
        List<Employee> allEmployees = EmployeeDAO.getAllEmployees();

        employeesTable.clearTable();


        for (Employee employee : allEmployees) {
            employeesTable.addRowToTable(new Object[]{employee.getId(), employee.getName(), employee.getAddress(), employee.getAge(), employee.getSalary()});
        }

    }
}

