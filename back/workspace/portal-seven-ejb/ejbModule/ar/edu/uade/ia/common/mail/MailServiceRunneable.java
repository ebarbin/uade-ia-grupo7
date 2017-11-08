package ar.edu.uade.ia.common.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jboss.logging.Logger;

public class MailServiceRunneable implements Runnable {

	private static Logger LOGGER = Logger.getLogger(MailServiceRunneable.class);
	
	private final String username = "uade.ia.g7.2017@gmail.com";
	private final String password = "uadeuade";
	
	private String to;
	private String subject;
	private String body;
	
	private Session session;
	
	public MailServiceRunneable(String to, String subject, String body) {
		try {
			this.to = to;
			this.subject = subject;
			this.body = body;
			
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			
			this.session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			
		} catch (Exception e) {
			MailServiceRunneable.LOGGER.error("Error al enviar email: " + e.getMessage(), e); 
		}
	}
	
	@Override
	public void run() {
		try {
			Message message = new MimeMessage(this.session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.to));
			message.setSubject(this.subject);
			message.setText(this.body);

			Transport.send(message);

		} catch (Exception e) {
			MailServiceRunneable.LOGGER.error("Error al enviar email: " + e.getMessage(), e); 
		}
	}

}
