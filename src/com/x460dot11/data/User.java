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


    /**
     * Initialize User object
     * @param username - user name (i.e. John Doe)
     * @param role - role (i.e. manager)
     */
    
    
    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }
    /**
     * Initialize User object
     * Default username is "John Doe", and role is "manager"
     */
    public User() {
    }
    /**
     * Return User name
     * @return user name
     */
    public String getUsername() {
        return username;
    }
    /**
     * Return User role
     * @return role
     */
    public String getRole() {
        return role;
    }
}
