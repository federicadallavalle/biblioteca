package model;

import java.time.LocalDate;

public class Prestito {
	private Long id;
	private LocalDate dataInizio;
	private LocalDate dataConsegna;
	private LocalDate dataUltimoSollecito;
	private Utente utente;
	private Libro libro;

	public Prestito() {
		utente = new Utente();
		libro = new Libro();
	}

	public Prestito(Long id, LocalDate dataInizio, LocalDate dataConsegna, LocalDate dataUltimoSollecito, Utente utente,
			Libro libro) {
		this.id = id;
		this.dataInizio = dataInizio;
		this.dataConsegna = dataConsegna;
		this.dataUltimoSollecito = dataUltimoSollecito;
		this.utente = utente;
		this.libro = libro;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(LocalDate dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public LocalDate getDataUltimoSollecito() {
		return dataUltimoSollecito;
	}

	public void setDataUltimoSollecito(LocalDate dataUltimoSollecito) {
		this.dataUltimoSollecito = dataUltimoSollecito;
	}
}
