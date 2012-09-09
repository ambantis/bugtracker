package com.x460dot11.exception;

/**
 *
 * User: Alexandros Bantis
 * Date: 9/8/12
 * Time: 3:40 PM
 */
public class LostUpdateException extends RuntimeException {
    // This is a fault exception, as there should never be a lost update since the Database.editBug
    // method is synchronized. Thus, if this exception is ever thrown, it needs to be logged so
    // that the code error can be addressed.

    public LostUpdateException(String message) {
        super(message);
    }

}
