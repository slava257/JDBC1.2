package dao;

import lombok.NoArgsConstructor;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;


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
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();

        }
    }

    @Override
    public Employee findById(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        }
    }

    @Override
    public List<Employee> fullFindByEmployee() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employee").list();
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
    public void delete(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();

        }

    }
}
