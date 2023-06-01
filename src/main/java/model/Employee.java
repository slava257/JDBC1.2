package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
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
@Table(name = "Employee")
public class Employee {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private int age;

    @Column(name = "city_id")
    private int cityId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age && cityId == employee.cityId && Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(gender, employee.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, age, cityId);
    }
}