package utilities;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MessageSender {

	public static void invioEmail(String newPass, String emailUtente) {
		String nuovaPassword = newPass;
		String sendTo = emailUtente;
		String sendFrom = "libreriavalueson@gmail.com";
		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Prendo l'istanza della sessione, recupero le properties e passo email e
		// password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("libreriavalueson@gmail.com", "Libreria00");

			}
		});

		try {
			// Creo il messaggio
			MimeMessage message = new MimeMessage(session);

			// Setta l'header del campo from
			message.setFrom(new InternetAddress(sendFrom));

			// Setta l'header del campo to
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));

			// Setta il campo dell'oggetto
			message.setSubject("Campo dell'oggetto");

			// Setta il messaggio vero e proprio
			message.setText("Hai richiesto la nuova password. La nuova password �" + nuovaPassword
					+ " , fai il login su: localhost:8080/login.jsp");

			System.out.println("Inviando il messaggio...");
			// Invia il messaggio
			Transport.send(message);
			System.out.println("Messaggio Inviato");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
