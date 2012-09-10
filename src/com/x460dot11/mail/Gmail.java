package com.x460dot11.mail;


import com.x460dot11.data.Bug;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * User: Alexandros Bantis
 * Date: 9/7/12
 * Time: 12:15 AM
 */
public class Gmail {
    private final static String NEW_LINE = System.getProperty("line.separator");
    String host = "smtp.gmail.com";
    Properties properties;

    private static Gmail gmail = null;
    private final String USER_NAME = "bugtrakker@gmail.com";
    private final String PASSWORD = "x460dot11";

    private Gmail() {}

    public static Gmail getInstance() {
        if (gmail == null)
            gmail = new Gmail();
        return gmail;
    }

    public void init() {
        properties = new Properties();
        properties.put("mail.smtps.auth", "true");
    }

    public void sendHelloMessage(String address, Bug bug) {
        String recipients = address;
        String subject = "BugTracker: Hello Bug ID # " + bug.getBug_id();
        String body = "Thank you for creating a new report for BUG # " + bug.getBug_id() +
                ". Keep up the hard work!" + NEW_LINE + NEW_LINE + "The BugTracker Team";
        try {
            sendMessage(recipients, subject, body);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendUpdateMessage(ArrayList<String> addresses, Bug bug) {
        String recipients = convertToInternetAddressString(addresses);
        String subject = "Status Update on Bug # " + bug.getBug_id();
        String body = "There has been a new status update for " + bug.getBug_id() +
                ". Login to BugTracker to check the details, and keep up the hard work!" +
                NEW_LINE + NEW_LINE + "The Bug Tracker Team";
        try {
            sendMessage(recipients, subject, body);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public void sendByeMessage(String address, Bug bug) {
        String recipients = address;
        String subject = "Bye Bye Bug ID # " + bug.getBug_id();
        String body = "Thank you for creating a new report for BUG # " + bug.getBug_id() +
                ". You've been taken off the case for this one, but keep up the hard work!" +
                NEW_LINE + NEW_LINE + "The BugTracker Team";
        try {
            sendMessage(recipients, subject, body);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendCongratsMessage(ArrayList<String> addresses, Bug bug) {
        String recipients = convertToInternetAddressString(addresses);
        String subject = "Congrats for Killing Bug # " + bug.getBug_id();
        String body = "Thank you for creating a new report for BUG ID " + bug.getBug_id() +
                ". You've been taken off the case for this one, but keep up the hard work!" +
                NEW_LINE + NEW_LINE + "The BugTracker Team";
        try {
            sendMessage(recipients, subject, body);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String recipients, String subject, String body)
            throws MessagingException {

        Session session = Session.getInstance(properties, null);
        MimeMessage message = new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipients));
        message.setSubject(subject);
        message.setText(body);
        Transport transport = session.getTransport("smtps");
        try {
            transport.connect(host, USER_NAME, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
        } finally {
            transport.close();
        }
    }


    private String convertToInternetAddressString(ArrayList<String> addresses) {
        StringBuilder recipients = new StringBuilder();
        for (String address : addresses) {
            recipients.append(address);
            recipients.append(",");
        }
        recipients.deleteCharAt(recipients.toString().lastIndexOf(','));
        return recipients.toString();
    }
}
