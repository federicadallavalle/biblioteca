package model.dao;

import static utilities.DataBase.getConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Libro;
import model.Prestito;
import model.Utente;
import utilities.DataBase;
import utilities.Eccezione;

// TODO da commentare ogni metodo
public class PrestitoDao {
	/**
	 * creazione prestito
	 * 
	 * @param p prende e setta la dataInizio e le fk di Utente e Libro
	 * @throws Eccezione raccoglie le eccezioni di qualunque tipo
	 */
	public static void createPrestito(Prestito p) throws Eccezione {
		String sql = "INSERT INTO biblioteca.prestito (dataInizio, fkIdUtente, fkIdLibro) VALUES (?, ?, ?)";
		Connection conn = DataBase.getConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				throw new Eccezione(e1.getMessage());
			}
			throw new Eccezione(e.getMessage());
		}
		try {
			ps.setDate(1, Date.valueOf(p.getDataInizio()));
			ps.setLong(2, p.getUtente().getId());
			ps.setLong(3, p.getLibro().getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				throw new Eccezione(e.getMessage());
			}
		}
	}

	/**
	 * ricerca tutti i prestiti
	 * 
	 * @return una lista che conterrà tutti i prestiti
	 * @throws Eccezione raccoglie le eccezioni di qualunque tipo
	 */
	public static List<Prestito> findAllPrestito() throws Eccezione {
		String sql = "SELECT * FROM biblioteca.prestito";
		Connection conn = DataBase.getConnection();
		PreparedStatement ps;
		ArrayList<Prestito> lista = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				throw new Eccezione(e1.getMessage());
			}
			throw new Eccezione(e.getMessage());
		}
		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prestito p = new Prestito();
				p.setId(rs.getLong("id"));
				p.setDataInizio(rs.getDate("dataInizio").toLocalDate());
				p.setDataConsegna(rs.getDate("dataConsegna").toLocalDate());
				p.setDataUltimoSollecito(rs.getDate("dataUltimoSollecito").toLocalDate());
				// TODO settare l'utente e il libro
				Utente u = new Utente();
				u.setId(rs.getLong("fkIdUtente"));
				p.setUtente(u);
				Libro libro = new Libro();
				libro.setId(rs.getLong("fkIdLibro"));
				p.setLibro(libro);
				lista.add(p);
			}
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				throw new Eccezione(e.getMessage());
			}
		}
		return lista;
	}

	/**
	 * cerca gli id di tutti gli utenti che hanno ricevuto prestiti e non hanno
	 * restituito entro 30 giorni
	 * 
	 * @return la lista degli id utente cercati
	 * @throws Eccezione raccoglie le eccezioni di qualunque tipo
	 */
	public static List<Long> listaIdUtentiScadenze() throws Eccezione {
		// Seleziona le id utente che hanno un prestito non consegnato più vecchio di 30 giorni
		String sql = "SELECT fkIdUtente FROM biblioteca.prestito " 
				+ "WHERE dataInizio < ? AND dataConsegna IS NULL";
		// creo la connessione con il db
		Connection conn = DataBase.getConnection();
		PreparedStatement ps = null;
		ArrayList<Long> lista = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			// ottengo la data odierna
			LocalDate localDate = LocalDate.now();
			// sottraggo 30 giorni dalla data odierna
			// la data che ottengo è quella da confrontare con quella di inizio prestito
			localDate.plusDays(-30);
			Date data = Date.valueOf(localDate);
			// setto la data ottenuta come paramentro della query
			ps.setDate(1, data);
			ResultSet rs = ps.executeQuery();
			// aggiungo gli id ottenuti dalla query alla lista
			while (rs.next()) {
				lista.add(rs.getLong("id"));
			}
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				throw new Eccezione(e.getMessage());
			}
		}
		return lista;
	}

	/**
	 * aggiornamento di un prestito, verrà aggiunta anche la dataConsegna e la
	 * dataUltimoSollecito
	 * 
	 * @param p prende e setta tutti gli attributi di Prestiti
	 * @throws Eccezione raccoglie le eccezioni di qualunque tipo
	 */
	public static void updatePrestito(Prestito p) throws Eccezione {
		String sql = "UPDATE biblioteca.prestito SET "
				+ "dataInizio = ?, dataConsegna = ?, dataUltimoSollecito = ?, fkIdUtente = ?, fkIdLibro = ? "
				+ "WHERE id = ?";
		Connection conn = DataBase.getConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				throw new Eccezione(e1.getMessage());
			}
			throw new Eccezione(e.getMessage());
		}
		try {
			ps.setDate(1, Date.valueOf(p.getDataInizio()));
			ps.setDate(2, Date.valueOf(p.getDataConsegna()));
			ps.setDate(3, Date.valueOf(p.getDataUltimoSollecito()));
			ps.setLong(4, p.getUtente().getId());
			ps.setLong(5, p.getLibro().getId());
			ps.setLong(6, p.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				throw new Eccezione(e.getMessage());
			}
		}
	}

	/**
	 * cancellazione di un prestito
	 * 
	 * @param idPrestito della riga Prestito che verrà cancellata
	 * @throws Eccezione raccoglie le eccezioni di qualunque tipo
	 */
	public static void deletePrestito(Long idPrestito) throws Eccezione {
		String sql = "DELETE FROM biblioteca.prestito WHERE id = ?";
		Connection conn = DataBase.getConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				throw new Eccezione(e1.getMessage());
			}
			throw new Eccezione(e.getMessage());
		}
		try {
			ps.setLong(1, idPrestito);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				throw new Eccezione(e.getMessage());
			}
		}
	}

	public static int countPrestitoByLibro(Libro libro) throws Eccezione {
		Connection conn = getConnection();
		String sql = "SELECT COUNT(id) AS libriInPrestito FROM prestito WHERE fkIdLibro = ? AND dataConsegna IS NULL";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, libro.getId());
			ResultSet rs = ps.executeQuery();
			return rs.getInt("libriInPrestito");
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}
	}

	/**
	 * ricerca del prestito dall'id
	 * 
	 * @param id ovvero quello ricercato
	 * @return tutta la riga di quell'id
	 * @throws Eccezione raccoglie le eccezioni di qualunque tipo
	 */
	public static Prestito findPrestitoById(Long id) throws Eccezione {
		String sql = "SELECT * FROM biblioteca.prestito WHERE id = ?";
		Connection conn = DataBase.getConnection();
		PreparedStatement ps;
		Prestito p = new Prestito();
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				throw new Eccezione(e1.getMessage());
			}
			throw new Eccezione(e.getMessage());
		}
		try {
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			p.setId(rs.getLong("id"));
			p.setDataInizio(rs.getDate("dataInizio").toLocalDate());
			p.setDataConsegna(rs.getDate("dataConsegna").toLocalDate());
			p.setDataUltimoSollecito(rs.getDate("dataUltimoSollecito").toLocalDate());
			// TODO settare l'utente e il libro
			Utente u = new Utente();
			u.setId(rs.getLong("fkIdUtente"));
			p.setUtente(u);
			Libro libro = new Libro();
			libro.setId(rs.getLong("fkIdLibro"));
			p.setLibro(libro);
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				throw new Eccezione(e.getMessage());
			}
		}
		return p;

	}

	/**
	 * ricerca della riga prestito dall'idUtente
	 * 
	 * @param idUtente ovvero quello ricercato
	 * @return lista che conterrà tutti i prestiti del singolo utente ricercato
	 * @throws Eccezione raccoglie le eccezioni di qualunque tipo
	 */
	public static List<Prestito> findByUtenteId(Long idUtente) throws Eccezione {
		String sql = "SELECT * FROM biblioteca.prestito WHERE  fkIdUtente = ?";
		Connection conn = DataBase.getConnection();
		PreparedStatement ps;
		ArrayList<Prestito> lista = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				throw new Eccezione(e1.getMessage());
			}
			throw new Eccezione(e.getMessage());
		}
		try {
			ps.setLong(1, idUtente);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prestito p = new Prestito();
				p.setId(rs.getLong("id"));
				p.setDataInizio(rs.getDate("dataInizio").toLocalDate());
				p.setDataConsegna(rs.getDate("dataConsegna").toLocalDate());
				p.setDataUltimoSollecito(rs.getDate("dataUltimoSollecito").toLocalDate());
				// TODO settare l'utente e il libro
				Utente u = new Utente();
				u.setId(rs.getLong("fkIdUtente"));
				p.setUtente(u);
				Libro libro = new Libro();
				libro.setId(rs.getLong("fkIdLibro"));
				p.setLibro(libro);
				lista.add(p);
			}
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				throw new Eccezione(e.getMessage());
			}
		}
		return lista;
	}

	/**
	 * ricerca del prestito tramite l'idLibro
	 * 
	 * @param idLibro ovvero quello ricercato
	 * @return lista dei prestiti collegati al libro ricercato( di un libro possiamo
	 *         avere più copie )
	 * @throws Eccezione raccoglie le eccezioni di qualunque tipo
	 */
	public static List<Prestito> findByLibroId(Long idLibro) throws Eccezione {
		String sql = "SELECT * FROM biblioteca.prestito WHERE  fkIdLibro = ?";
		Connection conn = DataBase.getConnection();
		PreparedStatement ps;
		ArrayList<Prestito> lista = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				throw new Eccezione(e1.getMessage());
			}
			throw new Eccezione(e.getMessage());
		}
		try {
			ps.setLong(1, idLibro);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prestito p = new Prestito();
				p.setId(rs.getLong("id"));
				p.setDataInizio(rs.getDate("dataInizio").toLocalDate());
				p.setDataConsegna(rs.getDate("dataConsegna").toLocalDate());
				p.setDataUltimoSollecito(rs.getDate("dataUltimoSollecito").toLocalDate());
				// TODO settare l'utente e il libro
				Utente u = new Utente();
				u.setId(rs.getLong("fkIdUtente"));
				p.setUtente(u);
				Libro libro = new Libro();
				libro.setId(rs.getLong("fkIdLibro"));
				p.setLibro(libro);
				lista.add(p);
			}
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				throw new Eccezione(e.getMessage());
			}
		}
		return lista;
	}
}
