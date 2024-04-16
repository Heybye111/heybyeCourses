package x_clients;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inno.courses.certification.db.EmployeeRepository;
import ru.inno.courses.certification.db.EmployeeRepositoryJDBC;
import ru.inno.courses.certification.model.EmployeeDB;

import java.sql.SQLException;


public class XClientsBusinessTests {

    private EmployeeRepository repository;


    @BeforeEach
    public void setUp() throws SQLException {
        String connectionString = "jdbc:postgresql://dpg-cn1542en7f5s73fdrigg-a.frankfurt-postgres.render.com/x_clients_xxet";
        String user = "x_clients_user";
        String pass = "x7ngHjC1h08a85bELNifgKmqZa8KIR40";

        repository = new EmployeeRepositoryJDBC(connectionString, user, pass);

    }

    @Test
    void iCanReadFromDB() throws SQLException {
        String employeeId = "1515";
        EmployeeDB employeeDB = repository.getById(4846);
        System.out.println(employeeDB);
    }

    @Test
    void iCanPasteEmployeeInDB() throws SQLException {
        int employeeDB = repository.create("SASHA", "asdasda", "sadsada", true, 1504, "dsadsa", "2012-04-15", "544353453");
        System.out.println(employeeDB);

    }


}
