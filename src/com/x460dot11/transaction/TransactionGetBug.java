package com.x460dot11.transaction;

import com.x460dot11.data.Bug;
import com.x460dot11.data.BugDAO;
import com.x460dot11.data.DAOFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandros Bantis
 * Date: 8/31/12
 * Time: 2:47 PM
 */
public class TransactionGetBug {
    private String id;

    public TransactionGetBug() {
    }

    public Bug doGetBug(String id) {
        DAOFactory postgresDAOFactory = DAOFactory.getDAOFactory();
        BugDAO bugDAO = postgresDAOFactory.getBugDAO();
        return bugDAO.getBug(Integer.parseInt(id));
    }
}
