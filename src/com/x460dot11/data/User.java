package com.x460dot11.data;


public class User {
  private String userId;
  private String password;
  private String fullName;
  private String roleId;
  private String email;


  public User() {
  }

  public User(String userId, String roleId) {
    this.userId = userId;
    this.roleId = roleId;
  }

  public User(String userId, String password, String fullName, String roleId, String email) {
    this.userId = userId;
    this.password = password;
    this.fullName = fullName;
    this.roleId = roleId;
    this.email = email;
  }


  public String getUserId() {
    return userId;
  }

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
