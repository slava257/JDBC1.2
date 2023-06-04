package dao;

import model.City;

import java.util.List;

public interface CityDAO {
    City addCity(City city);

    City findByIdCity(Integer cityId);

    List<City> fullFindByEmployee();

    City toChangeCity(City city);

    void delete(City city);
}
