package com.x460dot11.model;

import org.joda.time.LocalDate;
import java.io.Serializable;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 11:54 AM
 */
public class Bug implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer bugId;
  private LocalDate dueDate;
  private LocalDate closeDate;
  private String assignee;
  private Integer priority;
  private String summary;
  private String history;
  private String finalResult;

  public Bug () {
    this.bugId = 0;
    this.dueDate = new LocalDate();
    this.closeDate = new LocalDate("1970-01-01");
    this.assignee = "unk";
    this.priority = 0;
    this.summary = "";
    this.history = "";
    this.finalResult = "n/a";
  }

  public Integer getBugId() {
    return bugId;
  }

  public void setBugId(Integer bugId) {
    this.bugId = bugId;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public LocalDate getCloseDate() {
    return closeDate;
  }

  public void setCloseDate(LocalDate closeDate) {
    this.closeDate = closeDate;
  }

  public String getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
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

  public String getFinalResult() {
    return finalResult;
  }

  public void setFinalResult(String finalResult) {
    this.finalResult = finalResult;
  }

  public boolean hasSameValues(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Bug other = (Bug) obj;
    if (!(this.getBugId().equals(other.getBugId())))
      return false;

    if (!(this.getDueDate().equals(other.getDueDate())))
      return false;
    if (!(this.getCloseDate().equals(other.getCloseDate())))
      return false;
    if (!(this.getAssignee().equals(other.getAssignee())))
      return false;
    if (!(this.getPriority().equals(other.getPriority())))
      return false;
    if (!(this.getSummary().equals(other.getSummary())))
      return false;
    if (!(this.getHistory().equals(other.getHistory())))
      return false;
    if (!(this.getFinalResult().equals(other.getFinalResult())))
      return false;

      return true;
  }

  @Override
  public int hashCode() {
    if (bugId == null)
      return super.hashCode();
    else
      return this.getClass().hashCode() + bugId.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Bug other = (Bug) obj;
    return this.getBugId().equals(other.getBugId());
  }

  @Override
  public String toString() {
    return String.format("Bug[id=%d,dueDate=%s,closeDate=%s,assignee=%s,priority=%d,summary=%s,history=%s,finalResult=%s",
        bugId,dueDate,closeDate,assignee,priority,summary,history, finalResult);
  }
}