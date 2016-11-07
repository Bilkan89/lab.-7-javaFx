package hr.tvz.java.vjezbe.entitet;

/**
 *
 * Klasa Izdavaæ koja je definirana kroz dva niza znakova koji predstavljaju
 * naziv izdavaæa i državu izdavaèa Sadrži konstruktor, metode get i nadjaèane
 * metode toString i equals.
 *
 * @author Matej Biliæ
 * @version 1.0
 *
 */
public class Izdavac {

	private int idIzdavac;
	private String NazivIzdavaca;
	private String DrzavaIzdavaca;

	/**
	 * Inicijalizira podatak o nazivu i državi izdvaæa
	 *
	 * @param mNazivIzdavaca string vrijednsot predstalvja naziv izdavaca
	 * @param mDrzavaIzdavaca  string vrijednsot predstalvja državu izdavaca
	 */
	public Izdavac(int idIzdavac, String NazivIzdavaca, String DrzavaIzdavaca) {

		this.idIzdavac = idIzdavac;
		this.NazivIzdavaca = NazivIzdavaca;
		this.DrzavaIzdavaca = DrzavaIzdavaca;
	}

	/**
	 * Metoda vraèa niz znakova koji su naziv izdavaèa.
	 *
	 * @return mNazivIzdavaca prestavlja varijablu koja sadrži niz znakova.
	 *
	 */
	public String getNazivIzdavaca() {
		return NazivIzdavaca;
	}

	/**
	 * Metoda vraèa niz znakova koji predstavljaju državu izdavaèa
	 *
	 * @return mDrzavaIzdavaca prdstavlja varijablu koja sadrži niz znakova.
	 */
	public String getDrzavaIzdavaca() {
		return DrzavaIzdavaca;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Izdavac)) {
			return false;
		}
		Izdavac other = (Izdavac) obj;
		if (DrzavaIzdavaca == null) {
			if (other.DrzavaIzdavaca != null) {
				return false;
			}
		} else if (!DrzavaIzdavaca.equals(other.DrzavaIzdavaca)) {
			return false;
		}
		if (NazivIzdavaca == null) {
			if (other.NazivIzdavaca != null) {
				return false;
			}
		} else if (!NazivIzdavaca.equals(other.NazivIzdavaca)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return  NazivIzdavaca + ", " + DrzavaIzdavaca;

	}
	
	public int getIdIzdavac() {
		return idIzdavac;
	}
	
	public void setIdIzdavac(int idIzdavac) {
		this.idIzdavac = idIzdavac;
	}

}
