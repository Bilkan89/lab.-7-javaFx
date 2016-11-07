package hr.tvz.java.vjezbe.apstraktne;

import java.math.BigDecimal;

import hr.tvz.java.vjezbe.enumeracije.VrstaPublikacije;
import hr.tvz.java.vjezbe.sucelja.ZaProdaju;

/**
 * Abstraktna klasa koja predstavlja publikaciju i implementira su�elje za
 * prodaju u kojoj je definiran izra�un cijene. Definirana je nazivom
 * publikacije, brojem stranica, vrstom publikacije (kroz enumeraciju
 * VrstaPublikacije) i cijenom publikacije (izra�un kroz su�elje za prodaje ).
 *
 *
 * @author Matej Bili�
 * @version 1.0
 *
 *
 */

public abstract class Publikacija implements ZaProdaju {

	private String nazivPublikacije;
	private int godinaIzdanja;
	private int brojStranicaPublikacije;
	private VrstaPublikacije vrstaPublikacije;
	private BigDecimal cijena;

	/**
	 * Konstruktor klase publikacije. Inicijalizira podatak o nazivu
	 * publikacije, godina izdanja, broj stranica publikacija, vrsti publikacije
	 * koja mo�e biti papirnata ili elektroni�ka kroz enumeraciju
	 * {@link VrstaPublikacije} te o cijeni stranice koju ra�una pomo�u metode
	 * {@link CijenaPublikacije}
	 *
	 *
	 * @param NazivPublikacije
	 *            podatak o nazivu publikacije.
	 * @param GodinaIzdanja
	 *            podatak o godini izdanja publikacije
	 * @param BrojStranicaPublikacije
	 *            podatak o broju stranica publikacije.
	 * @param VrstaPublikacije
	 *            podatak o vrsti publikacije, papirnata ili elektroni�ka.
	 * @param cijenaPoStr
	 *            podatak o cijeni publikacije.
	 * @see ZaProdaju
	 * @see VrstaPublikacije
	 *
	 */

	public Publikacija(String NazivPublikacije, int GodinaIzdanja,
			int BrojStranicaPublikacije, VrstaPublikacije VrstaPublikacije,
			double cijenaPoStr) {

		this.nazivPublikacije = NazivPublikacije;
		this.godinaIzdanja = GodinaIzdanja;
		this.brojStranicaPublikacije = BrojStranicaPublikacije;
		this.vrstaPublikacije = VrstaPublikacije;
		this.cijena = CijenaPublikacije(BrojStranicaPublikacije,
				VrstaPublikacije, BigDecimal.valueOf(cijenaPoStr));

	}

	/**
	 * Vra�a string koji sadr�i naziv publikacije.
	 *
	 * @return Niz znakova koji sadr�i naziv odre�ene publikacije.
	 */
	public String getNazivPublikacije() {
		return nazivPublikacije;
	}

	/**
	 * Vra�a cjelobrojni broj koji predstavlja godinu publikacije.
	 *
	 *
	 * @return Cjelobrojni broj.
	 */
	public int getGodinaIzdanja() {
		return godinaIzdanja;
	}

	/**
	 * Vra�a cjelobrojni broj koji predstavlja broj stranica publikacije.
	 *
	 *
	 * @return Cjelobrojni broj.
	 */
	public final int getBrojStranicaPublikacije() {
		return brojStranicaPublikacije;
	}

	/**
	 * Vra�a vrstu publikacije definiranu kroz enumeraciju
	 * {@link VrstaPublikacije}.
	 *
	 *
	 * @return vra�a vrijednost definiranu kroz enumeraciju.
	 */
	public VrstaPublikacije getVrstaPublikacije() {
		return vrstaPublikacije;
	}

	/**
	 * Vra�a izra�unatu cijenu publikacije u tipu podataka big decimal.
	 * Varijabla koja sadr�i izra�unatu cijenu publikacije.
	 *
	 * @return Niz znakova koji sadr�i naziv odre�ene publikacije.
	 */
	public BigDecimal getCijena() {
		return cijena;
	}

	/**
	 * Nadja�ana metoda equals koja uspore�uje varijable publikacije.
	 *
	 * @return Vra�a true or false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Publikacija)) {
			return false;
		}
		Publikacija other = (Publikacija) obj;
		if (brojStranicaPublikacije != other.brojStranicaPublikacije) {
			return false;
		}
		if (cijena == null) {
			if (other.cijena != null) {
				return false;
			}
		} else if (!cijena.equals(other.cijena)) {
			return false;
		}
		if (godinaIzdanja != other.godinaIzdanja) {
			return false;
		}
		if (nazivPublikacije == null) {
			if (other.nazivPublikacije != null) {
				return false;
			}
		} else if (!nazivPublikacije.equals(other.nazivPublikacije)) {
			return false;
		}
		if (vrstaPublikacije != other.vrstaPublikacije) {
			return false;
		}
		return true;
	}

	/**
	 * Nadja�ana metoda toString koja ispisuje varijable publikacije.
	 *
	 * @return Ispisuje nizove znakova - String.
	 */
	@Override
	public String toString() {
		return "Naziv publikacije: " + nazivPublikacije + "\n" + "Vrsta: "
				+ vrstaPublikacije + "\n" + "Broj stranica publikacije: "
				+ brojStranicaPublikacije + "\n" + "Cijena publikacije: "
				+ cijena + " kn\n" + "Godina izdanja: " + godinaIzdanja + "\n";
	}

}
