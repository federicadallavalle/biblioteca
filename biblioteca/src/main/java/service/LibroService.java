package service;

import model.Libro;
import model.ListaLibri;
import utilities.Eccezione;

public interface LibroService {

	/**
	 * Recupera una lista di libri salvati nel database
	 * @param key Chiave di ricerca, cerca libri che contengano la chiave nel titolo, autore, editore e isbn
	 * @return Ritorna un oggetto ListaLibri contenente una lista di Libro
	 * @throws Eccezione
	 */
	ListaLibri getList(String key) throws Eccezione;

	void addLibro(Libro libro) throws Eccezione;

	void updateLibro(Libro libro) throws Eccezione;

	void deleteLibro(Long id) throws Eccezione;
}
