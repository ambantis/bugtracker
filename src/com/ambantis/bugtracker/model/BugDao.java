package com.ambantis.bugtracker.model;

import com.ambantis.bugtracker.exception.DaoConnectionException;
import com.ambantis.bugtracker.exception.DaoException;

import java.util.ArrayList;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 11:48 AM
 */
public interface BugDao {
  public Bug find(Integer bugId) throws DaoException, DaoConnectionException;

  public ArrayList<Bug> listAll() throws DaoException, DaoConnectionException;

  public ArrayList<Bug> listAll(String assignee) throws DaoException, DaoConnectionException;

  public ArrayList<Bug> listOpen() throws DaoException, DaoConnectionException;

  public ArrayList<Bug> listOpen(String assignee) throws DaoException, DaoConnectionException;

  public ArrayList<Bug> listClosed() throws DaoException, DaoConnectionException;

  public ArrayList<Bug> listClosed(String assignee) throws DaoException, DaoConnectionException;

  public void create(Bug bug, User user) throws IllegalArgumentException, DaoException, DaoConnectionException;

  public void update(Bug bugOld, Bug bugNew, User user) throws IllegalArgumentException, DaoException, DaoConnectionException;

  public void delete(Bug bug, User user) throws IllegalArgumentException, DaoException, DaoConnectionException;
}
