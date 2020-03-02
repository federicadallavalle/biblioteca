package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static model.dao.DataBase.getConnection;
import model.Utente;
import utilities.Eccezione;

public class UtenteDao {

	public static List<Utente> cercaUtente(Utente ut) throws Eccezione {
		ArrayList<Utente> lista = new ArrayList<>();
		Connection conn = getConnection();
		String sql = "SELECT * FROM biblioteca.utente " + "WHERE nome LIKE '%?%', cognome LIKE '%?%', email LIKE '%?%',"
				+ "ruolo LIKE '%?%', username LIKE '%?%'";
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

	public static void eliminaUtente(int id) throws Eccezione {
		Connection conn = getConnection();
		String sql = "DELETE FROM biblioteca.utente WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Cancellazione eseguita");
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
	}

	public static void modifica(Utente utente, int id) throws Eccezione {
		Connection conn = getConnection();
		String sql = "UPDATE biblioteca.utente "
				+ " SET nome = ?, cognome = ?, email = ?, telefono = ?, via = ?, civico = ?, citta = ?, provincia = ? "
				+ " cap = ?, ruolo = ?, username = ?, password = ? " + " WHERE id = ?";
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
			ps.setInt(13, id);
			ps.executeUpdate();
			System.out.println("Modifica eseguita");
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
	}
}
