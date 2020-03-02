package model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBase {

	public static Connection getConnection() throws SQLException {
		FileReader reader = null;
		Properties p = new Properties();
		try {
			reader = new FileReader("conf.properties");
			p.load(reader);
		} catch (FileNotFoundException e) {
			System.out.println("File conf.properties non trovato");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		String user = p.getProperty("user");
		String password = p.getProperty("password");
		String jdbc = p.getProperty("jdbc");
		String indirizzo = p.getProperty("indirizzo");
		String porta = p.getProperty("porta");
		String db = p.getProperty("db");

		String url = "jdbc:" + jdbc + "://" + indirizzo + ":" + porta + "/" + db + "?serverTimezone=Europe/Rome";

		Connection conn = DriverManager.getConnection(url, user, password);

		return conn;
	}

}
