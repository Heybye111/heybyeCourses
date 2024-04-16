package ru.inno.courses.certification.db;

import ru.inno.courses.certification.model.EmployeeDB;

import java.sql.SQLException;

public interface EmployeeRepository {


    int create(String first_name, String last_name, String middle_name, boolean is_active, int company_id,  String avatar_url, String birthdate, String phone) throws SQLException;

    EmployeeDB getById(int id) throws SQLException;

}
