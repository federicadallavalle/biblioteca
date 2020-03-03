package serviceimpl;

import javax.servlet.http.HttpServletRequest;

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
	public void login(HttpServletRequest request, String username, String password) throws Eccezione {
		// TODO Auto-generated method stub

	}

	@Override
	public void passwordDimenticata(HttpServletRequest request, String email) throws Eccezione {
		int lunghezzaPassword = 7;
		// recupero l'indirizzo email dell'utente nel campo di input
		// genero la password e la inserisco nel documento da inviare all'indirizzo email dell'utente
		String nuovaPassword = RandomPassword.getPassword(lunghezzaPassword);
		// inserisco all'interno del documento anche il link che reindirizza alla pagina di nuova password
	}

	@Override
	public void nuovaPassword(HttpServletRequest request, String email, String password) {
		// TODO Auto-generated method stub

	}

}
