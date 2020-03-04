package model;

import java.util.ArrayList;
import java.util.List;

public class ListaUtente {
	private List<Utente> lista = new ArrayList<>();

	public List<Utente> getLista() {
		return lista;
	}

	public void setLista(List<Utente> lista) {
		this.lista = lista;
	}
}
