package com.ambantis.bugtracker.model;

import java.io.Serializable;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 10:35 AM
 */
public class User implements Serializable {

  // Constants-------------------------------------------------------------------------------------
  private static final long serialVersionUID = 1L;

  // Properties------------------------------------------------------------------------------------
  private String userId;
  private String password;
  private String fullName;
  private String roleId;
  private String email;

  // Getters/setters-------------------------------------------------------------------------------
  public User() {
    this.userId = "";
    this.password = "";
    this.fullName = "";
    this.roleId = "";
    this.email = "";
  }

  // Getters/setters-------------------------------------------------------------------------------
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  // Object overrides -----------------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    return this.getUserId().equals(other.getUserId());
  }

  @Override
  public int hashCode() {
    if (userId == null)
      return super.hashCode();
    else
      return this.getClass().hashCode() + userId.hashCode();
  }

  @Override
  public String toString() {
    return String.format("User[id=%s,email=%s,fullName=%s,roleId=%s,email=%s]",
        userId, email, fullName, roleId, email);
  }
}