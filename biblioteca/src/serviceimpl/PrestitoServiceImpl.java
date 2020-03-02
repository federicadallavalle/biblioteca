package serviceimpl;

import model.Prestito;
import model.dao.PrestitoDao;
import service.PrestitoService;

public class PrestitoServiceImpl implements PrestitoService {

	@Override
	public void createPrestito(Prestito p) {
		Prestito prestito = new Prestito();
		prestito.setDataInizio(p.getDataInizio());
		prestito.setUtente(p.getUtente());
		prestito.setLibro(p.getLibro());
		PrestitoDao.createPrestito(prestito);
	}
}
