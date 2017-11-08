package ar.edu.uade.ia.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.edu.uade.ia.common.mail.MailServiceHelper;

public class SendMailTLS {

	public static void main(String[] args) {
		
		/*final String username = "uade.ia.g7.2017@gmail.com";
		final String password = "uadeuade";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("uade.ia.g7.2017@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("emmanuel.barbin@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}*/
		
		MailServiceHelper.sendMail("emmanuel.barbin@gmail.com", "hola", "chau");
	}

}
