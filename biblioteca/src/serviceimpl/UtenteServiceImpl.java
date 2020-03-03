package serviceimpl;

import java.util.List;

import model.Utente;
import model.dao.UtenteDao;
import service.UtenteService;
import utilities.Eccezione;

public class UtenteServiceImpl implements UtenteService {

	private static UtenteServiceImpl istanza = null;
	
	private UtenteServiceImpl() {}
	
	public static UtenteServiceImpl getIstance() {
		if(istanza == null) {
			istanza = new UtenteServiceImpl();
		} 
		return istanza;
	}
	
	@Override
	public void createUtente(Utente u) throws Eccezione {
		UtenteDao.cercaUtente(u);
	}

	@Override
	public void updateUtente(Utente u, Long id) throws Eccezione {
		UtenteDao.modificaUtente(u, id);
	}

	@Override
	public void deleteUtente(Long id) throws Eccezione {
		UtenteDao.eliminaUtente(id);
	}

	@Override
	public List<Utente> searchUtente(Utente u) throws Eccezione {
		return UtenteDao.cercaUtente(u);
	}
}
