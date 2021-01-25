package mx.ipn.forms.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

  private String host;
  private String from;
  private String password;
  private String port;

  public Boolean sendEmail(String toEmail, String subject, String body) {
    try {
      Properties props = new Properties();
      props.put("mail.smtp.host", host); // SMTP Host
      props.put("mail.smtp.port", port); // TLS Port
      props.put("mail.smtp.auth", "true"); // enable authentication
      props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

      // create Authenticator object to pass in Session.getInstance argument
      Authenticator auth = new Authenticator() {
        // override the getPasswordAuthentication method
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(from, password);
        }
      };
      Session session = Session.getInstance(props, auth);

      MimeMessage msg = new MimeMessage(session);
      // set message headers
      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
      msg.addHeader("format", "flowed");
      msg.addHeader("Content-Transfer-Encoding", "8bit");
      msg.setFrom(new InternetAddress(from, "Forms-WAD"));
      msg.setReplyTo(InternetAddress.parse(from, false));
      msg.setSubject(subject, "UTF-8");
      msg.setText(body, "UTF-8");
      msg.setSentDate(new Date());

      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
      Transport.send(msg);

      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public Mail(String host, String from, String password, String port) {
    this.host = host;
    this.from = from;
    this.password = password;
    this.port = port;
  }
}
