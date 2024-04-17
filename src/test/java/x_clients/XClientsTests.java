package x_clients;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.*;
import ru.inno.courses.certification.XClients;
import ru.inno.courses.certification.db.EmployeeRepository;
import ru.inno.courses.certification.db.EmployeeRepositoryJDBC;
import ru.inno.courses.certification.model.Company;
import ru.inno.courses.certification.model.Employee;
import ru.inno.courses.certification.model.EmployeeDB;
import ru.inno.courses.certification.model.patchedEmployee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class XClientsTests {

    private EmployeeRepository repository;

    static int companyId;


    @BeforeAll
    public static void setup() throws IOException {
        XClients.getToken("bloom", "fire-fairy");

    }

    @BeforeEach
    public void setUp() throws SQLException {
        Company company = new Company("Beta bank");
        companyId = XClients.createNewCompany(company);

        String connectionString = "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet";
        String user = "x_clients_user";
        String pass = "x7ngHjC1h08a85bELNifgKmqZa8KIR40";

        repository = new EmployeeRepositoryJDBC(connectionString, user, pass);

    }

    @Test
    @Tag("API")
    @DisplayName("Проверка создания нового клиента через API")
    void createNewEmployee() {
        Employee employee = new Employee(companyId);
        int newEmployeeId = XClients.createNewEmployee(employee);
        assertNotNull(newEmployeeId);
        assertNotEquals(0, newEmployeeId);
    }

    @Test
    @Tag("DB_Tests")
    @DisplayName("Проверка получения данных о сотруднике по id")
    void getEmployeeInfo() throws SQLException {
        int employeeId = repository.create(true, "Alex", "Polov", "Olegovich",
                "88005553535", "wow@mail.ru", "2021-06-01", "http://photo.ru/asd", companyId);
        Employee employeeInfo = XClients.getEmployeeInfo(employeeId);

        assertEquals(true, employeeInfo.getActive());
        assertEquals("Alex", employeeInfo.getFirstName());
        assertEquals("Olegovich", employeeInfo.getMiddleName());
        assertEquals("http://photo.ru/asd", employeeInfo.getAvatar_url());
        assertEquals("wow@mail.ru", employeeInfo.getEmail());
        assertEquals("Polov", employeeInfo.getLastName());
        assertEquals("88005553535", employeeInfo.getPhone());
        assertEquals("2021-06-01", employeeInfo.getBirthdate());
        assertEquals(companyId, employeeInfo.getCompanyId());

    }

    @Test
    @Tag("API")
    @DisplayName("Проверка получения сотрудников по companyId")
    void listEmployees() {
        Employee employee = new Employee(companyId);
        employee.setLastName("Griffin");
        XClients.createNewEmployee(employee);
        employee.setLastName("Simpson");
        XClients.createNewEmployee(employee);
        List<Employee> list = XClients.getListOfEmployees(companyId);
        assertEquals(2, list.size());
        assertEquals("Griffin", list.get(0).getLastName());
        assertEquals("Simpson", list.get(1).getLastName());

    }
    @Test
    @Tag("API")
    @DisplayName("Проверка получения одного сотрудника по companyId")
    void listOneEmployees() {
        Employee employee = new Employee(companyId);
        employee.setLastName("Griffin");
        XClients.createNewEmployee(employee);
        List<Employee> list = XClients.getListOfEmployees(companyId);
        assertEquals(1, list.size());
        assertEquals("Griffin", list.get(0).getLastName());

    }

    @Test
    @Tag("API")
    @DisplayName("Проверка контракта возможности изменить информацию о сотруднике")
    public void patchEmployee() {
        Employee employee = new Employee(companyId);
        int employeeId = XClients.createNewEmployee(employee);
        String patchEmployeeParams = """
                {
                  "lastName": "changedLastName",
                  "email": "changedEmail@ya.ru",
                  "url": "Changed",
                  "phone": "66666666666",
                  "isActive": false
                }
                """;
        patchedEmployee patchedEmployee = XClients.patchEmployeeInfo(employeeId, patchEmployeeParams);
        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson(patchEmployeeParams, JsonObject.class);
        System.out.println(patchedEmployee);
        assertEquals(jsonObj.get("email").getAsString(), patchedEmployee.getEmail());
        assertEquals(jsonObj.get("url").getAsString(), patchedEmployee.getUrl());
        assertEquals(jsonObj.get("isActive").getAsBoolean(), patchedEmployee.isActive());
        assertEquals(jsonObj.get("lastName").getAsString(), patchedEmployee.getLastName());
        assertEquals(employeeId, patchedEmployee.getId());
        System.out.println(patchedEmployee);

    }

    @Test
    @DisplayName("Проверка изменения полей в БД при вызове изменения информации о сотруднике")
    @Tag("BD_Test")
    public void comparePatchEmployee() throws SQLException {
        Employee employee = new Employee(companyId);
        int employeeId = XClients.createNewEmployee(employee);
        String patchEmployeeParams = """
                {
                  "lastName": "changedLastName",
                  "email": "changedEmail@ya.ru",
                  "url": "Changed",
                  "phone": "66666666666",
                  "isActive": false
                }
                """;
        patchedEmployee patchedEmployee = XClients.patchEmployeeInfo(employeeId, patchEmployeeParams);

        EmployeeDB employeeDB = repository.getById(patchedEmployee.getId());
        assertEquals(patchedEmployee.getId(), employeeDB.getId());
        assertEquals("changedLastName", employeeDB.getLastName()); //метод не возвращает параметр
        assertEquals(patchedEmployee.getEmail(), employeeDB.getEmail());
        assertEquals("66666666666", employeeDB.getPhone()); //метод не возвращает параметр
        assertEquals(patchedEmployee.isActive(), employeeDB.isActive());



    }
    @Test
    @DisplayName("Сохранение firstName в БД при создании клиента")
    @Tag("DB_Tests")
    void compareFirstNameEmployee() throws SQLException {
        Employee employee = new Employee(companyId);
        employee.setFirstName("Bruno");
        int empId = XClients.createNewEmployee(employee);
        EmployeeDB employeeDB = repository.getById(empId);
        assertEquals(employee.getFirstName(), employeeDB.getFirstName());
    }

    @Test
    @DisplayName("Сохранение lastName в БД при создании клиента")
    @Tag("DB_Tests")
    void compareLastNameEmployee() throws SQLException {
        Employee employee = new Employee(companyId);
        employee.setLastName("Tinkoff");
        int empId = XClients.createNewEmployee(employee);
        EmployeeDB employeeDB = repository.getById(empId);
        assertEquals(employee.getLastName(), employeeDB.getLastName());

    }

    @Test
    @DisplayName("Сохранение middleName в БД при создании клиента")
    @Tag("DB_Tests")
    void compareMiddleNameEmployee() throws SQLException {
        Employee employee = new Employee(companyId);
        employee.setMiddleName("Egorovich");
        int empId = XClients.createNewEmployee(employee);
        EmployeeDB employeeDB = repository.getById(empId);
        assertEquals(employee.getMiddleName(), employeeDB.getMiddleName());

    }

    @Test
    @DisplayName("Сохранение phone в БД при создании клиента")
    @Tag("DB_Tests")
    void comparePhoneNameEmployee() throws SQLException {
        Employee employee = new Employee(companyId);
        employee.setPhone("88005553535");
        int empId = XClients.createNewEmployee(employee);
        EmployeeDB employeeDB = repository.getById(empId);
        assertEquals(employee.getPhone(), employeeDB.getPhone());

    }
    @Test
    @DisplayName("Сохранение email в БД при создании клиента")
    @Tag("DB_Tests")
    void compareEmailNameEmployee() throws SQLException {
        Employee employee = new Employee(companyId);
        employee.setEmail("Vasya@mail.ru");
        int empId = XClients.createNewEmployee(employee);
        EmployeeDB employeeDB = repository.getById(empId);
        assertEquals(employee.getEmail(), employeeDB.getEmail());

    }
    @Test
    @DisplayName("Сохранение birthdate в БД при создании клиента")
    @Tag("DB_Tests")
    void compareBirthdayNameEmployee() throws SQLException {
        Employee employee = new Employee(companyId);
        employee.setBirthdate("2001-05-04");
        int empId = XClients.createNewEmployee(employee);
        EmployeeDB employeeDB = repository.getById(empId);
        assertEquals(employee.getBirthdate(), employeeDB.getBirthdate());

    }
    @Test
    @DisplayName("Сохранение avatarUrl в БД при создании клиента")
    @Tag("DB_Tests")
    void compareAvatarUrlNameEmployee() throws SQLException {
        Employee employee = new Employee(companyId);
        employee.setAvatar_url("https://junit.org/");
        int empId = XClients.createNewEmployee(employee);
        EmployeeDB employeeDB = repository.getById(empId);
        assertEquals(employee.getAvatar_url(), employeeDB.getAvatar_url());

    }

}


