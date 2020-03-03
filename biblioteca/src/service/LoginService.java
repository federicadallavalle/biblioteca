package service;


import javax.servlet.http.HttpServletRequest;

import model.Prestito;
import utilities.Eccezione;

public interface LoginService {

	void login(HttpServletRequest request, String username, String password) throws Eccezione;

	void passwordDimenticata(HttpServletRequest request, String email) throws Eccezione;

	void nuovaPassword(HttpServletRequest request, String email, String password);

}
