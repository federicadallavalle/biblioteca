package model.dao;

import static utilities.DataBase.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utilities.Eccezione;
import model.Libro;

public class LibroDao {
	/**
	 * Ritorna una lista di libri trovata secondo una chiave di ricerca. I libri
	 * vengono recuperati dal database
	 * 
	 * @param key la chiave di ricerca
	 * @return Una lista di libri
	 * @throws Eccezione gestisce eccezione SQLException
	 */
	public static List<Libro> findAllLibro(String key) throws Eccezione {
		List<Libro> lista = new ArrayList<>();
		try {
			Connection conn = getConnection();
			// Cerca libri che contengano la chiave di ricerca nel titolo, autore, editore e
			// isbn
			String sql = "SELECT * FROM libro WHERE " + "titolo='%" + key + "%' OR " + "autore='%" + key + "%' OR "
					+ "editore='%" + key + "%' OR " + "isbn='%" + key + "%'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// finch� ci sono libri li aggiunge alla lista
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

	public static void createLibro(Libro libro) throws Eccezione {
		Connection conn = getConnection();
		String sql = "INSERT INTO libro (titolo, autore, editore, isbn, quantita, scaffale, corsia, libreria) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, libro.getTitolo());
			ps.setString(2, libro.getAutore());
			ps.setString(3, libro.getEditore());
			ps.setString(4, libro.getIsbn());
			ps.setInt(5, libro.getQta());
			ps.setInt(6, libro.getScaffale());
			ps.setInt(7, libro.getCorsia());
			ps.setString(8, libro.getLibreria());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
	}

	public static void updateLibro(Libro libro, Long id) throws Eccezione {
		Connection conn = getConnection();
		String sql = "SELECT COUNT(id) AS libriInPrestito FROM prestito WHERE fkIdLibro = ? AND dataConsegna IS NULL";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, libro.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.getInt("libriInPrestito") <= libro.getQta()) {
				sql = "UPDATE libro SET titolo = ?, autore = ?, editore = ?, qta = ?, scaffale = ?, corsia = ?, libreria = ? WHERE id = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, libro.getTitolo());
				ps.setString(2, libro.getAutore());
				ps.setString(3, libro.getEditore());
				ps.setInt(4, libro.getQta());
				ps.setInt(5, libro.getScaffale());
				ps.setInt(6, libro.getCorsia());
				ps.setString(7, libro.getLibreria());
				ps.setLong(8, libro.getId());
				ps.executeUpdate();
			} else {
				throw new Eccezione("Quantit� minore dei libri attualmente in prestito");
			}
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
	}

	public static void deleteLibro(Long id) throws Eccezione {
		Connection conn = getConnection();
		String sql = "DELETE FROM prestito WHERE fkIdLibro = ?";
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
			sql = "DELETE FROM libro WHERE id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
	}

	public static void findLibroById(Long id) throws Eccezione {
		Connection conn = getConnection();
		String sql = "SELECT FROM libro WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
	}
}
