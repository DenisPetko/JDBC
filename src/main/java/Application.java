import model.City;
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
            statement.setInt(1, 2);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Имя: " + resultSet.getString("first_name"));
                System.out.println("Фамилия: " + resultSet.getString("last_name"));
                System.out.println("Пол: " + resultSet.getString("gender"));
                System.out.println("Пол: " + resultSet.getInt("age"));
                System.out.println("id Города " + resultSet.getString("city_id"));
            }
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);
            City city = new City(4, "Тюмень");
            employeeDAO.addCity(city);
            Employee employee = new Employee("Дмитрий", "Бизин", "М", 30, city);
            employeeDAO.addEmployee(employee);
//            System.out.println(employeeDAO.getEmployeeByID(15));
//            System.out.println(employeeDAO.getAllEmployees());
//            employeeDAO.updateEmployee(1, "Арнольд", "Шварцнеггер", "M", 55);
//            employeeDAO.deleteEmployee(1);
        }
    }
}
