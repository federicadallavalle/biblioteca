package serviceimpl;

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
		if (utenteTrovato.getPassword() != password) {
			throw new Eccezione("Password errata");
		}
		System.out.println("Accesso eseguito");
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

	public void passwordDimenticata(HttpServletRequest request, Utente utente) throws Eccezione {
		int lunghezzaPassword = 7;
		// recupero l'indirizzo email dell'utente nel campo di input
		// genero la password e la inserisco nel documento da inviare all'indirizzo
		// email dell'utente
		String nuovaPassword = RandomPassword.getPassword(lunghezzaPassword);
		// inserisco all'interno del documento anche il link che reindirizza alla pagina
		// di nuova password
	}

	@Override
	public void nuovaPassword(HttpServletRequest request, Utente utente) {
		// TODO Auto-generated method stub
	}
}
