package service.impl;

import model.Employee;
import service.EmployeeDAO;

import java.sql.*;
import java.util.*;

public class EmployeeDAOImpl implements EmployeeDAO {
    final String user = "postgres";
    final String password = "DenP7641";
    final String url = "jdbc:postgresql://localhost:5432/skypro";
    final Connection connection = DriverManager.getConnection(url, user, password);

    public EmployeeDAOImpl(Connection connection) throws SQLException {
    }

    @Override
    public void addEmployee(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO employee (id, first_name, last_name, gender, age) VALUES ((?), (?), (?), (?), (?))")) {
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getGender());
            statement.setInt(5, employee.getAge());
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEmployeeByID(int id) {
        Employee employee = new Employee();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee WHERE id=(?)")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee.setId(resultSet.getInt("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee(Integer.parseInt(resultSet.getString("id")),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("gender"),
                        Integer.parseInt(resultSet.getString("age")));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public void updateEmployee(int id, String firstName, String lastName, String gender, int age) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE employee SET first_name=(?), last_name=(?), gender=(?), age=(?) WHERE book_id=(?)")) {
            statement.setInt(1, id);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, gender);
            statement.setInt(5, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM employee WHERE id=(?)")) {
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


