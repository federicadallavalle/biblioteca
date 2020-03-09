package serviceimpl;


import model.Libro;
import model.ListaLibri;
import model.dao.LibroDao;
import service.LibroService;
import utilities.Eccezione;

public class LibroServiceImpl implements LibroService {
	
	private static LibroServiceImpl istance = null;

	private LibroServiceImpl() {
	}

	public static LibroServiceImpl getIstance() {
		if (istance == null)
			istance = new LibroServiceImpl();
		return istance;
	}

	@Override
	public ListaLibri getList(String key) throws Eccezione {
		ListaLibri lista = new ListaLibri();
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
