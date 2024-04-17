package ru.inno.courses.certification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties

public class Employee {
    int id;
    Boolean isActive;
    String firstName;
    String lastName;
    String middleName;
    String phone;
    String email;
    String birthdate;
    String avatar_url;
    int companyId;

    public Employee(int companyId) {
        Faker faker = new Faker();
        this.isActive = true;
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.middleName = faker.name().username();
        this.phone = faker.phoneNumber().subscriberNumber();
        this.email = faker.internet().emailAddress();
        this.birthdate = "1998-05-05";
        this.avatar_url = "http://pixels.com/123";
        this.companyId = companyId;
    }


    public int getId() {
        return id;
    }


    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", companyId=" + companyId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && companyId == employee.companyId && Objects.equals(isActive, employee.isActive) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(middleName, employee.middleName) && Objects.equals(phone, employee.phone) && Objects.equals(email, employee.email) && Objects.equals(birthdate, employee.birthdate) && Objects.equals(avatar_url, employee.avatar_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, firstName, lastName, middleName, phone, email, birthdate, avatar_url, companyId);
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }


}