package com.ambantis.bugtracker.exception;


public class DaoLostUpdateException extends RuntimeException {
  // This is a fault exception, as there should never be a lost update since the Database.editBug
  // method is synchronized. Thus, if this exception is ever thrown, it needs to be logged so
  // that the code error can be addressed.

  public DaoLostUpdateException(String message) {
    super(message);
  }

  public DaoLostUpdateException(String message, Throwable cause) {
    super(message, cause);
  }

}
