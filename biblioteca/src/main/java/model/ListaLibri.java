package model;

import java.util.ArrayList;
import java.util.List;

public class ListaLibri {
	private List<Libro> lista = new ArrayList<>();

	public List<Libro> getLista() {
		return lista;
	}

	public void setLista(List<Libro> lista) {
		this.lista = lista;
	}
}
