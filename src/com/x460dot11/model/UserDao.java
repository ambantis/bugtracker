package com.x460dot11.model;

import java.util.ArrayList;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 5:14 PM
 */
public interface UserDao {

  public User find(String userId) throws DaoException;

  public User find(String userId, String password) throws DaoException;

  public ArrayList<String> developers() throws DaoException;

  public ArrayList<User> list() throws DaoException;

  public void create(User user) throws IllegalArgumentException, DaoException;

  public void update(User user) throws IllegalArgumentException, DaoException;

  public void delete(User user) throws DaoException;

  public boolean existUserId(String userId) throws DaoException;

  public void changePassword(User user) throws DaoException;
}
