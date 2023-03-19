import model.City;
import model.Employee;
import service.CityDao;
import service.EmployeeDAO;
import service.impl.CityDaoImpl;
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
            EmployeeDAO employeeDAO = new EmployeeDAOImpl();
            CityDao cityDao = new CityDaoImpl();

            City city = new City(27, "Краснодар");
            cityDao.addCity(city);
            Employee someEmployee = new Employee("Дмитрий", "Рязанский", "М", 20, city);
            employeeDAO.addEmployee(someEmployee);

            System.out.println(employeeDAO.getEmployeeByID(5));

            for (Employee employee : employeeDAO.getAllEmployees()) {
                System.out.println(employee);
            }

            City cityLA = new City(28, "LA");
            cityDao.addCity(cityLA);
            Employee employeeArni = new Employee("Арнольд", "Шварцнеггер", "M", 55, cityLA);
            employeeDAO.updateEmployeeById(6, employeeArni);

            employeeDAO.deleteEmployee(35);

        }
    }
}
