package com.x460dot11.tracker;

import com.x460dot11.data.BugDAO;
import com.x460dot11.data.DAOFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandros Bantis
 * Date: 8/28/12
 * Time: 10:06 PM
 */
public class TransactionNewBug extends Transaction {
    private String summary;
    private String description;

    public TransactionNewBug (String summary, String description) {
        this.summary = summary;
        this.description = description;

        if (this.summary == null)
            this.summary = "summary was null";
        if (this.description == null)
            this.description = "description was null";
        createBug();
    }

    private boolean createBug() {
        boolean success;
        DAOFactory postgresDAOFactory = DAOFactory.getDAOFactory();
        BugDAO bugDAO = postgresDAOFactory.getBugDAO();
        success = bugDAO.newBug(summary, description);
        return success;
    }
}