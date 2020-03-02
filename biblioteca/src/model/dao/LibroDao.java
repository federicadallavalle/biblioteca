package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static model.dao.DataBase.getConnection;
import utilities.Eccezione;
import model.Libro;

public class LibroDao {
	/**
	 * Ritorna una lista di libri trovata secondo una chiave di ricerca. I libri vengono recuperati dal database
	 * 
	 * @param key la chiave di ricerca
	 * @return Una lista di libri
	 * @throws Eccezione gestisce eccezione SQLException
	 */
	public static List<Libro> lista(String key) throws Eccezione {
		List<Libro> lista = new ArrayList<>();
		try {
			Connection conn = getConnection();
			String sql = "SELECT * FROM libro WHERE " 
					+ "titolo='%" + key + "%' OR " 
					+ "autore='%" + key + "%' OR "
					+ "editore='%" + key + "%' OR " 
					+ "isbn='%" + key + "%'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Libro libro = new Libro();
				libro.setId(rs.getLong("id"));
				libro.setTitolo(rs.getString("titolo"));
				libro.setAutore(rs.getString("autore"));
				libro.setEditore(rs.getString("editore"));
				libro.setIsbn(rs.getString("isbn"));
				libro.setQta(rs.getInt("quantita"));
				libro.setScaffale(rs.getInt("scaffale"));
				libro.setCorsia(rs.getInt("corsia"));
				libro.setLibreria(rs.getString("libreria"));
				lista.add(libro);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
		return lista;
	}
}
