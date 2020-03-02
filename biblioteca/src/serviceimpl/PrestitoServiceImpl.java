package serviceimpl;

import java.util.List;

import model.Prestito;
import model.dao.PrestitoDao;
import service.PrestitoService;
import utilities.Eccezione;

public class PrestitoServiceImpl implements PrestitoService {

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
	public List<Prestito> searchPrestito() throws Eccezione {
		return PrestitoDao.findAllPrestito();
	}
}
