package com.x460dot11.tracker;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ambantis
 * Date: 8/28/12
 * Time: 9:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class Bug implements Serializable {
    int bugID;
    int priority;
    String user;
    String dueDate;
    String summary;
    String description;
    String finalResult;

    public int getBugID() {
        return bugID;
    }

    public void setBugID(int bugID) {
        this.bugID = bugID;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
    }
}
