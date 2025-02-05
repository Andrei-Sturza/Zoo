package org.example;

public class User {
    private String username;
    private String password; // In a real app, store hashed passwords

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean validatePassword(String inputPassword) {
        // In real applications, compare hashed passwords
        return this.password.equals(inputPassword);
    }
}
