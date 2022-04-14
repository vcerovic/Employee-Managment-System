package com.veljko.project.ui.pages;

import com.veljko.project.ui.components.ActionButton;
import com.veljko.project.ui.components.EmployeesTable;
import com.veljko.project.ui.components.HeadingLabel;
import com.veljko.project.database.EmployeeDAO;
import com.veljko.project.database.model.Employee;
import com.veljko.project.ui.services.Colors;
import com.veljko.project.ui.services.Spacer;
import com.veljko.project.ui.services.Validator;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSearchPage extends JPanel {

    private static boolean isDisplayed = false;
    private JCheckBox nameFilter;
    private JCheckBox ageFilter;
    private JCheckBox salaryFilter;
    private JTextField nameFilterField;
    private JTextField ageFilterField;
    private JSlider salaryFilterSlider;

    public EmployeeSearchPage() {
        this.setName("EmployeeSearchPage");
        this.add(new HeadingLabel("Search employees", Colors.PRIMARY_COLOR.getColor()));

        initComponents();

        JPanel filtersHolder = crateFiltersHolder();

        //Filter Table
        EmployeesTable filtersTable = new EmployeesTable(new DefaultTableModel(new String[]{"ID", "Name", "Address", "Age", "Salary"}, 0));
        JScrollPane scrollPane = new JScrollPane(filtersTable);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        filtersTable.changeColWidth();

        ActionButton searchBtn = new ActionButton("src/main/java/com/veljko/project/icons/search.png");
        ActionButton.addHoverEffect(searchBtn);

        handleFilterSearch(filtersTable, searchBtn);

        this.add(scrollPane);
        this.add(new Spacer(10, 0));
        this.add(filtersHolder);
        filtersHolder.add(searchBtn);

    }

    private void initComponents() {
        nameFilter = new JCheckBox("Search by name");
        ageFilter = new JCheckBox("Search by age");
        salaryFilter = new JCheckBox("Search by salary");
        nameFilterField = new JTextField();
        ageFilterField = new JTextField();
        ageFilterField.setName("Age field");
        salaryFilterSlider = new JSlider(0, 200000, 40000);
    }

    private void handleFilterSearch(EmployeesTable filtersTable, ActionButton searchBtn) {
        searchBtn.addActionListener(e -> {
            if (nameFilter.isSelected() || ageFilter.isSelected() || salaryFilter.isSelected()) {
                String name = "";
                int age = 200;
                int salary = salaryFilterSlider.getMaximum();

                if (nameFilter.isSelected()) {
                    if (!nameFilterField.getText().equals("")) {
                        name = nameFilterField.getText();
                    } else {
                        JOptionPane.showMessageDialog(null, "You must fill up name field.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        nameFilter.setSelected(false);
                    }
                }

                if (ageFilter.isSelected()) {
                    if (!ageFilterField.getText().equals("")) {
                        if (Validator.validateIfNumber(new JTextField[]{ageFilterField})) {
                            age = Integer.parseInt(ageFilterField.getText());
                        } else {
                            ageFilter.setSelected(false);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "You must fill up age field.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        ageFilter.setSelected(false);
                    }
                }

                if (salaryFilter.isSelected()) {
                    salary = salaryFilterSlider.getValue();
                }

                if (nameFilter.isSelected() || ageFilter.isSelected() || salaryFilter.isSelected()) {
                    List<Employee> employeesByFilter = EmployeeDAO.findEmployeesByFilter(
                            name,
                            age,
                            salary,
                            nameFilter.isSelected(),
                            ageFilter.isSelected(),
                            salaryFilter.isSelected());


                    filtersTable.clearTable();

                    if (employeesByFilter != null) {
                        for (Employee employee : employeesByFilter) {
                            filtersTable.addRowToTable(new Object[]{employee.getId(), employee.getName(), employee.getAddress(), employee.getAge(), employee.getSalary()});
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "You must check at least one check-box.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private JPanel crateFiltersHolder() {
        //Filter holder
        JPanel filtersHolder = new JPanel();
        filtersHolder.setPreferredSize(new Dimension(580, 450));
        filtersHolder.setLayout(new GridLayout(11, 1));

        //Label
        JLabel salaryLabel = new JLabel("");

        salaryFilterSlider.setMajorTickSpacing(20000);
        salaryFilterSlider.setMinorTickSpacing(5000);
        salaryFilterSlider.setPaintTicks(true);
        salaryFilterSlider.setPaintLabels(true);


        JCheckBox[] checkBoxes = {ageFilter, salaryFilter};

        for (JCheckBox checkBox : checkBoxes) {
            checkBox.addItemListener(e -> {
                if (!isDisplayed) {
                    JOptionPane.showMessageDialog(this, "When you enter filters for years and salary, " +
                                    "the system will find all employees who have a smaller value than entered.", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                    isDisplayed = true;
                }
            });
        }


        salaryFilterSlider.addChangeListener(e -> salaryLabel.setText("Selected salary: " + ((JSlider) e.getSource()).getValue()));

        //Styling labels and components
        JComponent[] comps = {nameFilter, ageFilter, salaryFilter, nameFilterField, ageFilterField, salaryLabel};

        for (JComponent comp : comps) {
            comp.setForeground(Colors.PRIMARY_COLOR.getColor());
            comp.setFont(new Font("Times New Roman", Font.BOLD, 20));
            comp.setPreferredSize(new Dimension(200, 40));
        }

        //Mounting to Page
        filtersHolder.add(nameFilter);
        filtersHolder.add(nameFilterField);
        filtersHolder.add(new Spacer(200, 50));
        filtersHolder.add(ageFilter);
        filtersHolder.add(ageFilterField);
        filtersHolder.add(new Spacer(200, 50));
        filtersHolder.add(salaryFilter);
        filtersHolder.add(salaryFilterSlider);
        filtersHolder.add(new Spacer(200, 50));
        filtersHolder.add(salaryLabel);

        return filtersHolder;
    }
}
