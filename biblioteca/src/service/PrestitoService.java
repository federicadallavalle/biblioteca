package service;

import java.util.List;

import model.Prestito;
import utilities.Eccezione;

public interface PrestitoService {
	void createPrestito(Prestito p) throws Eccezione;

	void updatePrestito(Prestito p) throws Eccezione;

	void deletePrestito(Long idPrestito) throws Eccezione;

	List<Prestito> searchPrestito() throws Eccezione;
}
