package com.ambantis.bugtracker.util;

import org.joda.time.LocalDateTime;

public class Converter {

  public static String formatNewComment(String history, String newComment, String user) {
    newComment = newComment.replace(System.getProperty("line.separator"), "");
    LocalDateTime dateTime = new LocalDateTime();
    StringBuilder comment = new StringBuilder();

    if (history.length() > 0) {
      comment.append(history);
      comment.append(System.getProperty("line.separator"));
      comment.append(System.getProperty("line.separator"));
    }
    comment.append(user.toUpperCase());
    comment.append(":");
    comment.append(dateTime.toString());
    comment.append("  ");
    comment.append(newComment);
    return comment.toString();
  }
}
