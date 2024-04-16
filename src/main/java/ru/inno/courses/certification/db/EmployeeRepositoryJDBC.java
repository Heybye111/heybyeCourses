package ru.inno.courses.certification.db;

import ru.inno.courses.certification.model.EmployeeDB;

import java.sql.*;

public class EmployeeRepositoryJDBC implements EmployeeRepository {

    private static final String SQL_INSERT_EMPLOYEE = "INSERT INTO employee(\"first_name\", \"last_name\", \"middle_name\", \"is_active\", \"company_id\", \"avatar_url\", \"birthdate\", \"phone\") "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM employee where id = ?";
    private final Connection connection;

    public EmployeeRepositoryJDBC(String connectionString, String user, String pass) throws SQLException {
        this.connection = DriverManager.getConnection(connectionString, user, pass);
    }

    @Override
    public int create(String first_name, String last_name, String middle_name, boolean is_active,
                      int company_id, String avatar_url, String birthdate, String phone) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, first_name);
        statement.setString(2, last_name);
        statement.setString(3, middle_name);
        statement.setBoolean(4, is_active);
        statement.setInt(5, company_id);
        statement.setString(6, avatar_url);
        statement.setDate(7, Date.valueOf(birthdate));
        statement.setString(8, phone);

        statement.executeUpdate();

        ResultSet keys = statement.getGeneratedKeys();
        keys.next();
        return keys.getInt(1);
    }

    @Override
    public EmployeeDB getById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new EmployeeDB(
                    resultSet.getInt("id"),
                    resultSet.getBoolean("is_active"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("middle_name"),
                    resultSet.getString("create_timestamp"),
                    resultSet.getString("change_timestamp"),
                    resultSet.getString("phone"),
                    resultSet.getString("birthdate"),
                    resultSet.getString("avatar_url"),
                    resultSet.getInt("company_id"));
        } else return null;
    }

}
