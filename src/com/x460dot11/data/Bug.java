package com.x460dot11.data;

import org.joda.time.LocalDate;

public class Bug {
  private int bug_id;
  private LocalDate due_date;
  private LocalDate close_date;
  private String assignee;
  private int priority;
  private String summary;
  private String history;
  private String final_result;

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

  public LocalDate getDue_date() {
    return due_date;
  }

  public void setDue_date(LocalDate due_date) {
    this.due_date = due_date;
  }

  public LocalDate getClose_date() {
    return close_date;
  }

  public void setClose_date(LocalDate close_date) {
    this.close_date = close_date;
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
