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

	public List<Utente> cercaUtente(Utente ut) throws Eccezione {
		ArrayList<Utente> lista = new ArrayList<>();
		Connection conn = getConnection();
		String sql = "SELECT * FROM biblioteca.utente "
				+ "WHERE nome LIKE '%?%', cognome LIKE '%?%', email LIKE '%?%',"
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
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setEmail(rs.getString("email"));
				utente.setRuolo(rs.getString("ruolo"));
				utente.setUsername(rs.getString("username"));
				lista.add(utente);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
		return lista;
	}

}
