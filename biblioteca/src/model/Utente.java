package model;

public class Utente {

	private String nome;
	private String cognome;
	private String email;
	private String via;
	private String numCivico;
	private String citta;
	private String provincia;
	private String cap;
	private String telefono;
	private String ruolo;
	private String username;
	private String password;
	
	
	
	
	public Utente(String nome, String cognome, String email, String via, String numCivico, String citta,
			String provincia, String cap, String telefono, String ruolo, String username, String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.via = via;
		this.numCivico = numCivico;
		this.citta = citta;
		this.provincia = provincia;
		this.cap = cap;
		this.telefono = telefono;
		this.ruolo = ruolo;
		this.username = username;
		this.password = password;
	}


	public Utente() {
		super();
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
	public String getNumCivico() {
		return numCivico;
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
	public void setNumCivico(String numCivico) {
		this.numCivico = numCivico;
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

	
	public String getCodiceUtente() {
		return username;
	}


	public String getPassword() {
		return password;
	}


	public void setCodiceUtente(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", via=" + via + ", numCivico="
				+ numCivico + ", citta=" + citta + ", provincia=" + provincia + ", cap=" + cap + ", telefono="
				+ telefono + ", ruolo=" + ruolo + ", codiceUtente=" + username + ", password=" + password + "]";
	}


	
	
}

