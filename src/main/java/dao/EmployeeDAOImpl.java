package dao;

import connect.Connect;
import lombok.NoArgsConstructor;
import model.City;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//1. Создать классы Employee и City с полями, аналогично созданным таблицам.
//2. Создать интерфейс EmployeeDAO.
//3. Создать в интерфейсе методы:
//    1. Создание(добавление) сущности Employee в таблицу
//    2. Получение конкретного объекта Employee по id
//    3. Получение списка всех объектов Employee из базы
//    4. Изменение конкретного объекта Employee в базе по id
//    5. Удаление конкретного объекта Employee из базы по id
//4. Реализовать сервис EmployeeDAO и каждый его метод в отдельном классе.
//5. Проверить корректность работы всех методов в классе Application.
@NoArgsConstructor
public class EmployeeDAOImpl implements EmployeeDAO {

    public void addEmployee(Employee employee) {
        try (PreparedStatement preparedStatement = Connect.getConnection().prepareStatement("INSERT INTO employee(first_name,last_name,gender,age,city_id) VALUES ( ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getGender());
            preparedStatement.setInt(4, employee.getAge());
            preparedStatement.setInt(5, employee.getCityId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee findById(Integer id) {
        try (PreparedStatement preparedStatement = Connect.getConnection().prepareStatement("SELECT * FROM employee INNER JOIN city ON city.city_id = employee.city_id WHERE id = (?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setMaxRows(1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Employee.findById(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> fullFindByEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        try (PreparedStatement preparedStatement = Connect.getConnection().prepareStatement("SELECT * FROM employee INNER JOIN city ON city.city_id = employee.city_id")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeList.add(Employee.findById(resultSet));
            }
            return employeeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void toChange(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }
    }

    @Override
    public void deleteById(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }

    }
}
