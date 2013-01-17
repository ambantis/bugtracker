package com.ambantis.bugtracker.model;

import java.util.ArrayList;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 5:14 PM
 */
public interface UserDao {

  public User find(String userId) throws DaoException, DaoConnectionException;

  public User find(String userId, String password) throws DaoException, DaoConnectionException;

  public ArrayList<String> developers() throws DaoException, DaoConnectionException;

  public ArrayList<User> list() throws DaoException, DaoConnectionException;

  public void create(User user) throws IllegalArgumentException, DaoException, DaoConnectionException;

  public void update(User user) throws IllegalArgumentException, DaoException, DaoConnectionException;

  public void delete(User user) throws DaoException, DaoConfigurationException, DaoConnectionException;

  public boolean existUserId(String userId) throws DaoException, DaoConnectionException;

  public void changePassword(User user) throws DaoException, DaoConnectionException;
}
