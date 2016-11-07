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

import hr.tvz.java.vjezbe.entitet.Clan;
import hr.tvz.java.vjezbe.entitet.Izdavac;
import hr.tvz.java.vjezbe.entitet.Knjiga;
import hr.tvz.java.vjezbe.entitet.Posudba;
import hr.tvz.java.vjezbe.enumeracije.Jezik;
import hr.tvz.java.vjezbe.enumeracije.VrstaPublikacije;

public class PodaciIzBazeKnjige {

	public static List<Knjiga> dohvatiKnjigeIzBaze() throws Exception {
		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		List<Knjiga> ListaKnjiga = new ArrayList<Knjiga>();
		String SQLUpit = "SELECT * FROM RAZVOJ.KNJIGA";
		PreparedStatement prepareStmt = vezaSaBazom.prepareStatement(SQLUpit);
		ResultSet resultSet = prepareStmt.executeQuery();

		int idKnjige;
		String naziv;
		Integer godinaIzdanja;
		Integer vrstaPublikacijeId;
		VrstaPublikacije vrstaPub = null;
		Integer brojStranica;
		Integer jezikId;
		Jezik jezikKnjige = null;
		Integer izdavacId;
		Izdavac izdavacKnjige = null;
		float cijenaStranice;
		Knjiga knjigaBaza = null;

		while (resultSet.next()) {

			idKnjige = resultSet.getInt("id");
			naziv = resultSet.getString("naziv");
			godinaIzdanja = resultSet.getInt("godinaIzdanja");
			vrstaPublikacijeId = resultSet.getInt("vrstaPublikacije");
			for (VrstaPublikacije element : VrstaPublikacije
					.VrijednostiVrstePub()) {
				if (vrstaPublikacijeId == element.getVrstaPublikacijeId()) {
					vrstaPub = element;
				}
			}
			brojStranica = resultSet.getInt("brojStranica");
			jezikId = resultSet.getInt("jezik");
			for (Jezik element : Jezik.VrijednostiJezika()) {
				if (element.getJezikId() == jezikId) {
					jezikKnjige = element;
				}
			}
			izdavacId = resultSet.getInt("izdavac");
			izdavacKnjige = dohvatiIzdavacaIzBaze(izdavacId);
			cijenaStranice = (jezikKnjige == Jezik.HRVATSKI) ? 0.45f : 0.75f;

			knjigaBaza = new Knjiga(idKnjige, naziv, jezikKnjige, izdavacKnjige,
					godinaIzdanja, vrstaPub, brojStranica, cijenaStranice);
			ListaKnjiga.add(knjigaBaza);
		}

		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);
		return ListaKnjiga;
	}

	private static Izdavac dohvatiIzdavacaIzBaze(Integer izdavacId)
			throws Exception {
		Connection konekcija = KomunikacijaSaBazom.connectingToDataBase();
		String sqlUpit = "SELECT * FROM RAZVOJ.IZDAVAC WHERE id = ?";
		PreparedStatement prepStmt = konekcija.prepareStatement(sqlUpit);
		prepStmt.setInt(1, izdavacId);
		ResultSet resSet = prepStmt.executeQuery();

		Izdavac izdavacKnjige = null;

		while (resSet.next()) {
			int idIzdavac = resSet.getInt("id");
			String nazivIzdavaca = resSet.getString("naziv");
			String drzavaIzdavaca = resSet.getString("drzava");
			izdavacKnjige = new Izdavac(idIzdavac, nazivIzdavaca,
					drzavaIzdavaca);
		}
		KomunikacijaSaBazom.closingConnectionToDataBase(konekcija);
		return izdavacKnjige;
	}

	public static void spremiKnjiguUBazu(Knjiga knjigazaBazu) throws Exception {
		Connection vezaSabazom = KomunikacijaSaBazom.connectingToDataBase();
		vezaSabazom.setAutoCommit(false);
		String sqlUpitiIzdavac = "INSERT INTO RAZVOJ.IZDAVAC (naziv, drzava) VALUES (?,?)";
		PreparedStatement prepStmt = vezaSabazom
				.prepareStatement(sqlUpitiIzdavac);
		prepStmt.setString(1,
				knjigazaBazu.getIzdavacKnjige().getNazivIzdavaca());
		prepStmt.setString(2,
				knjigazaBazu.getIzdavacKnjige().getDrzavaIzdavaca());
		prepStmt.executeUpdate();

		ResultSet generiranKljuc = prepStmt.getGeneratedKeys();
		if (generiranKljuc.next()) {
			knjigazaBazu.getIzdavacKnjige()
					.setIdIzdavac(generiranKljuc.getInt(1));
		}
		String sqlUpitKnjiga = "INSERT INTO RAZVOJ.KNJIGA (naziv, godinaizdanja,vrstapublikacije, brojstranica,jezik, izdavac) VALUES (?,?,?,?,?,?)";
		PreparedStatement insertKnjigaPrepStmt = vezaSabazom
				.prepareStatement(sqlUpitKnjiga);
		insertKnjigaPrepStmt.setString(1, knjigazaBazu.getNazivPublikacije());
		insertKnjigaPrepStmt.setInt(2, knjigazaBazu.getGodinaIzdanja());
		insertKnjigaPrepStmt.setInt(3,
				knjigazaBazu.getVrstaPublikacije().getVrstaPublikacijeId());
		insertKnjigaPrepStmt.setInt(4,
				knjigazaBazu.getBrojStranicaPublikacije());
		insertKnjigaPrepStmt.setInt(5,
				knjigazaBazu.getJezikKnjige().getJezikId());
		insertKnjigaPrepStmt.setInt(6,
				knjigazaBazu.getIzdavacKnjige().getIdIzdavac());
		insertKnjigaPrepStmt.executeUpdate();
		vezaSabazom.commit();
		vezaSabazom.setAutoCommit(true);
		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSabazom);
	}

	public static int najveciIdBazeKnjige() throws Exception {
		int id = 0;
		Connection vezaBaza = KomunikacijaSaBazom.connectingToDataBase();
		Statement Stmt = vezaBaza.createStatement();
		ResultSet resSet = Stmt
				.executeQuery("SELECT MAX(id) FROM RAZVOJ.KNJIGA");
		while (resSet.next()) {
			id = resSet.getInt(1);
		}
		KomunikacijaSaBazom.closingConnectionToDataBase(vezaBaza);
		return id;
	}

	public static int najveciIdBazeIzdavac() throws Exception {
		int id = 0;
		Connection vezaBaza = KomunikacijaSaBazom.connectingToDataBase();
		Statement Stmt = vezaBaza.createStatement();
		ResultSet resSet = Stmt
				.executeQuery("SELECT MAX(id) FROM RAZVOJ.IZDAVAC");
		while (resSet.next()) {
			id = resSet.getInt(1);
		}
		KomunikacijaSaBazom.closingConnectionToDataBase(vezaBaza);
		return id;
	}

	public static void promijeniKnjigu(Knjiga knjiga) throws Exception {

		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		vezaSaBazom.setAutoCommit(false);// transakcija

		String sqlUpit = "UPDATE RAZVOJ.IZDAVAC SET naziv=?, drzava=? WHERE id=?";
		String sqlUpit2 = "UPDATE RAZVOJ.KNJIGA SET naziv=?, godinaizdanja=?, vrstapublikacije=?, brojstranica=?, jezik=?, izdavac=? WHERE id=?";
		PreparedStatement prepStmt = vezaSaBazom.prepareStatement(sqlUpit);

		prepStmt.setString(1, knjiga.getIzdavacKnjige().getNazivIzdavaca());
		prepStmt.setString(2, knjiga.getIzdavacKnjige().getDrzavaIzdavaca());
		prepStmt.setInt(3, knjiga.getIzdavacKnjige().getIdIzdavac());
		prepStmt.executeUpdate();

		prepStmt = vezaSaBazom.prepareStatement(sqlUpit2);
		prepStmt.setString(1, knjiga.getNazivPublikacije());
		prepStmt.setInt(2, knjiga.getGodinaIzdanja());
		prepStmt.setInt(3,
				knjiga.getVrstaPublikacije().getVrstaPublikacijeId());
		prepStmt.setInt(4, knjiga.getBrojStranicaPublikacije());
		prepStmt.setInt(5, knjiga.getJezikKnjige().getJezikId());
		prepStmt.setInt(6, knjiga.getIzdavacKnjige().getIdIzdavac());
		prepStmt.setInt(7, knjiga.getIdKnjige());
		prepStmt.executeUpdate();

		vezaSaBazom.commit();
		vezaSaBazom.setAutoCommit(true);

		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);
	}

	public static void obrisiKnjigu(Knjiga k) throws Exception {
		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		vezaSaBazom.setAutoCommit(false);
		String sqlUpit = "DELETE FROM RAZVOJ.KNJIGA WHERE id=?";
		PreparedStatement prepStmt = vezaSaBazom.prepareStatement(sqlUpit);
		prepStmt.setInt(1, k.getIdKnjige());
		prepStmt.executeUpdate();
		sqlUpit = "DELETE FROM RAZVOJ.IZDAVAC WHERE id=?";
		prepStmt = vezaSaBazom.prepareStatement(sqlUpit);
		prepStmt.setInt(1, k.getIzdavacKnjige().getIdIzdavac());
		prepStmt.executeUpdate();
		vezaSaBazom.commit();
		vezaSaBazom.setAutoCommit(true);
		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);
	}

	public static void spremanjePosudbeKnjige(Posudba<Knjiga> posudba)
			throws Exception {
		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();

		String sqlUpit = "INSERT INTO RAZVOJ.POSUDBA_KNJIGA (clan, knjiga, datumposudbe) VALUES (?,?,?)";
		PreparedStatement prepStmt = vezaSaBazom.prepareStatement(sqlUpit);

		prepStmt.setInt(1, posudba.getClanVar().getIdClana());
		prepStmt.setInt(2,
				((Knjiga) posudba.getPublikacijaVar()).getIdKnjige());
		prepStmt.setDate(3, Date.valueOf(posudba.getDatumPosudbe()));
		prepStmt.executeUpdate();
		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);
	}

	public static List<Posudba<Knjiga>> dohvacanjePosudbeKnjiga()
			throws Exception {

		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		String sqlUpit = "SELECT * FROM RAZVOJ.POSUDBA_KNJIGA";
		PreparedStatement prepStmt = vezaSaBazom.prepareStatement(sqlUpit);
		ResultSet resSet = prepStmt.executeQuery();

		List<Posudba<Knjiga>> listaPosudbeKnjiga = new ArrayList<>();
		Knjiga pKnjiga = null;
		Clan clan = null;
		int idKnjige = 0;
		int idClana = 0;
		Date datum = null;

		while (resSet.next()) {
			idKnjige = resSet.getInt("knjiga");
			idClana = resSet.getInt("clan");
			datum = resSet.getDate("datumposudbe");

			String sqlUpit2 = "SELECT * FROM RAZVOJ.KNJIGA WHERE id="
					+ idKnjige;
			PreparedStatement prepStmt2 = vezaSaBazom
					.prepareStatement(sqlUpit2);
			ResultSet resSet2 = prepStmt2.executeQuery();

			// POPUNA KNJIGE
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
				int jezikID = resSet2.getInt("jezik");
				Jezik jezikKnjige = null;
				for (Jezik element : Jezik.VrijednostiJezika()) {
					if (element.getJezikId() == jezikID) {
						jezikKnjige = element;
					}
				}
				int izdavacID = resSet2.getInt("izdavac");
				Izdavac izdavacKnjige = dohvatiIzdavacaIzBaze(izdavacID);
				float cijenaStranice = (jezikKnjige == Jezik.HRVATSKI) ? 0.45f
						: 0.75f;

				pKnjiga = new Knjiga(idKnjige, naziv, jezikKnjige,
						izdavacKnjige, godina, vrstaPub, brojStr,
						cijenaStranice);
			}
			String sqlUpit3 = "SELECT * FROM RAZVOJ.CLAN WHERE id=" + idClana;
			PreparedStatement prepStmt3 = vezaSaBazom
					.prepareStatement(sqlUpit3);
			ResultSet resSet3 = prepStmt3.executeQuery();

			// POPUNA ÈLANA
			while (resSet3.next()) {
				int oibC = resSet3.getInt("oib");
				String nazivC = resSet3.getString("ime");
				String prezimeC = resSet3.getString("prezime");
				clan = new Clan(idClana, nazivC, prezimeC, oibC);
			}

			LocalDate vrijemeP = LocalDate
					.parse(new SimpleDateFormat("yyyy-MM-dd").format(datum));

			Posudba<Knjiga> PosudbaKnjige = new Posudba<Knjiga>(clan, pKnjiga,
					vrijemeP);
			listaPosudbeKnjiga.add(PosudbaKnjige);
		}

		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);
		return listaPosudbeKnjiga;
	}

	public static void obrisiPosudbuKnjige(Posudba<Knjiga> book)
			throws Exception {
		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		String sqlUpit = "DELETE FROM RAZVOJ.POSUDBA_KNJIGA WHERE clan=? AND knjiga=? AND datumposudbe=?";
		PreparedStatement prepStmt = vezaSaBazom.prepareStatement(sqlUpit);

		prepStmt.setInt(1, book.getClanVar().getIdClana());
		prepStmt.setInt(2, book.getPublikacijaVar().getIdKnjige());
		prepStmt.setDate(3, Date.valueOf(book.getDatumPosudbe()));
		prepStmt.executeUpdate();

		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);

	}
}
