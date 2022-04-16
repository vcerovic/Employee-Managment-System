package com.veljko.project.database;

import com.veljko.project.database.model.Employee;

import javax.swing.JOptionPane;
import java.util.List;

import com.veljko.project.database.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeDAO {

    public static List<Employee> getAllEmployees() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        List<Employee> employees = null;

        try {
            tx = session.beginTransaction();

            employees = session.createQuery("from Employee", Employee.class).list();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.close();
        }

        return employees;
    }

    public static void addNewEmployee(Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.persist(employee);

            tx.commit();

            JOptionPane.showMessageDialog(null, "Successfully created new employee, now go back to employees page.", "Information",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Employee with that ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);

            if (tx != null) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            HibernateUtil.close();
        }

    }

    public static void deleteEmployee(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Employee employee = session.get(Employee.class, id);

            if (employee != null) {
                session.remove(employee);
                JOptionPane.showMessageDialog(null, "Successfully deleted employee, now go back to employees page.", "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Employee with that ID doesn't exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }


            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }

            e.printStackTrace();

        } finally {
            HibernateUtil.close();
        }
    }

    public static void editEmployee(int id, Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Employee inEmployee = session.get(Employee.class, id);

            if (inEmployee != null) {
                session.merge(employee);
                JOptionPane.showMessageDialog(null, "Successfully updated employee, now go back to employees page.", "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Employee with that ID doesn't exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }


            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }

            e.printStackTrace();

        } finally {
            HibernateUtil.close();
        }
    }

    public static List<Employee> findEmployeesByFilter(String name, int age, int salary, boolean byName, boolean byAge, boolean bySalary) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Employee> employees = null;

        try {
            tx = session.beginTransaction();

            String nameFilter = byName ? "employee.name = '" + name + "'" : "";
            String ageFilter = byAge ? "employee.age < " + age : "";
            String salaryFilter = bySalary ? "employee.salary < " + salary : "";

            String addQuery;

            if (byName && byAge && bySalary) {
                addQuery = nameFilter + " AND " + ageFilter + " AND " + salaryFilter;
            } else if (byName && byAge) {
                addQuery = nameFilter + " AND " + ageFilter;
            } else if (byName && bySalary) {
                addQuery = nameFilter + " AND " + salaryFilter;
            } else if (byAge && bySalary) {
                addQuery = ageFilter + " AND " + salaryFilter;
            } else if (byName) {
                addQuery = nameFilter;
            } else if (byAge) {
                addQuery = ageFilter;
            } else {
                addQuery = salaryFilter;
            }

            employees = session.createQuery("from Employee as employee where " + addQuery, Employee.class).list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateUtil.close();
        }

        return employees;


    }

    public static Employee getEmployeeById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Employee employee = null;

        try {
            tx = session.beginTransaction();

            employee = session.get(Employee.class, id);

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }

            e.printStackTrace();

        } finally {
            HibernateUtil.close();
        }

        return employee;

    }

}
