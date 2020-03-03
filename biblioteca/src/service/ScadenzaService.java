package service;

import java.util.List;

import model.Utente;
import utilities.Eccezione;

public interface ScadenzaService {
	List<Utente> listaUtentiScadenze() throws Eccezione;
}
