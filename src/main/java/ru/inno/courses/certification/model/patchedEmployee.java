package ru.inno.courses.certification.model;

import java.util.Objects;

public class patchedEmployee {
    int id;
    boolean isActive;
    String email;
    String url;
    String lastName;

    public patchedEmployee(int id, boolean isActive, String email, String url) {

        this.id = id;
        this.email = email;
        this.isActive = isActive;
        this.url = url;

    }

    public patchedEmployee(int id, boolean isActive, String email, String url, String lastName) {

        this.id = id;
        this.email = email;
        this.isActive = isActive;
        this.url = url;
        this.lastName = lastName;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        patchedEmployee that = (patchedEmployee) o;
        return id == that.id && isActive == that.isActive && Objects.equals(email, that.email) && Objects.equals(url, that.url) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, email, url, lastName);
    }

    @Override
    public String toString() {
        return "patchedEmployee{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", email='" + email + '\'' +
                ", url='" + url + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
