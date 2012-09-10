package com.x460dot11.mail;


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
    String host = "smtp.gmail.com";
    Properties properties;

    private static Gmail gmail = null;
    private final String userName = "bugtrakker@gmail.com";
    private final String password = "x460dot11";

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

    public void sendMessage()
            throws MessagingException {

        Session session = Session.getInstance(properties, null);



        MimeMessage message = new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("ambantis@gmail.com,a.bantis@lausd.net"));
        message.setSubject("Testing Subject");
        message.setText("Dear Developer, this is a test to make sure that email works");
        Transport transport = session.getTransport("smtps");
        try {
            transport.connect(host, userName, password);
            transport.sendMessage(message, message.getAllRecipients());
        } finally {
            transport.close();
        }
    }
}
