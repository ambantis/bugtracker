package com.x460dot11.data;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandros Bantis
 * Date: 8/28/12
 * Time: 8:14 PM
 */
public abstract class DAOFactory {
    public abstract BugDAO getBugDAO();




    public static PostgresDAOFactory getDAOFactory() {
        return new PostgresDAOFactory();
    }






}
