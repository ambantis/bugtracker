package com.x460dot11.data;

import org.joda.time.LocalDate;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandros Bantis
 * Date: 8/28/12
 * Time: 9:58 PM.
 */
public class Bug {
    private int bug_id;
    private LocalDate due_date;
    private LocalDate close_date;
    private String assignee;
    private int priority;
    private String summary;
    private String history;
    private String final_result;
    /**
     * Constructor
     * @param bug_id
     * @param due_date 
     * @param assignee
     * @param priority
     * @param summary
     * @param history
     * @param final_result
     * @param is_open
     */
    public Bug(int bug_id, LocalDate due_date, LocalDate close_date, String assignee, int priority,
               String summary, String history, String final_result) {
        this.bug_id = bug_id;
        this.due_date = due_date;
        this.close_date = close_date;
        this.assignee = assignee;
        this.priority = priority;
        this.summary = summary;
        this.history = history;
        this.final_result = final_result;
    }
    /**
     * Constructor
     */
    public Bug() {
    }
    /**
     * 
     * @return Bug ID
     */
    public int getBug_id() {
        return bug_id;
    }
    /**
     * Sets Bug ID
     * @param bug_id
     */
    public void setBug_id(int bug_id) {
        this.bug_id = bug_id;
    }
    /**
     * 
     * @return Bug Priority
     */
    public int getPriority() {
        return priority;
    }
    /**
     * Sets Bug Priority
     * @param priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
    /**
     * 
     * @return Bug due date
     */
    public LocalDate getDue_date() {
        return due_date;
    }
    /**
     * Sets Bug due date
     * @param due_date
     */
    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public LocalDate getClose_date() {
        return close_date;
    }

    public void setClose_date(LocalDate close_date) {
        this.close_date = close_date;
    }
    /**
     * 
     * @return Bug Assignee
     */
    public String getAssignee() {
        return assignee;
    }
    /**
     * Sets Bug Assignee
     * @param assignee
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    /**
     * 
     * @return Bug Summary
     */
    public String getSummary() {
        return summary;
    }
    /**
     * Sets Bug Summary
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
    /**
     * 
     * @return Bug History
     */
    public String getHistory() {
        return history;
    }
    /**
     * Sets Bug History
     * @param history
     */
    public void setHistory(String history) {
        this.history = history;
    }
    /**
     * 
     * @return Bug final status
     */
    public String getFinal_result() {
        return final_result;
    }

    /**
     * Sets Bug final status
     * @param final_result
     */
    public void setFinal_result(String final_result) {
        this.final_result = final_result;
    }

    public boolean hasSameValuesAs(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bug other = (Bug) obj;
        if (this.bug_id != other.getBug_id())
            return false;

        if (this.due_date == null || other.getDue_date() == null) {
            if (this.due_date == null && other.getDue_date() != null)
                return false;
            if (this.due_date != null && other.getDue_date() == null)
                return false;
        } else if (!this.due_date.equals(other.getDue_date())) {
            return false;
        }

        if (this.close_date == null || other.getClose_date() == null) {
            if (this.close_date == null && other.getClose_date() != null)
                return false;
            if (this.close_date != null && other.getClose_date() == null)
                return false;
        } else if (!this.close_date.equals(other.getClose_date())) {
            return false;
        }

        if (!this.assignee.equals(other.getAssignee()))
            return false;
        if (this.priority != other.getPriority())
            return false;
        if (!this.summary.equals(other.getSummary()))
            return false;
        if (!this.history.equals(other.getHistory()))
            return false;
        if (!this.final_result.equals(other.getFinal_result()))
            return false;

        return true;
    }


}
