package service;

import javax.servlet.http.HttpServletRequest;

import model.Prestito;
import model.Utente;
import utilities.Eccezione;

public interface LoginService {
	/**
	 * Effettua il login dell'utente, salva il ruolo in sessione
	 * 
	 * @param request
	 * @param username username dell'utente che esegue il login
	 * @param password password dell'utente che esegue il login
	 * @return la pagina in cui si viene reindirizzati
	 * @throws Eccezione
	 */
	String login(HttpServletRequest request, String username, String password) throws Eccezione;

	/**
	 * Dopo aver ricevuto una richiesta, genera una nuova password per l'utente
	 * 
	 * @param request
	 * @param utente  l'utente di cui deve essere modificata la password
	 * @param email   l'email dell'utente
	 * @return la pagina in cui si viene reindirizzati
	 * @throws Eccezione
	 */
	String passwordDimenticata(HttpServletRequest request, Utente utente, String email) throws Eccezione;

}
