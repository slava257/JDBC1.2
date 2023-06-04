package model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
//Приведите оба класса (Employee и City) к требованиям Entity.
//2. Свяжите сущности между собой так, чтобы из одного города могло быть несколько сотрудников, а один сотрудник — только из одного города.
//3. Включите полную каскадность.

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "city")
public class City {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "city_id")
    private int cityId;
    @Column(name = "city_name")
    private String cityName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private List<Employee> employees;

    public City(int cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public City(String cityName) {
        this.cityName = cityName;

    }

    @Override
    public String toString() {
        return "City{" + "cityId=" + cityId + ", cityName='" + cityName;
    }
}
