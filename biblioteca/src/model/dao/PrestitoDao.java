package model.dao;

import static model.dao.DataBase.getConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Libro;
import model.Prestito;
import model.Utente;
import utilities.Eccezione;

// TODO da commentare ogni metodo
public class PrestitoDao {

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

	public static List<Prestito> findAllPrestito() throws Eccezione {
		String sql = "SELECT * FROM biblioteca.prestito ";
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

	public static Prestito findPrestitoById(String id) throws Eccezione {
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
}
