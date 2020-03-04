package model;

import java.util.Collections;
import java.util.List;

public class Utente {
	private Long id;
	private String nome;
	private String cognome;
	private String email;
	private String via;
	private String civico;
	private String citta;
	private String provincia;
	private String cap;
	private String telefono;
	private String ruolo;
	private String username;
	private String password;
	private List<Prestito> prestiti;

	public Utente(String nome, String cognome, String email, String via, String numCivico, String citta,
			String provincia, String cap, String telefono, String ruolo, String username, String password,
			List<Prestito> prestiti) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.via = via;
		this.civico = numCivico;
		this.citta = citta;
		this.provincia = provincia;
		this.cap = cap;
		this.telefono = telefono;
		this.ruolo = ruolo;
		this.username = username;
		this.password = password;
		this.prestiti = prestiti;
	}

	public Utente() {
	}

	public Utente(String nome, String cognome, String email, String ruolo, String username) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.ruolo = ruolo;
		this.username = username;
	}
	
	

	public Utente(String nome, String cognome, String email, String via, String civico, String citta,
			String provincia, String cap, String telefono, String ruolo,String username, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.via = via;
		this.civico = civico;
		this.citta = citta;
		this.provincia = provincia;
		this.cap = cap;
		this.telefono = telefono;
		this.ruolo = ruolo;
		this.username=username;
		this.password=password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getEmail() {
		return email;
	}

	public String getVia() {
		return via;
	}

	public String getCivico() {
		return civico;
	}

	public String getCitta() {
		return citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getCap() {
		return cap;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public void setCivico(String numCivico) {
		this.civico = numCivico;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Prestito> getPrestiti() {
		return prestiti;
	}

	public void setPrestiti(List<Prestito> prestiti) {
		this.prestiti = prestiti;
	}

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", via=" + via + ", numCivico="
				+ civico + ", citta=" + citta + ", provincia=" + provincia + ", cap=" + cap + ", telefono=" + telefono
				+ ", ruolo=" + ruolo + ", username=" + username + ", password=" + password + ", prestiti=" + prestiti
				+ "]";
	}

	public static Utente getEmptyUtente() {
		return new Utente("", "", "", "", "", "", "", "", "", "", "", "", Collections.<Prestito>emptyList());
	}

}
