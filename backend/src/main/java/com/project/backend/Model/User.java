package com.project.backend.Model;

public class User{
    private String name;
    private String surname;
    private String role;
    private String email;
    private String password;

    private boolean isActive;

    public User(String name, String surname, String role, String email, String password, boolean isActive) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
    }

    public User(String name, String surname, String email) { //constructor used for the lectures in the app
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
