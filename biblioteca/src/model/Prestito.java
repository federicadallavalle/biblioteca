package model;

import java.time.LocalDate;

public class Prestito {
	private Long id;
	private LocalDate dataInizio;
	private LocalDate dataConsegna;
	private LocalDate dataUltimoSollecito;

	public Prestito() {
	}

	public Prestito(Long id, LocalDate dataInizio, LocalDate dataConsegna, LocalDate dataUltimoSollecito) {
		this.id = id;
		this.dataInizio = dataInizio;
		this.dataConsegna = dataConsegna;
		this.dataUltimoSollecito = dataUltimoSollecito;
	}

	public Prestito(LocalDate dataInizio, LocalDate dataConsegna, LocalDate dataUltimoSollecito) {
		this.dataInizio = dataInizio;
		this.dataConsegna = dataConsegna;
		this.dataUltimoSollecito = dataUltimoSollecito;
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
