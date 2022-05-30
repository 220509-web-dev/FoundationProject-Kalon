package dev.kalon.entities;

import java.sql.Date;

public class appUsers {

    private int userId;
    private String firstName;
    private String lastName;
    private Date birth_date;
    private String email;
    private String username;
    private String statusId;
    private String password;

    public appUsers() {
    }

    public appUsers(int userId, String firstName, String lastName, Date birth_date, String email, String username, String statusId, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth_date = birth_date;
        this.email = email;
        this.username = username;
        this.statusId = statusId;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "appUsers{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birth_date=" + birth_date +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", statusId='" + statusId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}