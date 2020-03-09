package model.dao;

import static utilities.DataBase.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Libro;
import model.ListaLibri;
import utilities.Eccezione;

public class LibroDao {
	/**
	 * Ritorna una lista di libri trovata secondo una chiave di ricerca. I libri
	 * vengono recuperati dal database
	 * 
	 * @param key la chiave di ricerca
	 * @return Una lista di libri
	 * @throws Eccezione gestisce eccezione SQLException
	 */
	public static ListaLibri findAllLibro(String key) throws Eccezione {
		ListaLibri listaLibri = new ListaLibri();

		try {
			Connection conn = getConnection();
			// Cerca libri che contengano la chiave di ricerca nel titolo, autore, editore e
			// isbn
			String sql = "SELECT * FROM libro WHERE titolo LIKE concat('%', ?, '%') OR "
					+ "autore LIKE concat('%', ?, '%') OR editore LIKE concat('%', ?, '%') OR "
					+ "isbn LIKE concat('%', ?, '%')";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps = conn.prepareStatement(sql);
			ps.setString(1, key);
			ps.setString(2, key);
			ps.setString(3, key);
			ps.setString(4, key);
			ResultSet rs = ps.executeQuery();
			System.out.println(sql);
			System.out.println(key);
			// finche' ci sono libri li aggiunge alla lista
			while (rs.next()) {
				Libro libro = new Libro();
				libro.setId(rs.getLong("id"));
				libro.setTitolo(rs.getString("titolo"));
				libro.setAutore(rs.getString("autore"));
				libro.setEditore(rs.getString("editore"));
				libro.setIsbn(rs.getString("isbn"));
				libro.setQta(rs.getInt("quantita"));
				libro.setScaffale(rs.getInt("scaffale"));
				libro.setLibreria(rs.getString("libreria"));
				listaLibri.addLibro(libro);
				System.out.println(libro);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
		return listaLibri;
	}

	/**
	 * Crea un nuovo libro e lo salva del database
	 * 
	 * @param libro il libro da salvare
	 * @throws Eccezione
	 */
	public static void createLibro(Libro libro) throws Eccezione {
		Connection conn = getConnection();
		String sql = "INSERT INTO libro (titolo, autore, editore, isbn, quantita, scaffale, libreria) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, libro.getTitolo());
			ps.setString(2, libro.getAutore());
			ps.setString(3, libro.getEditore());
			ps.setString(4, libro.getIsbn());
			ps.setInt(5, libro.getQta());
			ps.setInt(6, libro.getScaffale());
			ps.setString(7, libro.getLibreria());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
	}

	/**
	 * Modifica un libro gia' presente nel database
	 * 
	 * @param libro il libro da modificare
	 * @throws Eccezione
	 */
	public static void updateLibro(Libro libro) throws Eccezione {
		Connection conn = getConnection();
		// conta i libri attualmente in prestito
		String sql = "SELECT COUNT(id) AS libriInPrestito FROM prestito WHERE fkIdLibro = ? AND dataConsegna IS NULL";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, libro.getId());
			ResultSet rs = ps.executeQuery();
			// la modifica sara' effettuata solo se la quantita' inserita e' minore o uguale delle copie attualmente in prestito
			if (rs.getInt("libriInPrestito") <= libro.getQta()) {
				sql = "UPDATE libro SET titolo = ?, autore = ?, editore = ?, qta = ?, scaffale = ?, libreria = ? WHERE id = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, libro.getTitolo());
				ps.setString(2, libro.getAutore());
				ps.setString(3, libro.getEditore());
				ps.setInt(4, libro.getQta());
				ps.setInt(5, libro.getScaffale());
				ps.setString(6, libro.getLibreria());
				ps.setLong(7, libro.getId());
				ps.executeUpdate();
			} else {
				throw new Eccezione("Quantita' minore dei libri attualmente in prestito");
			}
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
	}

	/**
	 * Elimina un libro presente nel database
	 * 
	 * @param id l'id del libro da eliminare
	 * @throws Eccezione
	 */
	public static void deleteLibro(Long id) throws Eccezione {
		Connection conn = getConnection();
		// per prima cosa elimina i prestiti associati a quel libro
		String sql = "DELETE FROM prestito WHERE fkIdLibro = ?";
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
			// poi elimina il libro
			sql = "DELETE FROM libro WHERE id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
	}

	/**
	 * Ricerca un libro per id
	 * 
	 * @param id l'id del libro da torvare
	 * @throws Eccezione
	 */
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
