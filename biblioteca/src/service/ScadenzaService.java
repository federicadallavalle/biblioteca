package service;

import java.util.List;

import model.Utente;
import utilities.Eccezione;

public interface ScadenzaService {
	/**
	 * Recupera dal db una lista di utenti che hanno ricevuto libri in prestito
	 * e non li hanno restituiti entro 30 giorni
	 * 
	 * @return la lista di tutti gli utenti che hanno scadenze
	 * 
	 * @throws Eccezione raccoglie eccezioni di vario tipo, estende Exception
	 */
	List<Utente> listaUtentiScadenze() throws Eccezione;
}
