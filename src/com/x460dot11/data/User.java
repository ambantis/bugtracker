package com.x460dot11.data;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandros Bantis
 * Date: 8/31/12
 * Time: 8:27 AM
 */
public class User {
  private String userId;
  private String password;
  private String fullName;
  private String roleId;
  private String email;


  /**
   * Initialize User object
   * @param userId - user name (i.e. John Doe)
   * @param roleId - roleId (i.e. manager)
   */


  public User(String userId, String password, String fullName, String roleId, String email) {
    this.userId = userId;
    this.roleId = roleId;
  }
  /**
   * Initialize User object
   * Default userId is "John Doe", and roleId is "manager"
   */
  public User() {
  }
  /**
   * Return User name
   * @return user name
   */
  public String getUserId() {
    return userId;
  }
  /**
   * Return User roleId
   * @return roleId
   */
  public String getRoleId() {
    return roleId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
