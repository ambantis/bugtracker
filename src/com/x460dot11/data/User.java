package com.x460dot11.data;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandros Bantis
 * Date: 8/31/12
 * Time: 8:27 AM
 */
public class User {
    private String username;
    private String role;

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
