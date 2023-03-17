package service;
import model.Employee;
import java.util.List;

public interface EmployeeDAO {
    void addEmployee(Employee employee);

    Employee getEmployeeByID(int id);

    List<Employee> getAllEmployees();

    void updateEmployee(int id, String firstName, String lastName, String gender, int age);

    void deleteEmployee(int id);

}
