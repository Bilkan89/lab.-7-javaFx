package hr.tvz.java.vjezbe.baza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hr.tvz.java.vjezbe.entitet.Clan;

public class PodaciIzBazeClan {

	public static List<Clan> dohvatiClanoveIzBaze() throws Exception {
		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		String SQLupit = "SELECT * FROM RAZVOJ.CLAN";
		PreparedStatement prepaStmt = vezaSaBazom.prepareStatement(SQLupit);
		ResultSet resSet = prepaStmt.executeQuery();

		List<Clan> listaClanova = new ArrayList<Clan>();
		Clan clan = null;

		while (resSet.next()) {
			int idClana = resSet.getInt("id");
			String imeClana = resSet.getString("ime");
			String prezimeclana = resSet.getString("prezime");
			Integer oibClana = resSet.getInt("oib");

			clan = new Clan(idClana, imeClana, prezimeclana, oibClana);
			listaClanova.add(clan);
		}

		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);
		return listaClanova;
	}

	public static void spremiClanaUBazu(Clan clan) throws Exception {

		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		String sqlUpit = "INSERT INTO RAZVOJ.CLAN (oib, ime, prezime ) VALUES (?,?,?)";
		PreparedStatement stmt = vezaSaBazom.prepareStatement(sqlUpit);
		stmt.setInt(1, clan.getOib());
		stmt.setString(2, clan.getIme());
		stmt.setString(3, clan.getPrezime());
		stmt.executeUpdate();

		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);

	}

	public static int najveciIdBazeClan() throws Exception {
		int id = 0;
		Connection vezaBaza = KomunikacijaSaBazom.connectingToDataBase();
		Statement Stmt = vezaBaza.createStatement();
		ResultSet resSet = Stmt.executeQuery("SELECT MAX(id) FROM RAZVOJ.CLAN");
		while (resSet.next()) {
			id = resSet.getInt(1);
		}
		KomunikacijaSaBazom.closingConnectionToDataBase(vezaBaza);
		return id;
	}

	public static void promijeniClana(Clan clan) throws Exception {

		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		String sqlUpit = "UPDATE RAZVOJ.CLAN SET oib=?, ime=?, prezime=? WHERE id=?";
		PreparedStatement prepStmt = vezaSaBazom.prepareStatement(sqlUpit);

		prepStmt.setInt(1, clan.getOib());
		prepStmt.setString(2, clan.getIme());
		prepStmt.setString(3, clan.getPrezime());
		prepStmt.setInt(4, clan.getIdClana());
		prepStmt.executeUpdate();

		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);
	}

	public static void obrisiClana(Clan clan) throws Exception {
		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		String sqlUpit = "DELETE FROM RAZVOJ.CLAN WHERE id=?";
		PreparedStatement stmt = vezaSaBazom.prepareStatement(sqlUpit);
		stmt.setInt(1, clan.getIdClana());
		stmt.executeUpdate();
		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);

	}

}
