package com.ambantis.bugtracker.model;

/**
 * User: Alexandros Bantis
 * Date: 1/16/13
 * Time: 9:01 PM
 */
public class DaoConnectionException extends Exception {

  private final static long serialVersionUID = 1L;

  public DaoConnectionException(String message) {
    super(message);
  }

  public DaoConnectionException(Throwable cause) {
    super(cause);
  }

  public DaoConnectionException(String message, Throwable cause) {
    super(message, cause);
  }

}
