package com.x460dot11.data;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandros Bantis
 * Date: 8/28/12
 * Time: 8:18 PM
 */
public interface BugDAO {
    public boolean newBug(String summary, String description);
    public boolean assignBug(int bugID, String user);
    public boolean updateBug(String due_date, int priority, String summary, String description);
}
