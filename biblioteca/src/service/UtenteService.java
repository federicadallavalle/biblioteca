package service;

import java.util.List;

import model.Utente;
import utilities.Eccezione;

public interface UtenteService {
	void createUtente(Utente u) throws Eccezione;

	void updateUtente(Utente u, Long id) throws Eccezione;

	void deleteUtente(Long idUtente) throws Eccezione;

	List<Utente> searchUtente(Utente u) throws Eccezione;
}
