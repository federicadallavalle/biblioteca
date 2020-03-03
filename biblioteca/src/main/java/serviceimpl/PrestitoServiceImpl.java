package serviceimpl;

import java.util.List;

import model.Prestito;
import model.dao.PrestitoDao;
import service.PrestitoService;
import utilities.Eccezione;

public class PrestitoServiceImpl implements PrestitoService {

	private static PrestitoServiceImpl instance = new PrestitoServiceImpl();

	private PrestitoServiceImpl() {
	}

	public static PrestitoServiceImpl getInstance() {
		return instance;
	}

	@Override
	public void createPrestito(Prestito p) throws Eccezione {
		PrestitoDao.createPrestito(p);
	}

	@Override
	public void updatePrestito(Prestito p) throws Eccezione {
		PrestitoDao.updatePrestito(p);
	}

	@Override
	public void deletePrestito(Long idPrestito) throws Eccezione {
		PrestitoDao.deletePrestito(idPrestito);
	}

	@Override
	public List<Prestito> searchPrestitoByUtente(Long idUtente) throws Eccezione {
		return PrestitoDao.findByUtenteId(idUtente);
	}

	@Override
	public List<Prestito> searchPrestitoByLibro(Long idLibro) throws Eccezione {
		return PrestitoDao.findByLibroId(idLibro);
	}

}
