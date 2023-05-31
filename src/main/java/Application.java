import dao.EmployeeDAOImpl;
import dao.HibernateSessionFactoryUtil;
import model.City;
import model.Employee;
import model.Resume;

import java.sql.*;

import java.util.Scanner;

//### Задание 1
//
//1. Создать maven проект с архетипом org.apache.maven.archetypes:maven-archetype-webapp.
//2. Добавить зависимость postgresql с сайта https://mvnrepository.com/ и плагин maven-compiler-plugin
// в pom.xml файл (пример файла pom.xml ниже).
//3. Создать класс Application и настроить в нем подключение к созданной ранее базе данных skypro.
//4. Получить и вывести в консоль полные данные об одном из работников (имя, фамилия, пол, город)
// данные получить по id.
public class Application {
    private static final EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    public Employee findById1(Integer id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Employee.class, id);
    }


    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        localizeResume(sc);

    }


    public static String exceptionString(String message, Scanner scanner) {
        while (true) {

            System.out.println(message);
            String exceptionString = scanner.nextLine();
            if (exceptionString == null || exceptionString.isBlank()) {
                System.out.println("неверное значение ");
            } else {
                return exceptionString;
            }

        }
    }

    private static void fullEmployee() throws SQLException {
        System.out.println("Все сотрудники");
        for (Employee employee : employeeDAO.fullFindByEmployee()) {
            System.out.println(employee);
        }
    }

    private static void findById(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Выберете сотрудника для получения");
                String idLine = scanner.nextLine();
                int id = Integer.parseInt(idLine);
                System.out.println(employeeDAO.findById(id));

                break;
            } catch (NumberFormatException e) {
                System.out.println("введено не верное значение");
            }
        }
    }

    private static void delete(Scanner scanner) {
        while (true) {
            try {
                fullEmployee();
                System.out.println("Выберете сотрудника для удаления");
                Integer id = Integer.valueOf(exceptionString("id", scanner));
                String firstName = exceptionString("Имя сотрудника", scanner);
                String lastName = exceptionString("Фамилия сотрудника", scanner);
                String gender = exceptionString("пол", scanner);
                int age = Integer.parseInt(exceptionString("Возраст", scanner));
                int cityId = Integer.parseInt(exceptionString("номер города", scanner));
                Employee employee1 = new Employee(id, firstName, lastName, gender, age, cityId);
                employeeDAO.delete(employee1);
                break;
            } catch (NumberFormatException e) {
                System.out.println("введено не верное значение");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void addEmployee(Scanner scanner) {
        String firstName = exceptionString("Имя сотрудника", scanner);
        String lastName = exceptionString("Фамилия сотрудника", scanner);
        String gender = exceptionString("пол", scanner);
        int age = Integer.parseInt(exceptionString("Возраст", scanner));
        int cityId = new City(Integer.parseInt(exceptionString("номер города", scanner)), exceptionString("город", scanner)).getCityId();
        Employee employee = new Employee(null, firstName, lastName, gender, age, cityId);
        employeeDAO.addEmployee(new Employee(null, employee.getFirstName(), employee.getLastName(), employee.getGender(), employee.getAge(), employee.getCityId()));
    }

    private static void receiveEmployee(Scanner scanner) {
        Integer id = Integer.valueOf(exceptionString("id сотрудника", scanner));
        String firstName = exceptionString("Имя сотрудника", scanner);
        String lastName = exceptionString("Фамилия сотрудника", scanner);
        String gender = exceptionString("пол", scanner);
        int age = Integer.parseInt(exceptionString("Возраст", scanner));
        int cityId = Integer.parseInt(exceptionString("Номер города", scanner));
        Employee employee = new Employee(id, firstName, lastName, gender, age, cityId);
        employeeDAO.toChange(employee);
    }

    private static Resume resume(Scanner scanner) {
        while (true) {
            try {
                System.out.println("продолжить работу ?");
                for (Resume resume : Resume.values()) {
                    System.out.println(resume.ordinal() + " " + localizeResume1(resume));
                }
                System.out.println("Введите команду");
                String ordinalLain = scanner.nextLine();
                int ordinal = Integer.parseInt(ordinalLain);
                return Resume.values()[ordinal];
            } catch (NumberFormatException e) {
                System.out.println("Веден не верная команда");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Команда не найден");
            }
        }
    }

    private static String localizeResume1(Resume resume) {
        return switch (resume) {
            case DELETE_EMPLOYEE -> "Удалить сатрудника";
            case ADD_EMPLOYEE -> "Добавить сатрудника";
            case RECEIVE_EMPLOYEE -> "Получить сотрудника";
            case UPDATE_EMPLOYEE -> "Изменить сотрудника";
            case FULL_EMPLOYEE -> "Все сотрудники";
            case EXIT -> "Завершить";
        };
    }

    private static void localizeResume(Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("Введите команду");
            switch (resume(scanner)) {
                case DELETE_EMPLOYEE -> delete(scanner);
                case ADD_EMPLOYEE -> addEmployee(scanner);
                case RECEIVE_EMPLOYEE -> findById(scanner);
                case UPDATE_EMPLOYEE -> receiveEmployee(scanner);
                case FULL_EMPLOYEE -> fullEmployee();
                default -> throw new IllegalArgumentException("пока");
            }
        }
    }
}