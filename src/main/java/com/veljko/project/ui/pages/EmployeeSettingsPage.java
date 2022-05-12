package com.veljko.project.ui.pages;

import com.veljko.project.ui.components.ActionButton;
import com.veljko.project.ui.components.HeadingLabel;
import com.veljko.project.database.EmployeeDAO;
import com.veljko.project.database.model.Employee;
import com.veljko.project.ui.services.Colors;
import com.veljko.project.ui.services.Spacer;
import com.veljko.project.ui.services.Validator;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.veljko.project.ui.services.Validator.*;


public class EmployeeSettingsPage extends JPanel {

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField addressField;
    private JTextField salaryField;
    private ActionButton addEmployee;
    private ActionButton deleteEmployee;
    private ActionButton editEmployee;

    public EmployeeSettingsPage() {
        this.setName("EmployeeSettingsPage");
        this.add(new HeadingLabel("Manage employees", Colors.PRIMARY_COLOR.getColor()));

        initFields();

        JPanel form = createForm();
        Spacer spacer = new Spacer(100, 50);
        JPanel buttonsHolder = createActionButtons();


        autoFillFieldsByID();

        this.add(form);
        this.add(spacer);
        this.add(buttonsHolder);
    }

    private void initFields() {
        salaryField = new JTextField();
        addressField = new JTextField();
        ageField = new JTextField();
        nameField = new JTextField();
        idField = new JTextField();
    }

    private void handleSubmit(ActionButton btn) {
        JTextField[] fieldsToCheck = {idField, nameField, addressField, ageField, salaryField};
        JTextField[] textLengthFields = {nameField, addressField};

        btn.addActionListener(e -> {

            switch (btn.getActionCommand()) {
                case "add-employee" -> {

                    if (validateFields(fieldsToCheck, "You must fill up all fields.", "Add Employee") &&
                            Validator.validateTextLength(textLengthFields)) {

                        if (validateIfNumber(new JTextField[]{idField, ageField, salaryField})) {
                            EmployeeDAO.addNewEmployee(createEmployee());
                            clearAllFields();
                        }
                    }
                }

                case "delete-employee" -> {
                    if (validateFields(new JTextField[]{idField}, "You must fill up ID field.", "Delete Employee")) {
                        if (validateIfNumber(new JTextField[]{idField})) {
                            int id = Integer.parseInt(idField.getText());
                            EmployeeDAO.deleteEmployee(id);
                            clearAllFields();
                        }
                    }
                }

                case "edit-employee" -> {
                    if (validateFields(fieldsToCheck, "You must fill up all fields.", "Edit employee") &&
                            Validator.validateTextLength(textLengthFields)) {
                        if (validateIfNumber(new JTextField[]{idField, ageField, salaryField})) {
                            int id = Integer.parseInt(idField.getText());
                            EmployeeDAO.editEmployee(id, createEmployee());
                            clearAllFields();
                        }
                    }
                }

                default -> System.out.println("error");
            }
        });
    }

    private void autoFillFieldsByID() {
        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    int num = Integer.parseInt(idField.getText());

                    Employee employee = EmployeeDAO.getEmployeeById(num);

                    if (employee != null) {
                        addEmployee.setEnabled(false);
                        editEmployee.setEnabled(true);
                        deleteEmployee.setEnabled(true);
                        ActionButton.changeButtonsState(new JButton[]{addEmployee, editEmployee, deleteEmployee});

                        nameField.setText(employee.getName());
                        addressField.setText(employee.getAddress());
                        ageField.setText(String.valueOf(employee.getAge()));
                        salaryField.setText(String.valueOf(employee.getSalary()));

                        JOptionPane.showMessageDialog(null, "We found employee with that ID.", "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        addEmployee.setEnabled(true);
                        editEmployee.setEnabled(false);
                        deleteEmployee.setEnabled(false);
                        ActionButton.changeButtonsState(new JButton[]{addEmployee, editEmployee, deleteEmployee});
                    }
                } catch (NumberFormatException ignored) {

                }
            }
        });
    }

    private JPanel createActionButtons() {
        //Action Buttons
        JPanel buttonsHolder = new JPanel();
        buttonsHolder.setLayout(new GridLayout(3, 1, 0, 45));

        addEmployee = new ActionButton("src/main/java/com/veljko/project/icons/add_employee.png", "add-employee");
        deleteEmployee = new ActionButton("src/main/java/com/veljko/project/icons/delete_employee.png", "delete-employee");
        editEmployee = new ActionButton("src/main/java/com/veljko/project/icons/edit_employee.png", "edit-employee");

        ActionButton[] buttons = {addEmployee, deleteEmployee, editEmployee};

        for (ActionButton btn : buttons) {
            ActionButton.addHoverEffect(btn);
            buttonsHolder.add(btn);

            handleSubmit(btn);
        }

        return buttonsHolder;
    }

    private Employee createEmployee() {
        int id = Integer.parseInt(idField.getText());
        int age = Integer.parseInt(ageField.getText());
        int salary = Integer.parseInt(salaryField.getText());
        return new Employee(id, nameField.getText(), addressField.getText(), age, salary);
    }

    private JPanel createForm() {
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        idField.setName("ID Field");
        nameField.setName("Name Field");
        addressField.setName("Address Field");
        ageField.setName("Age Field");
        salaryField.setName("Salary Field");

        //Labels
        JLabel idLabel = new JLabel("ID:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel addressLabel = new JLabel("Address:");
        JLabel salaryLabel = new JLabel("Salary:");


        //Styling labels and components
        JComponent[] comps = {idLabel, idField, nameLabel, nameField, ageLabel, ageField, addressLabel, addressField, salaryLabel, salaryField};

        for (JComponent comp : comps) {
            comp.setForeground(Colors.PRIMARY_COLOR.getColor());
            comp.setFont(new Font("Times New Roman", Font.BOLD, 20));
            comp.setPreferredSize(new Dimension(200, 40));
            form.add(comp);
        }

        return form;
    }

    private void clearAllFields() {
        JTextField[] fields = {idField, nameField, ageField, addressField, salaryField};

        for (JTextField field : fields) {
            field.setText("");
        }
    }

}
