package model;

public class Libro {

	private Long id;
	private String titolo;
	private String autore;
	private String editore;
	private int qta;
	private String isbn;
	private int scaffale;
	private int corsia;
	private char libreria;
	
	public Libro(Long id, String titolo, String autore, String editore, int qta, String isbn, int scaffale, int corsia,
			char libreria) {
		this.id = id;
		this.titolo = titolo;
		this.autore = autore;
		this.editore = editore;
		this.qta = qta;
		this.isbn = isbn;
		this.scaffale = scaffale;
		this.corsia = corsia;
		this.libreria = libreria;
	}
	
	public Libro(String titolo, String autore, String editore, int qta, String isbn, int scaffale, int corsia,
			char libreria) {
		this.titolo = titolo;
		this.autore = autore;
		this.editore = editore;
		this.qta = qta;
		this.isbn = isbn;
		this.scaffale = scaffale;
		this.corsia = corsia;
		this.libreria = libreria;
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
	public int getCorsia() {
		return corsia;
	}
	public void setCorsia(int corsia) {
		this.corsia = corsia;
	}
	public char getLibreria() {
		return libreria;
	}
	public void setLibreria(char libreria) {
		this.libreria = libreria;
	}
	
}
