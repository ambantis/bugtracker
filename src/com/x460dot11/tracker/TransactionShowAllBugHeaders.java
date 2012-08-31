package com.x460dot11.tracker;

import com.x460dot11.data.BugDAO;
import com.x460dot11.data.DAOFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandros Bantis
 * Date: 8/31/12
 * Time: 10:39 AM
 */
public class TransactionShowAllBugHeaders extends Transaction {

    public TransactionShowAllBugHeaders() {
    }

    public String processShowAllBugHeadersRequest() {
        boolean success;
        DAOFactory posgresDAOFactory = DAOFactory.getDAOFactory();
        BugDAO bugDAO = posgresDAOFactory.getBugDAO();
        return bugDAO.getAllBugHeaders();
    }
}
