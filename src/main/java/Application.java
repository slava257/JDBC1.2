import dao.CityDAOImpl;

import model.City;

import java.util.List;


//В методе main вызовите CRUD-операции для связанных сущностей.
public class Application {
    // private static final EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
    private static final CityDAOImpl cityDAO = new CityDAOImpl();

    public static void main(String[] args) {
        cityDAO.addCity(new City(0, "Novgorod"));
        cityDAO.delete(cityDAO.findByIdCity(3));
        List<City> list = cityDAO.fullFindByEmployee();
        list.forEach(System.out::println);
        cityDAO.toChangeCity(new City(4, "MSK"));
    }


}