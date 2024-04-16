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
    private String birthdate;
    private String avatar_url;
    private int companyId;

    public EmployeeDB(int id, boolean active, String createTimestamp, String changeTimestamp, String firstName, String lastName,
                      String middleName, String phone, String birthdate, String avatar_url, int companyId) {
        this.id = id;
        this.active = active;
        this.createTimestamp = createTimestamp;
        this.changeTimestamp = changeTimestamp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phone = phone;
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
                Objects.equals(birthdate, that.birthdate) &&
                Objects.equals(avatar_url, that.avatar_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, active, createTimestamp, changeTimestamp, firstName, lastName, middleName, phone, birthdate, avatar_url, companyId);
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
                ", birthdate='" + birthdate + '\'' +
                ", avatarUrl='" + avatar_url + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}