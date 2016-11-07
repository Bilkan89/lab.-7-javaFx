package hr.tvz.java.vjezbe.apstraktne;

import java.math.BigDecimal;

import hr.tvz.java.vjezbe.enumeracije.VrstaPublikacije;
import hr.tvz.java.vjezbe.sucelja.ZaProdaju;

/**
 * Abstraktna klasa koja predstavlja publikaciju i implementira suèelje za
 * prodaju u kojoj je definiran izraèun cijene. Definirana je nazivom
 * publikacije, brojem stranica, vrstom publikacije (kroz enumeraciju
 * VrstaPublikacije) i cijenom publikacije (izraèun kroz suèelje za prodaje ).
 *
 *
 * @author Matej Biliæ
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
	 * koja može biti papirnata ili elektronièka kroz enumeraciju
	 * {@link VrstaPublikacije} te o cijeni stranice koju raèuna pomoæu metode
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
	 *            podatak o vrsti publikacije, papirnata ili elektronièka.
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
	 * Vraæa string koji sadrži naziv publikacije.
	 *
	 * @return Niz znakova koji sadrži naziv odreðene publikacije.
	 */
	public String getNazivPublikacije() {
		return nazivPublikacije;
	}

	/**
	 * Vraæa cjelobrojni broj koji predstavlja godinu publikacije.
	 *
	 *
	 * @return Cjelobrojni broj.
	 */
	public int getGodinaIzdanja() {
		return godinaIzdanja;
	}

	/**
	 * Vraæa cjelobrojni broj koji predstavlja broj stranica publikacije.
	 *
	 *
	 * @return Cjelobrojni broj.
	 */
	public final int getBrojStranicaPublikacije() {
		return brojStranicaPublikacije;
	}

	/**
	 * Vraæa vrstu publikacije definiranu kroz enumeraciju
	 * {@link VrstaPublikacije}.
	 *
	 *
	 * @return vraèa vrijednost definiranu kroz enumeraciju.
	 */
	public VrstaPublikacije getVrstaPublikacije() {
		return vrstaPublikacije;
	}

	/**
	 * Vraæa izraèunatu cijenu publikacije u tipu podataka big decimal.
	 * Varijabla koja sadrži izraèunatu cijenu publikacije.
	 *
	 * @return Niz znakova koji sadrži naziv odreðene publikacije.
	 */
	public BigDecimal getCijena() {
		return cijena;
	}

	/**
	 * Nadjaèana metoda equals koja usporeðuje varijable publikacije.
	 *
	 * @return Vraèa true or false.
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
	 * Nadjaèana metoda toString koja ispisuje varijable publikacije.
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
