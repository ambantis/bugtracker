package com.x460dot11.model;

import com.x460dot11.model.Bug;

import java.util.ArrayList;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 11:48 AM
 */
public interface BugDao {
  public Bug find(Integer bugId) throws DaoException;

  public ArrayList<Bug> list() throws DaoException;

  public ArrayList<Bug> list(String assignee) throws DaoException;

  public void create(Bug bug, User user) throws IllegalArgumentException, DaoException;

  public void update(Bug bugOld, Bug bugNew, User user) throws IllegalArgumentException, DaoException;

  public void delete(Bug bug, User user) throws IllegalArgumentException, DaoException;
}
