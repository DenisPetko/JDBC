package service;
import model.Employee;
import java.util.List;

public interface EmployeeDAO {

    void addEmployee(Employee employee);

    Employee getEmployeeByID(int id);

    List<Employee> getAllEmployees();

    void updateEmployeeById(int id, Employee employee);

    void deleteEmployee(int id);

}
