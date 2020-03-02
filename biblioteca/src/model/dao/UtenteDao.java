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

	public List<Utente> cercaUtente() throws Eccezione {
		ArrayList<Utente> lista = new ArrayList<>();
		Connection conn = getConnection();
		String sql = "SELECT * FROM biblioteca.utente "
				+ "WHERE nome LIKE '%?%', cognome LIKE '%?%', email LIKE '%?%' ,"
				+ "ruolo LIKE '%?%', username LIKE '%?%'";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				

			}

			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
		return lista;
	}

}
