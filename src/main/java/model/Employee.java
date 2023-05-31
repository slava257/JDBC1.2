package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
//### Задание 1
//
//1. Добавьте зависимость `hibernate-core` версии `[5.6.14.Final](http://5.6.14.Final)` в файл pom.xml
//2. Замените в классе Employee поле city с типа City на тип int.
//3. Приведите класс Employee ко всем критериям Entity. Используйте все необходимые аннотации.
//4. Создайте конфигурационный класс для соединения с БД.
//5. Создайте конфигурационный файл hibernate.cfg.xml
//6. Скорректируйте в интерфейсе EmployeeDAO методы удаления и изменения, они должны принимать объект типа
// Employee.
//7. Измените методы класса EmployeeDAOImpl для реализации через Hibernate.
//8. Измените класс Application для реализации через Hibernate.

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String gender;
    private int age;

    @Column(name = "city_id")
    private int cityId;


    public static Employee findById(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setGender(resultSet.getString("gender"));
        employee.setAge(resultSet.getInt("age"));
        employee.setCityId(new City(resultSet.getInt("city_id"), resultSet.getString("city_name")).getCity_id());
        return employee;
    }
}