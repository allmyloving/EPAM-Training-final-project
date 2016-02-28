package ua.nure.serdyuk.SummaryTask4.util;

import java.io.IOException;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.AndTerm;
import javax.mail.search.BodyTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.FromTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import org.apache.log4j.Logger;

import com.sun.mail.imap.IMAPFolder;

public class MailSender {

	private static final Logger LOG = Logger.getLogger(MailSender.class);

	private static final String SERVICE_NAME = "ST4 Ticket service";

	private static final String DELIVERY_SUBSYSTEM = "mailer-daemon@googlemail.com";

	private static final String SEND_FAILED_REGEX = "(?s).*failed.*%s.*";

	private static final String EMAIL;

	private static final String PASSWORD;

	// private static final Session SESSION;

	static {
		// SESSION = getSession();
		EMAIL = System.getenv("myemail");
		PASSWORD = System.getenv("mypassword");

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

	public void sendConfirmation(String userEmail, String url) {
		try {
			Message message = new MimeMessage(getSession(getWriteProps()));
			message.setFrom(new InternetAddress(EMAIL, SERVICE_NAME));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(userEmail));
			LOG.info("Trying to send email to " + userEmail);

			message.setSubject("Confirm registration");
			message.setText(String.format(
					"Please confirm tour registration by clicking the following link %s",
					url));
			
			Transport.send(message);
		} catch (MessagingException | IOException e) {
			LOG.error(String.format("Error while sending mail: %s",
					e.getMessage()));
		}

	}

	public boolean sendMail(String userEmail) {
		try {
			Message message = new MimeMessage(getSession(getWriteProps()));
			message.setFrom(new InternetAddress(EMAIL, SERVICE_NAME));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(userEmail));
			LOG.info("Trying to send email to " + userEmail);

			message.setSubject("You have tickets bought for tomorrow");
			message.setText("HI!!!");

			synchronized (this) {
				Transport.send(message);
			}
			return true;
		} catch (Exception e) {
			LOG.error(String.format("Error while sending mail: %s",
					e.getMessage()));
			return false;
		}

	}

	public void sendMail(String userEmail, String fileName)
			throws AddressException, MessagingException, IOException {

		MimeBodyPart attachment = new MimeBodyPart();
		attachment.attachFile(fileName);
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(attachment);

		Message message = new MimeMessage(getSession(getWriteProps()));
		message.addHeader("Content-type", "text/HTML; charset=UTF-8");
		message.addHeader("format", "flowed");
		message.addHeader("Content-Transfer-Encoding", "8bit");

		message.setFrom(new InternetAddress(EMAIL, SERVICE_NAME));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(userEmail));
		LOG.info("Trying to send email to " + userEmail);

		message.setSubject("Your tickets");
		message.setContent(multipart);

		Transport.send(message);
	}

	private synchronized Session getSession(Properties props) {
		return Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL, PASSWORD);
			}
		});
	}

	private static Properties getWriteProps() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		return props;
	}

	private static Properties getReadProps() {
		Properties props = new Properties();
		props.put("mail.pop3.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.pop3.socketFactory.fallback", "false");
		props.put("mail.pop3.port", "995");
		props.put("mail.pop3.socketFactory.port", "995");
		props.put("mail.pop3.host", "pop.gmail.com");
		return props;
	}

}
