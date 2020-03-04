package serviceimpl;

import java.util.ArrayList;
import java.util.List;

import model.Utente;
import model.dao.PrestitoDao;
import model.dao.UtenteDao;
import service.ScadenzaService;
import utilities.Eccezione;

public class ScadenzaServiceImpl implements ScadenzaService {

	private static ScadenzaServiceImpl instance = new ScadenzaServiceImpl();

	private ScadenzaServiceImpl() {
	}

	/**
	 * Metodo accessore del singleton ScadenzaService
	 * 
	 * @return l'istanza di ScadenzaService
	 */
	public static ScadenzaServiceImpl getInstance() {
		return instance;
	}

	@Override
	public List<Utente> listaUtentiScadenze() throws Eccezione {
		// recupero la lista degi id degli utenti che hanno scadenze
		List<Long> idUtentiScadenze = PrestitoDao.listaIdUtentiScadenze();
		List<Utente> utenti = new ArrayList<>();
		// se la lista degli id non è vuota
		if (idUtentiScadenze != null) {
			// per ogni id utente trovato, recupero l'utente corrispondente
			for (Long id : idUtentiScadenze) {
				utenti.add(UtenteDao.cercaUtentePerId(id));
			}
		}
		return utenti;
	}
}
