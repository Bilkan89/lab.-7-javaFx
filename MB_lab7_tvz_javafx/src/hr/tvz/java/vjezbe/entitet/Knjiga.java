package hr.tvz.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.tvz.java.vjezbe.apstraktne.Publikacija;
import hr.tvz.java.vjezbe.enumeracije.Jezik;
import hr.tvz.java.vjezbe.enumeracije.VrstaPublikacije;
import hr.tvz.java.vjezbe.iznimke.NeisplativoObjavljivanjeException;
import hr.tvz.java.vjezbe.sucelja.ZaPosudbu;
/**
 * Klasa knjiga je pro�irena abstraktnom klasom {@link Publikacija} i implementira su�elje {@link ZaPosudbu}.
 * Klasa Knjiga koja je definirana sa jezikom knjiga kroz enumeraciju {@link Jezik} , izdava�em {@link Izdavac}, i varijablom tipa boolean koja predstavlja mogu�nost posudbe.
 * Sadr�i konstante cijena po stranici publikacije napisane na engleskom i na hrvatskom jeziku.
 * Sadr�i tako�er konstruktor klase, get metode, nadja�ane metode: provjeri raspolo�ivost, posudba, vracanje, toString, equals.
 *
 * @author Matej Bili�
 * @version 1.0
 *
 */
public class Knjiga extends Publikacija implements ZaPosudbu{

	private int idKnjige;
	private Jezik JezikKnjige;
	private Izdavac IzdavacKnjige;
	private boolean Pametna;

//	private final static double CIJENA_PO_STRANICI_HR = 0.50;
//	private final static double CIJENA_PO_STRANICI_FOREIGN = 0.85;

	/**
	 *
	 * Konstruktor koji inicijalizira podatke o publikciji tipa knjiga preko konstruktora nadklase.
	 * Unutrak konstruktora je pozvana metoda provjera isplativosti objavljivanja koja baca iznimku {@link NeisplativoObjavljivanjeException}
	 *
	 * @param mNazivPublikacije string vrijednsot predstalvja naziv knjige
	 * @param jezikENUMERACIJA string vrijednsot predstalvja jezik knjige
	 * @param mIzdavacKnjige string vrijednsot predstalvja izdavaca knjige
	 * @param godinaIzdanja string vrijednsot predstalvja godinu knjige
	 * @param publikacija string vrijednsot predstalvja vrsta publikacije knjige
	 * @param brojStranicaPublikacije string vrijednsot predstalvja broj stranica knjige
	 */
	public Knjiga(int idKnjige, String NazivPublikacije, Jezik JezikKnjige, Izdavac IzdavacKnjige, int GodinaIzdanja,
															VrstaPublikacije Publikacija,int BrojStranicaPublikacije, double cijenaPoStranici ) {
		super(NazivPublikacije, GodinaIzdanja, BrojStranicaPublikacije, Publikacija, cijenaPoStranici);
		
		this.idKnjige = idKnjige;
		this.JezikKnjige = JezikKnjige;
		this.IzdavacKnjige = IzdavacKnjige;

		Pametna = true;

		
		ProvjeraIsplativostiObjavljivanja(getCijena());
		
		//this.mCijenaPoStranici = (int) odabirCijenePoStranici(mJezikKnjige);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Knjiga)) {
			return false;
		}
		Knjiga other = (Knjiga) obj;
		if (IzdavacKnjige == null) {
			if (other.IzdavacKnjige != null) {
				return false;
			}
		} else if (!IzdavacKnjige.equals(other.IzdavacKnjige)) {
			return false;
		}
		if (JezikKnjige == null) {
			if (other.JezikKnjige != null) {
				return false;
			}
		} else if (!JezikKnjige.equals(other.JezikKnjige)) {
			return false;
		}
		if (Pametna != other.Pametna) {
			return false;
		}
		return true;
	}

	/**
	 * Metoda provjerava isplativos objavljvanja publikacije, metoda ne vra�a
	 * nikaku vrijednost nego baca iznimku
	 * {@link NeisplativoObjavljivanjeException}. Prima parametar getCijena �to
	 * je izra�un cijene tipa BigDecimal te ga uspore�uje sa vrijednost 100.00 �to
	 * predstavlja cijenu publikcije 1kn Ako je parametar getmCijena manji od
	 * 1kn baca iznimku {@link NeisplativoObjavljivanjeException}
	 *
	 * @param getmCijena
	 * @throws NeisplativoObjavljivanjeException
	 */
	private void ProvjeraIsplativostiObjavljivanja(BigDecimal getCijena) throws NeisplativoObjavljivanjeException{
		
		BigDecimal stotka = BigDecimal.valueOf(100);

			if (getCijena.compareTo(stotka) != 1) {  //100 compareTO 100  je 0// 50 compareTO 100  je -1 // 200 compareTO 100  je 1
				throw new NeisplativoObjavljivanjeException();
				}

	}

				/*	*//**
					 * Metoda vra�a double vrijednost na osnovu parametra mJezikKnjige.
					 * Ako je knjiga na engleskom onda se uzima konstanta cijena po stranici na engleskom,
					 * ako nije onda se za izra�un cijene publikacije koristi konstanta cijena po stranici na hrvatskom
					 *
					 * @param mJezikKnjige
					 * @return vrijednost koja predstavlja cijenu po stranici knjige
					 *//*
					private static double odabirCijenePoStranici(Jezik mJezikKnjige) {
						double vrati;
				
							if(mJezikKnjige.equals(Jezik.HRVATSKI)){
								vrati = CIJENA_PO_STRANICI_HR;
							}else {
									vrati = CIJENA_PO_STRANICI_FOREIGN;
								}
				
						return vrati;
					}*/

	/**
	 * Metoda vra�a na kojem je jeziku knjiga.
	 *
	 * @return mJezikKnjige vra�a varijablu koja je definirana kroz enumeraciju {@link Jezik}
	 */
	public Jezik getJezikKnjige() {
		return JezikKnjige;
	}

	/**
	 * Metoda vra�a naziv izdava�a knjige.
	 *
	 * @return mIzdavacKnjige vra�a varijablu koja je definirana kroz {@link Izdavac} klasu.
	 */
	public Izdavac getIzdavacKnjige() {
		return IzdavacKnjige;
	}

	/**
	 * Nadja�ana metoda posudba koja stavlja vrijednost false
	 */
	@Override
	public void posudba() {

		Pametna = false;
		}

	/**
	 * Nadja�ana metoda posudba koja stavlja vrijednost true
	 */
	@Override
	public void vracanje() {
		Pametna = true;
		}

	/**
	 * Metoda vra�a raspolo�ivost knjige true or false
	 *
	 * @return mPametna varijabla koja sadr�i vrijednost true or false
	 */
	@Override
	public boolean provjeriRaspolozivost() {
//			if(mPametna==true){
//				System.out.print("DA / ");}
//			else{
//				System.out.print("NE / " );
//				}
		return Pametna;

		}

	@Override
	public String toString() {
		return 	 super.toString() +
				"Jezik knjige: " + JezikKnjige +"\n"+
				"Izdava� knjige: " + getIzdavacKnjige().getNazivIzdavaca() +"\n"+
				"Dr�ava izdava�a knjige: " + getIzdavacKnjige().getDrzavaIzdavaca() +"\n"+
				"Raspolo�ivo za posudbu: " + Pametna +"\n";
	}


	public int getIdKnjige() {
		return idKnjige;
	}
	
	public void setIdKnjige(int idKnjige) {
		this.idKnjige = idKnjige;
	}



}
