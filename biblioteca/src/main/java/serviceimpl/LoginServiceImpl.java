package serviceimpl;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Utente;
import model.dao.UtenteDao;
import service.LoginService;
import utilities.Eccezione;
import utilities.MessageSender;
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
	public String passwordDimenticata(HttpServletRequest request, Utente utente, String email) throws Eccezione {
		// manuel
		String pagina = "";

		// Imposto la lunghezza della nuova password e la creo
		int lunghezzaPassword = 7;
		String nuovaPassword = RandomPassword.getPassword(lunghezzaPassword);

		// Recupero la lista di utenti che conterr� solo l'utente cercato per email
		List<Utente> lista = UtenteDao.cercaUtente(utente);
		System.out.println(utente);
		if (lista.isEmpty()) {
			throw new Eccezione("Email non trovata");
		}

		// Recupero l'email dell'utente trovato e preparo il messaggio
		// e cambio la password vecchia in quella generata
		Utente utenteTrovato = lista.get(0);
		utenteTrovato.setPassword(nuovaPassword);
		System.out.println(utenteTrovato);
		UtenteDao.modificaUtente(utenteTrovato, utenteTrovato.getId());
		System.out.println(utenteTrovato);
		String sendTo = utenteTrovato.getEmail();
		MessageSender.invioEmail(nuovaPassword, sendTo);
		
		pagina = "password-dimenticata";
		return pagina;
	}

}
