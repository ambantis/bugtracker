package com.ambantis.btracker.model;

import java.util.ArrayList;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 11:48 AM
 */
public interface BugDao {
  public Bug find(Integer bugId) throws DaoException;

  public ArrayList<Bug> listAll() throws DaoException;

  public ArrayList<Bug> listAll(String assignee) throws DaoException;

  public ArrayList<Bug> listOpen() throws DaoException;

  public ArrayList<Bug> listOpen(String assignee) throws DaoException;

  public ArrayList<Bug> listClosed() throws DaoException;

  public ArrayList<Bug> listClosed(String assignee) throws DaoException;

  public void create(Bug bug, User user) throws IllegalArgumentException, DaoException;

  public void update(Bug bugOld, Bug bugNew, User user) throws IllegalArgumentException, DaoException;

  public void delete(Bug bug, User user) throws IllegalArgumentException, DaoException;
}
