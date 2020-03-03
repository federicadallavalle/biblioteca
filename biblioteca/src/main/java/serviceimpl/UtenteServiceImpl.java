package serviceimpl;

import java.util.List;

import model.Utente;
import model.dao.UtenteDao;
import service.UtenteService;
import utilities.Eccezione;

public class UtenteServiceImpl implements UtenteService {

//	   Inizializzazione di istanza a null
	private static UtenteServiceImpl istanza = null;
	
//	   Costruttore senza parametri
	private UtenteServiceImpl() {}
	
//	   Creazione del Singleton per istanziare la classe UtenteServiceImpl
	public static UtenteServiceImpl getIstance() {
		if(istanza == null) {
			istanza = new UtenteServiceImpl();
		} 
		return istanza;
	}
	
//	   Il metodo createUtente richiama il metodo creaUtente della classe UtenteDao
//	   per inserire i dati dell'utente
	@Override
	public void createUtente(Utente u) throws Eccezione {
		UtenteDao.creaUtente(u);
	}
	
//	   Il metodo updateUtente richiama il metodo modificaUtente della classe UtenteDao
//	   per modificare i dati dell'utente
	@Override
	public void updateUtente(Utente u, Long id) throws Eccezione {
		UtenteDao.modificaUtente(u, id);
	}

//	   Il metodo deleteUtente richiama il metodo eliminaUtente della classe UtenteDao
//	   per cancellare i dati dell'utente
	@Override
	public void deleteUtente(Long id) throws Eccezione {
		UtenteDao.eliminaUtente(id);
	}

//	   Il metodo searchUtente richiama il metodo cercaUtente della classe UtenteDao
//	   per ricercare l'utente in base ai parametri inseriti nella ricerca
	@Override
	public List<Utente> searchUtente(Utente u) throws Eccezione {
		return UtenteDao.cercaUtente(u);
	}
}
