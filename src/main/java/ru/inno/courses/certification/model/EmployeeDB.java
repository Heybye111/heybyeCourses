package ru.inno.courses.certification.model;

import java.util.Objects;

public class EmployeeDB {
    private int id;
    private boolean active;
    private String createTimestamp;
    private String changeTimestamp;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
    private String email;
    private String birthdate;
    private String avatar_url;
    private int companyId;

    public int getCompanyId() {
        return companyId;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getBirthdate() {
        return birthdate;
    }
    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getChangeTimestamp() {
        return changeTimestamp;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public boolean isActive() {
        return active;
    }

    public int getId() {
        return id;
    }

    public EmployeeDB(int id, boolean active, String createTimestamp, String changeTimestamp, String firstName, String lastName,
                      String middleName, String phone,String email, String birthdate, String avatar_url, int companyId) {
        this.id = id;
        this.active = active;
        this.createTimestamp = createTimestamp;
        this.changeTimestamp = changeTimestamp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phone = phone;
        this.email = email;
        this.birthdate = birthdate;
        this.avatar_url = avatar_url;
        this.companyId = companyId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDB that = (EmployeeDB) o;
        return id == that.id &&
                active == that.active &&
                companyId == that.companyId &&
                Objects.equals(createTimestamp, that.createTimestamp) &&
                Objects.equals(changeTimestamp, that.changeTimestamp) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(birthdate, that.birthdate) &&
                Objects.equals(avatar_url, that.avatar_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, active, createTimestamp, changeTimestamp, firstName, lastName, middleName, phone, email, birthdate, avatar_url, companyId);
    }

    @Override
    public String toString() {
        return "EmployeeDB{" +
                "id=" + id +
                ", active=" + active +
                ", createTimestamp='" + createTimestamp + '\'' +
                ", changeTimestamp='" + changeTimestamp + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phone='" + phone + '\'' +
                ", phone='" + email + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", avatarUrl='" + avatar_url + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}