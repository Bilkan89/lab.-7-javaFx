package hr.tvz.java.vjezbe.entitet;

/**
 *
 * Klasa Izdava� koja je definirana kroz dva niza znakova koji predstavljaju
 * naziv izdava�a i dr�avu izdava�a Sadr�i konstruktor, metode get i nadja�ane
 * metode toString i equals.
 *
 * @author Matej Bili�
 * @version 1.0
 *
 */
public class Izdavac {

	private int idIzdavac;
	private String NazivIzdavaca;
	private String DrzavaIzdavaca;

	/**
	 * Inicijalizira podatak o nazivu i dr�avi izdva�a
	 *
	 * @param mNazivIzdavaca string vrijednsot predstalvja naziv izdavaca
	 * @param mDrzavaIzdavaca  string vrijednsot predstalvja dr�avu izdavaca
	 */
	public Izdavac(int idIzdavac, String NazivIzdavaca, String DrzavaIzdavaca) {

		this.idIzdavac = idIzdavac;
		this.NazivIzdavaca = NazivIzdavaca;
		this.DrzavaIzdavaca = DrzavaIzdavaca;
	}

	/**
	 * Metoda vra�a niz znakova koji su naziv izdava�a.
	 *
	 * @return mNazivIzdavaca prestavlja varijablu koja sadr�i niz znakova.
	 *
	 */
	public String getNazivIzdavaca() {
		return NazivIzdavaca;
	}

	/**
	 * Metoda vra�a niz znakova koji predstavljaju dr�avu izdava�a
	 *
	 * @return mDrzavaIzdavaca prdstavlja varijablu koja sadr�i niz znakova.
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
