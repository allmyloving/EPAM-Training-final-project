package ua.nure.serdyuk.SummaryTask4.util;

import java.io.IOException;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class MailSender {

	private static final Logger LOG = Logger.getLogger(MailSender.class);

	private static final String email;

	private static final String password;

	static {
		email = System.getenv("myemail");
		password = System.getenv("mypassword");

		MailcapCommandMap mc = (MailcapCommandMap) CommandMap
				.getDefaultCommandMap();
		mc.addMailcap(
				"text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		mc.addMailcap(
				"text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		mc.addMailcap(
				"text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		mc.addMailcap(
				"multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		mc.addMailcap(
				"message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822");
	}

	public void sendMail(String userEmail, String fileName)
			throws AddressException, MessagingException, IOException {

		MimeBodyPart attachment = new MimeBodyPart();
		attachment.attachFile(fileName);
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(attachment);

		Message message = new MimeMessage(getSession());
		message.addHeader("Content-type", "text/HTML; charset=UTF-8");
		message.addHeader("format", "flowed");
		message.addHeader("Content-Transfer-Encoding", "8bit");

		message.setFrom(new InternetAddress(email, "ST4 Ticket service"));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(userEmail));
		LOG.info("Trying to send email to " + userEmail);

		message.setSubject("Your tickets");
		message.setContent(multipart);

		Transport.send(message);
	}

	private Session getSession() {
		return Session.getInstance(getProps(), new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});
	}

	private Properties getProps() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		return props;
	}

}
