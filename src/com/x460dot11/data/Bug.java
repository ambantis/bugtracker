package com.x460dot11.data;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandros Bantis
 * Date: 8/28/12
 * Time: 9:58 PM.
 */
public class Bug {
    private int bug_id;
    private String due_date;
    private String assignee;
    private int priority;
    private String summary;
    private String history;
    private String final_result;
    private boolean is_open;

    public Bug(int bug_id, String due_date, String assignee, int priority, String summary,
               String history, String final_result, boolean is_open) {
        this.bug_id = bug_id;
        this.due_date = due_date;
        this.assignee = assignee;
        this.priority = priority;
        this.summary = summary;
        this.history = history;
        this.final_result = final_result;
        this.is_open = is_open;
    }

    public Bug() {
    }

    public int getBug_id() {
        return bug_id;
    }

    public void setBug_id(int bug_id) {
        this.bug_id = bug_id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
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

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getFinal_result() {
        return final_result;
    }

    public void setFinal_result(String final_result) {
        this.final_result = final_result;
    }

    public boolean isIs_open() {
        return is_open;
    }

    public void setIs_open(boolean is_open) {
        this.is_open = is_open;
    }
}
