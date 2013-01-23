package com.ambantis.bugtracker.exception;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 10:59 AM
 */
public class DaoException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DaoException(String message) {
    super(message);
  }

  public DaoException(Throwable cause) {
    super(cause);
  }

  public DaoException(String message, Throwable cause) {
    super(message, cause);
  }
}
