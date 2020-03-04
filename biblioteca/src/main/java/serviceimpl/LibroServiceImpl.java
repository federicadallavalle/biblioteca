package serviceimpl;

import model.dao.LibroDao;

import java.util.ArrayList;
import java.util.List;
import model.Libro;
import service.LibroService;
import utilities.Eccezione;

public class LibroServiceImpl implements LibroService {

	@Override
	public List<Libro> getList(String key) throws Eccezione {
		List<Libro> lista = new ArrayList<Libro>();
		try {
			lista = LibroDao.findAllLibro(key);
		} catch (Eccezione e) {
			System.out.println("Lista non trovata " + e.getMessage());
		}
		return lista;
	}

	@Override
	public void addLibro(Libro libro) throws Eccezione {
		LibroDao.createLibro(libro);
	}

	@Override
	public void updateLibro(Libro libro) throws Eccezione {
		LibroDao.updateLibro(libro);
	}

	@Override
	public void deleteLibro(Long id) throws Eccezione {
		LibroDao.deleteLibro(id);
	}

}
