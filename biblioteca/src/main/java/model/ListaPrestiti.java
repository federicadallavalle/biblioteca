package model;

import java.util.ArrayList;
import java.util.List;

public class ListaPrestiti {
	private List<Prestito> lista = new ArrayList<>();

	public List<Prestito> getLista() {
		return lista;
	}

	public void setLista(List<Prestito> lista) {
		this.lista = lista;
	}
}
