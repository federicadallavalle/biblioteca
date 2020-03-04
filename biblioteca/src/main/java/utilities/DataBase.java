package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBase {
	private static Properties p = new Properties();

	public static Connection getConnection() throws Eccezione {
		FileReader reader = null;
		try {
			// TODO settare un percorso relativo per il conf.properties
			reader = new FileReader("conf.properties");
			p.load(reader);
		} catch (FileNotFoundException e) {
			throw new Eccezione("File conf.properties non trovato"
					// TODO settare un percorso relativo per il conf.properties
					+ "\nCopiare il file in: " + new java.io.File("").getAbsolutePath());
		} catch (IOException e) {
			throw new Eccezione(e.getMessage());
		}

		String user = p.getProperty("user");
		String password = p.getProperty("password");
		String jdbc = p.getProperty("jdbc");
		String indirizzo = p.getProperty("indirizzo");
		String porta = p.getProperty("porta");
		String db = p.getProperty("db");

		String url = "jdbc:" + jdbc + "://" + indirizzo + ":" + porta + "/" + db + "?serverTimezone=Europe/Rome";

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new Eccezione(e.getMessage());
		}

		return conn;
	}

}
