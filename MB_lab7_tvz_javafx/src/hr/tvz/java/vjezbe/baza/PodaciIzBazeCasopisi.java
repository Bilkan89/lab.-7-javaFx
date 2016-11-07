package hr.tvz.java.vjezbe.baza;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hr.tvz.java.vjezbe.entitet.Casopis;
import hr.tvz.java.vjezbe.entitet.Clan;
import hr.tvz.java.vjezbe.entitet.Posudba;
import hr.tvz.java.vjezbe.enumeracije.VrstaPublikacije;

public class PodaciIzBazeCasopisi {

	public static List<Casopis> dohvatiCasopiseIzBaze() throws Exception {
		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		String SQLupit = "SELECT * FROM RAZVOJ.CASOPIS";
		PreparedStatement prepaStmt = vezaSaBazom.prepareStatement(SQLupit);
		ResultSet resSet = prepaStmt.executeQuery();
		List<Casopis> listaCasopisa = new ArrayList<Casopis>();
		while (resSet.next()) {
			int idCasopisa = resSet.getInt("id");
			String nazivCasopisa = resSet.getString("naziv");
			Integer godinaCasopisa = resSet.getInt("godinaizdanja");
			VrstaPublikacije vrstaPub = null;
			int vrstaPubId = resSet.getInt("vrstapublikacije");
			for (VrstaPublikacije element : VrstaPublikacije
					.VrijednostiVrstePub()) {
				if (element.getVrstaPublikacijeId() == vrstaPubId) {
					vrstaPub = element;
				}
			}
			Integer brojStranicaCas = resSet.getInt("brojstranica");
			int mjesecCasopisa = resSet.getInt("mjesecizdanja");
			Casopis casopis = new Casopis(idCasopisa, nazivCasopisa,
					godinaCasopisa, brojStranicaCas, mjesecCasopisa, vrstaPub,
					10.00 / brojStranicaCas);
			listaCasopisa.add(casopis);
		}
		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);
		return listaCasopisa;
	}

	public static void spremiCasopisUBazu(Casopis casop) throws Exception {
		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		String sqlUpit = "INSERT INTO RAZVOJ.CASOPIS (naziv, godinaizdanja, vrstapublikacije, brojstranica, mjesecizdanja) VALUES (?,?,?,?,?)";
		PreparedStatement prepStmt = vezaSaBazom.prepareStatement(sqlUpit);
		prepStmt.setString(1, casop.getNazivPublikacije());
		prepStmt.setInt(2, casop.getGodinaIzdanja());
		prepStmt.setInt(3, casop.getVrstaPublikacije().getVrstaPublikacijeId());
		prepStmt.setInt(4, casop.getBrojStranicaPublikacije());
		prepStmt.setInt(5, casop.getMjesecCasopisa());
		prepStmt.executeUpdate();
		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);
	}

	public static int najveciIdBazeCasopis() throws Exception {
		int id = 0;
		Connection vezaBaza = KomunikacijaSaBazom.connectingToDataBase();
		Statement Stmt = vezaBaza.createStatement();
		ResultSet resSet = Stmt
				.executeQuery("SELECT MAX(id) FROM RAZVOJ.CASOPIS");
		while (resSet.next()) {
			id = resSet.getInt(1);
		}
		KomunikacijaSaBazom.closingConnectionToDataBase(vezaBaza);
		return id;
	}

	public static void promijeniCasopis(Casopis casop) throws Exception {
		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		String sqlUpit = "UPDATE RAZVOJ.CASOPIS SET naziv=?, godinaizdanja=?, vrstapublikacije=?, brojstranica=?, mjesecizdanja=? WHERE id=?";
		PreparedStatement prepStmt = vezaSaBazom.prepareStatement(sqlUpit);
		prepStmt.setString(1, casop.getNazivPublikacije());
		prepStmt.setInt(2, casop.getGodinaIzdanja());
		prepStmt.setInt(3, casop.getVrstaPublikacije().getVrstaPublikacijeId());
		prepStmt.setInt(4, casop.getBrojStranicaPublikacije());
		prepStmt.setInt(5, casop.getMjesecCasopisa());
		prepStmt.setInt(6, casop.getIdCasopisa());
		prepStmt.executeUpdate();
		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);
	}

	public static void obrisiCasopis(Casopis casop) throws Exception {
		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		String sqlUpit = "DELETE FROM RAZVOJ.CASOPIS WHERE id=?";
		PreparedStatement stmt = vezaSaBazom.prepareStatement(sqlUpit);
		stmt.setInt(1, casop.getIdCasopisa());
		stmt.executeUpdate();
		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);
	}

	public static void spremanjePosudbeCasopisa(Posudba<Casopis> posudba)
			throws Exception {
		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		String sqlUpit = "INSERT INTO RAZVOJ.POSUDBA_CASOPIS (casopis, clan, datumposudbe) VALUES (?,?,?)";
		PreparedStatement prepStmt = vezaSaBazom.prepareStatement(sqlUpit);
		prepStmt.setInt(1,
				((Casopis) posudba.getPublikacijaVar()).getIdCasopisa());
		prepStmt.setInt(2, posudba.getClanVar().getIdClana());
		prepStmt.setDate(3, Date.valueOf(posudba.getDatumPosudbe()));
		prepStmt.executeUpdate();
		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);
	}

	public static List<Posudba<Casopis>> dohvacanjePosudbeCasopisa()
			throws Exception {

		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		String sqlUpit = "SELECT * FROM RAZVOJ.POSUDBA_CASOPIS";
		PreparedStatement prepStmt = vezaSaBazom.prepareStatement(sqlUpit);
		ResultSet resSet = prepStmt.executeQuery();

		List<Posudba<Casopis>> listPosudbeCasopisa = new ArrayList<>();
		Casopis pCasopis = null;
		Clan clan = null;
		int idCasopisa = 0;
		int idClana = 0;
		Date datum = null;

		while (resSet.next()) {
			idCasopisa = resSet.getInt("casopis");
			idClana = resSet.getInt("clan");
			datum = resSet.getDate("datumposudbe");

			String sqlUpit2 = "SELECT * FROM RAZVOJ.CASOPIS WHERE id="
					+ idCasopisa;
			PreparedStatement prepStmt2 = vezaSaBazom
					.prepareStatement(sqlUpit2);
			ResultSet resSet2 = prepStmt2.executeQuery();
			// POPUNA CASOPISA
			while (resSet2.next()) {
				String naziv = resSet2.getString("naziv");
				int godina = resSet2.getInt("godinaizdanja");
				VrstaPublikacije vrstaPub = null;
				int vrstaPubId = resSet2.getInt("vrstapublikacije");
				for (VrstaPublikacije element : VrstaPublikacije
						.VrijednostiVrstePub()) {
					if (element.getVrstaPublikacijeId() == vrstaPubId) {
						vrstaPub = element;
					}
				}
				int brojStr = resSet2.getInt("brojstranica");
				int mjesecIzd = resSet2.getInt("mjesecizdanja");

				pCasopis = new Casopis(idCasopisa, naziv, godina, brojStr,
						mjesecIzd, vrstaPub, 10.00 / brojStr);
			}
			String sqlUpit3 = "SELECT * FROM RAZVOJ.CLAN WHERE id=" + idClana;
			PreparedStatement prepStmt3 = vezaSaBazom
					.prepareStatement(sqlUpit3);
			ResultSet resSet3 = prepStmt3.executeQuery();
			// POPUNA CLANA
			while (resSet3.next()) {
				int oibC = resSet3.getInt("oib");
				String nazivC = resSet3.getString("ime");
				String prezimeC = resSet3.getString("prezime");
				clan = new Clan(idClana, nazivC, prezimeC, oibC);
			}
			LocalDate vrijemeP = LocalDate
					.parse(new SimpleDateFormat("yyyy-MM-dd").format(datum));
			Posudba<Casopis> PosudbaCasopisa = new Posudba<Casopis>(clan,
					pCasopis, vrijemeP);
			listPosudbeCasopisa.add(PosudbaCasopisa);
		}
		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);
		return listPosudbeCasopisa;
	}

	public static void obrisiPosudbuCasopisa(Posudba<Casopis> casop)
			throws Exception {
		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		String sqlUpit = "DELETE FROM RAZVOJ.POSUDBA_CASOPIS WHERE clan=? AND casopis=? AND datumposudbe=?";
		PreparedStatement prepStmt = vezaSaBazom.prepareStatement(sqlUpit);
		prepStmt.setInt(1, casop.getClanVar().getIdClana());
		prepStmt.setInt(2, casop.getPublikacijaVar().getIdCasopisa());
		prepStmt.setDate(3, Date.valueOf(casop.getDatumPosudbe()));
		prepStmt.executeUpdate();
		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);

	}
}
