package service;

import java.util.List;

import model.Utente;
import utilities.Eccezione;

public interface UtenteService {
	
	/**
	 * il metodo permette l'inserimento di un utente nel db
	 * @param u: Parametro di tipo utente che contiene i dati che dovranno essere inseriti nel db  
	 * @throws Eccezione: Il metodo lancia un eccezione di tipo Eccezione che estende Exception
	 */
	void createUtente(Utente u) throws Eccezione;
	
	/**
	 * il metodo permette la modifica di un utente presente nel db
	 * @param u: Parametro di tipo utente che contiene i dati che dovranno essere aggiornati nel db  
	 * @param id: Parametro di tipo Long che permette di identificare l'utente da modificare 
	 * @throws Eccezione: Il metodo lancia un eccezione di tipo Eccezione che estende Exception
	 */
	void updateUtente(Utente u, Long id) throws Eccezione;
	
	/**
	 * il metodo permette la rimozione di un utente presente nel db
	 * @param id: Parametro di tipo Long che permette di identificare l'utente da rimuovere
	 * @throws Eccezione: Il metodo lancia un eccezione di tipo Eccezione che estende Exception
	 */
	void deleteUtente(Long id) throws Eccezione;
	
	/**
	 * il metodo permette di cercare uno o piu utenti nel db basandosi sui parametri di ricerca
	 * @param u: Parametro di tipo utente che contiene i parametri di ricerca 
	 * @return Il metodo ritorna una lista di utenti che conterra il risultato della ricerca
	 * @throws Eccezione: Il metodo lancia un eccezione di tipo Eccezione che estende Exception
	 */
	List<Utente> searchUtente(Utente u) throws Eccezione;
}
