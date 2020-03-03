package service;

import java.util.List;

import model.Utente;
import utilities.Eccezione;

public interface UtenteService {
	
	/**
	 * permette l'inserimento di un utente nel db
	 * @param u: Parametro di tipo utente che contiene i dati che dovranno essere inseriti nel db  
	 * @throws Eccezione: Il metodo lancia un eccezione di tipo Eccezzione che estende Exception
	 */
	void createUtente(Utente u) throws Eccezione;

	void updateUtente(Utente u, Long id) throws Eccezione;

	void deleteUtente(Long idUtente) throws Eccezione;

	List<Utente> searchUtente(Utente u) throws Eccezione;
}
