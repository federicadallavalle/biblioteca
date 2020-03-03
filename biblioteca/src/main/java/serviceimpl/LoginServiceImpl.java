package serviceimpl;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Utente;
import model.dao.UtenteDao;
import service.LoginService;
import utilities.Eccezione;
import utilities.RandomPassword;

public class LoginServiceImpl implements LoginService {

	private static LoginServiceImpl istance = null;

	private LoginServiceImpl() {
	}

	public static LoginServiceImpl getIstance() {
		if (istance == null)
			istance = new LoginServiceImpl();
		return istance;
	}

	@Override
	public String login(HttpServletRequest request, Utente utente, String password) throws Eccezione {
		String pagina = "";
		// cerca l'utente tramite username e password (recupera una lista di lunghezza 1
		// in quanto l'username � univoco)
		List<Utente> lista = UtenteDao.cercaUtente(utente);
		Utente utenteTrovato = lista.get(0);
		// se la password dell'utente non corrisponde alla password inserita lancia un
		// eccezione
		if (!password.equals(utenteTrovato.getPassword())) {
			throw new Eccezione("Password errata");
		}
		// ora controlla il ruolo dell'utente che effettua l'accesso
		switch (utenteTrovato.getRuolo()) {
		case "iscritto": {
			pagina = "home-pubblica";
			break;
		}
		case "gestore": {
			pagina = "home-privata";
			break;
		}
		case "amministratore": {
			pagina = "home-privata";
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + utenteTrovato.getRuolo());
		}
		return pagina;
	}

	@Override
	public void passwordDimenticata(HttpServletRequest request, Utente utente) throws Eccezione {
		// manuel
		String pagina = "";

		// imposto la lunghezza della nuova password e la creo
		int lunghezzaPassword = 7;
		String nuovaPassword = RandomPassword.getPassword(lunghezzaPassword);

		// recupero la lista di utenti che conterr� solo l'utente cercato per email
		List<Utente> lista = UtenteDao.cercaUtente(utente);
		if (lista.isEmpty()) {
			throw new Eccezione("Email non trovata");
		}

		// Recupero l'email dell'utente trovato e preparo il messaggio
		Utente utenteTrovato = lista.get(0);
		String sendTo = utenteTrovato.getEmail();
		String sendFrom = "libreriavalueson@gmail.com";
		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// prendo l'istanza della sessione, recupero le properties e passo email e
		// password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("libreriavalueson@gmail.com", "Libreria00");

			}
		});

		try {
			// creo il messaggio
			MimeMessage message = new MimeMessage(session);

			// Setta l'header del campo from
			message.setFrom(new InternetAddress(sendFrom));

			// Setta l'header del campo to
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));

			// Setta il campo dell'oggetto
			message.setSubject("Campo dell'oggetto");

			// Setta il messaggio vero e proprio
			message.setText("Hai richiesto la nuova password. La nuova password �" + nuovaPassword);

			System.out.println("sending...");
			// Invia il messaggio
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
