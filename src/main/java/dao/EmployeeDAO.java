package dao;

import model.Employee;

import java.util.List;


public interface EmployeeDAO {
    //Создание(добавление) сущности Employee в таблицу
    void addEmployee(Employee employee);

    Employee findById(Integer id);

    List<Employee> fullFindByEmployee();

    void toChange(Employee employee);

    void deleteById(Integer id);
}
