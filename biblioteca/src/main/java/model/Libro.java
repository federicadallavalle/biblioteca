package model;

import java.util.List;

public class Libro {

	private Long id;
	private String titolo;
	private String autore;
	private String editore;
	private int qta;
	private String isbn;
	private int scaffale;
	private String libreria;
	private List<Prestito> prestiti;
	
	public Libro(Long id, String titolo, String autore, String editore, int qta, String isbn, int scaffale,
			String libreria, List<Prestito> prestiti) {
		this.id = id;
		this.titolo = titolo;
		this.autore = autore;
		this.editore = editore;
		this.qta = qta;
		this.isbn = isbn;
		this.scaffale = scaffale;
		this.libreria = libreria;
		this.prestiti = prestiti;
	}

	public Libro() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getEditore() {
		return editore;
	}

	public void setEditore(String editore) {
		this.editore = editore;
	}

	public int getQta() {
		return qta;
	}

	public void setQta(int qta) {
		this.qta = qta;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getScaffale() {
		return scaffale;
	}

	public void setScaffale(int scaffale) {
		this.scaffale = scaffale;
	}

	public String getLibreria() {
		return libreria;
	}

	public void setLibreria(String libreria) {
		this.libreria = libreria;
	}

	public List<Prestito> getPrestiti() {
		return prestiti;
	}

	public void setPrestiti(List<Prestito> prestiti) {
		this.prestiti = prestiti;
	}
	
	
	
}
