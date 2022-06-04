package dev.kalon.entities;

import java.sql.Date;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String username;
    private int statusId;
    private String password;

    public User() {
    }

    public User(String firstName, String lastName, Date birthDate, String email, String username, int statusId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.username = username;
        this.statusId = statusId;
        this.password = password;
    }

    public User(int id, String firstName, String lastName, Date birthDate, String email, String username, int statusId, String password) {
        this(firstName, lastName, birthDate, email, username, statusId, password);
        this.id = id;
    }

    public User(String firstName, String lastName, Date birthDate, String email, String username, int statusId, String password, int id) {
        this(firstName, lastName, birthDate, email, username, statusId, password);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public int getStatusId() {
        return Integer.parseInt(String.valueOf(statusId));
    }

    public void setStatusId(String statusId) {
        this.statusId = Integer.parseInt(statusId);
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
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", statusId='" + statusId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
