import model.Employee;
import service.EmployeeDAO;
import service.impl.EmployeeDAOImpl;

import java.sql.*;

public class Application {

    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "DenP7641";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)")) {
            statement.setInt(1, 6);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Имя: " + resultSet.getString("first_name"));
                System.out.println("Фамилия: " + resultSet.getString("last_name"));
                System.out.println("Пол: " + resultSet.getString("gender"));
                System.out.println("Возраст " + resultSet.getInt(5));
            }
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);
            Employee employee = new Employee(15, "Дмитрий", "Бизин", "М", 30);
            employeeDAO.addEmployee(employee);
            System.out.println(employeeDAO.getEmployeeByID(15));
            System.out.println(employeeDAO.getAllEmployees());
            employeeDAO.updateEmployee(1, "Арнольд", "Шварцнеггер", "M", 55);
            employeeDAO.deleteEmployee(1);
        }
    }
}
