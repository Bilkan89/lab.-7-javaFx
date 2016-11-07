package hr.tvz.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.tvz.java.vjezbe.apstraktne.Publikacija;
import hr.tvz.java.vjezbe.enumeracije.VrstaPublikacije;
import hr.tvz.java.vjezbe.iznimke.NeisplativoObjavljivanjeException;

/**
 *
 * Klasa Casopis sadr�i varijable mMjesecCasopis(string tip) i konstantu cijene
 * primjerka(double tipa) te naslje�uje apstraktnu klasu Publikacija. Sadr�i
 * konstruktor, nadja�anu metodu equals i toString, te metodu provjeru
 * isplativosti objavljivanja i metodu getMjesecCasopisa
 *
 * @author Matej Bili�
 * @see Publikacija
 *
 */
public class Casopis extends Publikacija {

	private int MjesecCasopisa;
	private int idCasopisa;
	//private static final double CIJENA_PO_PRIMJERKU = 10.00;

	/**
	 *
	 * Konstriktor klase Casopis koji inicijalizira podatke o nazivu
	 * publikacije, godini, broj stranica, mjesec casopisa, vrsti publikacije.
	 * Poziva i konstruktor nadklase u koji se predaje umno�ak konstante cijena
	 * po primjerku i broja stranica kako bi se dobila cijena po stranici koja
	 * se kasnije predaje u metodu izra�un cijene publikacije. Kroz konstruktor
	 * se projverava i isplativost objavljivnja publikcije kroz metodu
	 * ProvjeraIsplativostiObjavljivanja;
	 *
	 * Poziva se konstruktor publikacije {@link Publikacija}.
	 *
	 * @param nazivPublikacije
	 *            podatak o nazivu publikacije.
	 * @param godinaIzdanja
	 *            podatak o godini izdanja publikacije
	 * @param brojStranicaPublikacije
	 *            podatak o broju stranica publikacije.
	 * @param publikacija
	 *            predaje se podatak o vrsti publikacije kroz konstantu u
	 *            enumeraciji
	 * @param mjesecCasop
	 *            podatak o mjesecu izanja publikacije
	 *
	 * @see Publikacija
	 * @see VrstaPublikacije
	 *
	 *
	 */

	public Casopis(int idCasopisa, String nazivPublikacije, int godinaIzdanja, int brojStranicaPublikacije, int mjesecCasop,
			VrstaPublikacije publikacija, double cijenPostranici) {
		super(nazivPublikacije, godinaIzdanja, brojStranicaPublikacije, publikacija,
				cijenPostranici);

		this.MjesecCasopisa = mjesecCasop;
		this.idCasopisa = idCasopisa;

		ProvjeraIsplativostiObjavljivanja(getCijena());

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Casopis)) {
			return false;
		}
		Casopis other = (Casopis) obj;
		if (MjesecCasopisa != other.MjesecCasopisa) {
			return false;
		}
		return true;
	}

	/**
	 *
	 * Metoda provjerava isplativos objavljvanja publikacije, metoda ne vra�a
	 * nikaku vrijednost nego baca iznimku
	 * {@link NeisplativoObjavljivanjeException}. Prima parametar getCijena �to
	 * je izra�un cijene tipa BigDecimal te ga uspore�uje sa vrijednost 1.00 �to
	 * predstavlja cijenu publikcije 1kn Ako je parametar getmCijena manji od
	 * 1kn baca iznimku {@link NeisplativoObjavljivanjeException}
	 *
	 * @throws NeisplativoObjavljivanjeException - baca iznimku pro�irenu sa RuntimeException
	 *	@param getmCijena parametar koji tipa BigDecimal i koji se usporeduje sa uvjetom zadanim za usporedbu.
	 */

	private void ProvjeraIsplativostiObjavljivanja(BigDecimal getCijena) {

		BigDecimal jedan = BigDecimal.valueOf(5.00);
		
		if (getCijena.compareTo(jedan) < 1) {
			throw new NeisplativoObjavljivanjeException("Unijeli ste neisplativu publikaciju!!");
		}

	}

	/**
	 *
	 * Metoda vra�a mjesec casopisa
	 *
	 * @return mMjesecCasopisa cjelobrojni tip koji predstavlja uneseni mjesec
	 *         casopisa
	 */
	public int getMjesecCasopisa() {
		return MjesecCasopisa;
	}

	@Override
	public String toString() {
		return super.toString() + "Mjesec �asopisa: " + MjesecCasopisa + "\n";

	}

	public int getIdCasopisa() {
		return idCasopisa;
	}
	
	public void setIdCasopisa(int idCasopisa) {
		this.idCasopisa = idCasopisa;
	}


}


