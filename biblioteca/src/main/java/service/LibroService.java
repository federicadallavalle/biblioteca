package service;

import java.util.List;

import model.Libro;
import utilities.Eccezione;

public interface LibroService {

	List<Libro> getList(String key) throws Eccezione;

	void addLibro(Libro libro) throws Eccezione;

	void updateLibro(Libro libro) throws Eccezione;

	void deleteLibro(Libro libro) throws Eccezione;
}
