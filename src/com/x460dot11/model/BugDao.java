package com.x460dot11.model;

import com.x460dot11.model.Bug;
import java.util.List;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 11:48 AM
 */
public interface BugDao {
  public Bug find(Integer bugId) throws DaoException;

  public List<Bug> list() throws DaoException;

  public List<Bug> list(String assignee) throws DaoException;

  public void create(Bug bug) throws IllegalArgumentException, DaoException;

  public void update(Bug bug) throws IllegalArgumentException, DaoException;

  public void delete(Bug bug) throws DaoException;


}
