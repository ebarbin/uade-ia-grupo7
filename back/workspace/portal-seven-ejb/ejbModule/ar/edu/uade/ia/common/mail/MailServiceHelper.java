package ar.edu.uade.ia.common.mail;

public class MailServiceHelper {

	public static void sendMail(String to, String subject, String body) {
		MailServiceRunneable myRunnable = new MailServiceRunneable(to, subject, body); 
		Thread myThread = new Thread(myRunnable);
		myThread.start();
	}
}
