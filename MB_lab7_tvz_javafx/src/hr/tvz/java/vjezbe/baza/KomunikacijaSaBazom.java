package hr.tvz.java.vjezbe.baza;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import hr.tvz.java.vjezbe.sucelja.MyDialogs;

public class KomunikacijaSaBazom implements MyDialogs {

	private static final String DATABASE_PROPERTIES_FILE = "Resources/propertiesData/aplikacija.properties";

	protected static Connection connectingToDataBase() throws Exception {
		Connection vezaSaBazom = null;
		Properties svojstva = new Properties();
		final FileReader citacDatoteke = new FileReader(
				DATABASE_PROPERTIES_FILE);
		svojstva.load(citacDatoteke);
		String urlBazePodataka = svojstva.getProperty("bazaPodatakaURL");
		String korisnickoIme = svojstva.getProperty("korisnickoIme");
		String lozinka = svojstva.getProperty("lozinka");
		vezaSaBazom = DriverManager.getConnection(urlBazePodataka,
				korisnickoIme, lozinka);
		return vezaSaBazom;
	}

	protected static void closingConnectionToDataBase(Connection vezaSaBazom)
			throws SQLException {
		vezaSaBazom.close();
	}
}
