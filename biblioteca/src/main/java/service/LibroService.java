package service;

import java.util.List;
import model.Libro;

public interface LibroService {

	List<Libro> getList();

	void addLibro(Libro libro);

	void updateLibro(Libro libro);

	void deleteLibro(Libro libro);
}
