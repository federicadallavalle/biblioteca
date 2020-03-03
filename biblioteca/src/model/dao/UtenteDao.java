package model.dao;

import static utilities.DataBase.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Utente;
import utilities.Eccezione;

public class UtenteDao {

	/**
	 * Ricerca gli utenti in base ai parametri passati alla condizione
	 * nome, cognome, email, ruolo, username
	 * @param ut parametro di tipo utente contenente i dati di ricerca degli utenti
	 * @return lista degli utenti che soddisfano la condizione
	 * @throws Eccezione gestione degli errori
	 */
	public static List<Utente> cercaUtente(Utente ut) throws Eccezione {
		ArrayList<Utente> lista = new ArrayList<>();
		Connection conn = getConnection();
		String sql = "SELECT * FROM biblioteca.utente " 
				+ "WHERE nome LIKE '%?%', cognome LIKE '%?%', email LIKE '%?%'"
				+ ", ruolo LIKE '%?%', username LIKE '%?%'";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, ut.getNome());
			ps.setString(2, ut.getCognome());
			ps.setString(3, ut.getEmail());
			ps.setString(4, ut.getRuolo());
			ps.setString(5, ut.getUsername());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Utente utente = new Utente();
				utente.setId(rs.getLong("id"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setEmail(rs.getString("email"));
				utente.setTelefono(rs.getString("telefono"));
				utente.setVia(rs.getString("via"));
				utente.setCivico(rs.getString("civico"));
				utente.setCitta(rs.getString("citta"));
				utente.setProvincia(rs.getString("provincia"));
				utente.setCap(rs.getString("cap"));
				utente.setRuolo(rs.getString("ruolo"));
				utente.setUsername(rs.getString("username"));
				utente.setPassword(rs.getString("password"));
				lista.add(utente);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
		return lista;
	}
	
	/**
	 * Ricerca dell'utente in base al campo id
	 * @param id identificativo univoco dell'utente
	 * @return oggetto di tipo Utente contenente i dati dell'utente cercato
	 * @throws Eccezione gestione degli errori
	 */
	public static Utente cercaUtentePerId(Long id) throws Eccezione {
		// ottengo la connessione al db
		Connection conn = getConnection();
		// seleziono gli utenti corrispondenti all'id passato come paramentro
		String sql = "SELECT * FROM biblioteca.utente " 
				+ "WHERE id = ?";
		PreparedStatement ps = null;

		Utente utente = new Utente();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			// eseguo la query
			ResultSet rs = ps.executeQuery();
			// mi aspetto di trovare al più un risultato
			while (rs.next()) {
				// setto i parametri dell'oggetto da restituire
				utente.setId(rs.getLong("id"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setEmail(rs.getString("email"));
				utente.setTelefono(rs.getString("telefono"));
				utente.setVia(rs.getString("via"));
				utente.setCivico(rs.getString("civico"));
				utente.setCitta(rs.getString("citta"));
				utente.setProvincia(rs.getString("provincia"));
				utente.setCap(rs.getString("cap"));
				utente.setRuolo(rs.getString("ruolo"));
				utente.setUsername(rs.getString("username"));
				utente.setPassword(rs.getString("password"));
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
		return utente;
	}

	/**
	 * Inserimento dei dati dell'utente 
	 * @param utente parametro di tipo utente contenente i dati per l'inserimento nel db
	 * @throws Eccezione gestione degli errori
	 */
	public static void creaUtente(Utente utente) throws Eccezione {
		Connection conn = getConnection();
		String sql = "INSERT INTO biblioteca.utente "
				+ "(nome, cognome, email, telefono, via, civico, citta, procincia, cap, ruolo, username, password) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, utente.getNome());
			ps.setString(2, utente.getCognome());
			ps.setString(3, utente.getEmail());
			ps.setString(4, utente.getTelefono());
			ps.setString(5, utente.getVia());
			ps.setString(6, utente.getCivico());
			ps.setString(7, utente.getCitta());
			ps.setString(8, utente.getProvincia());
			ps.setString(9, utente.getCap());
			ps.setString(10, utente.getRuolo());
			ps.setString(11, utente.getUsername());
			ps.setString(12, utente.getPassword());
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
	}

	/**
	 * Cancellazione dell'utente in base al parametro id
	 * @param id identificativo univoco dell'utente
	 * @throws Eccezione gestione degli errori
	 */
	public static void eliminaUtente(Long id) throws Eccezione {
		Connection conn = getConnection();
		String sql = "DELETE FROM biblioteca.utente WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
			System.out.println("Cancellazione eseguita");
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
	}

	/**
	 * Aggiornamento dei dati dell'utente in base al parametro id
	 * @param utente parametro di tipo utente contenente i dati per la modifica
	 * @param id identificativo univoco dell'utente
	 * @throws Eccezione gestione degli errori
	 */
	public static void modificaUtente(Utente utente, Long id) throws Eccezione {
		Connection conn = getConnection();
		String sql = "UPDATE biblioteca.utente "
				+ "SET nome = ?, cognome = ?, email = ?, telefono = ?, via = ?, civico = ?, citta = ?"
				+ ", provincia = ? , cap = ?, ruolo = ?, username = ?, password = ? "
				+ "WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, utente.getNome());
			ps.setString(2, utente.getCognome());
			ps.setString(3, utente.getEmail());
			ps.setString(4, utente.getTelefono());
			ps.setString(5, utente.getVia());
			ps.setString(6, utente.getCivico());
			ps.setString(7, utente.getCitta());
			ps.setString(8, utente.getProvincia());
			ps.setString(9, utente.getCap());
			ps.setString(10, utente.getRuolo());
			ps.setString(11, utente.getUsername());
			ps.setString(12, utente.getPassword());
			ps.setLong(13, id);
			ps.executeUpdate();
			System.out.println("Modifica eseguita");
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
	}
}
