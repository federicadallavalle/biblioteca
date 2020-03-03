package serviceimpl;

import java.util.ArrayList;
import java.util.List;

import model.Utente;
import model.dao.PrestitoDao;
import service.ScadenzaService;

public class ScadenzaServiceImpl implements ScadenzaService{
	
	private static ScadenzaServiceImpl instance = new ScadenzaServiceImpl();
	
	private ScadenzaServiceImpl() {
	}
	
	public static ScadenzaServiceImpl getInstance() {
		return instance;
	}

	@Override
	public List<Utente> listaUtentiScadenze() {
		List<Integer> idUtentiScadenze = PrestitoDao.listaIdUtentiScadenze();
		List<Utente> utenti = new ArrayList<>();
		for(Integer id: idUtentiScadenze) {
			utenti.add(UtenteDao.cercaUtentePerId(id));
		}
		return utente;
	}	
}
