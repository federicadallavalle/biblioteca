package model.dao;

import com.sun.jdi.connect.spi.Connection;

import model.Prestito;

public class PrestitoDao {
	public static void createPrestito(Prestito p) {
		String sql = "INSERT INTO prestito (dataInizio, dataConsegna, dataUltimoSollecito, fkIdUtente, fkIdLibro)"
				+ "VALUES (?, ?, ?, ?, ?)";
//		Connection conn = DataBase.getConnection();
	}

}
