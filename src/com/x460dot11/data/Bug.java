package com.x460dot11.data;

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
    String priority;
    String dueDate;
    String assignee;
    String summary;
    String description;
    String finalResult;

    public Bug(String bugID, String priority, String dueDate, String assignee,
               String summary, String description) {
        this.bugID = Integer.parseInt(bugID);
        this.priority = priority;
        this.dueDate = dueDate;
        this.assignee = assignee;
        this.summary = summary;
        this.description = description;
    }

    public Bug() {
    }


    public int getBugID() {
        return bugID;
    }

    public void setBugID(int bugID) {
        this.bugID = bugID;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
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
