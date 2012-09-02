package com.x460dot11.transaction;

import com.x460dot11.data.Bug;
import com.x460dot11.data.BugDAO;
import com.x460dot11.data.DAOFactory;

/**
 * User: Alexandros Bantis
 * Date: 9/1/12
 * Time: 1:32 PM
 */
public class TransactionEditBug extends Transaction {
    Bug bug;


    public TransactionEditBug() {
    }

    public boolean doEditBug (Bug bug) {
        this.bug = bug;
        DAOFactory postgresDAOFactory = DAOFactory.getDAOFactory();
        BugDAO bugDAO = postgresDAOFactory.getBugDAO();
        return bugDAO.editBug(bug);
    }
}
