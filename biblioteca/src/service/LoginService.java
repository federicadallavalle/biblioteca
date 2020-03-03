package service;

import java.util.List;

import model.Prestito;
import utilities.Eccezione;

public interface LoginService {

	void login(String username, String password);

	void passwordDimenticata(String email);

	void nuovaPassword();

}
