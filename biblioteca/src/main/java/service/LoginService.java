package service;


import javax.servlet.http.HttpServletRequest;

import model.Prestito;
import model.Utente;
import utilities.Eccezione;

public interface LoginService {

	String login(HttpServletRequest request, Utente utente, String password) throws Eccezione;

	void passwordDimenticata(HttpServletRequest request, Utente utente) throws Eccezione;

	void nuovaPassword(HttpServletRequest request, Utente utente);

}
