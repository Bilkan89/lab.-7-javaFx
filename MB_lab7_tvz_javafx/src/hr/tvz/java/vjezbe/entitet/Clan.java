package hr.tvz.java.vjezbe.entitet;

/**
 *
 * Klasa Clan koja je definirana kroz varijable ime, prezime i oib. Sadrži
 * konstruktor, get metode i nadjaèanu toString metodu
 *
 * @author Matej Biliæ
 * @version 1.0
 *
 */
public class Clan {
	private int idClana;
	private String Ime;
	private String Prezime;
	private int Oib;

	/**
	 * Konstruktor kojim se inicijalizira ime, prezime i oib clana
	 *
	 *
	 * @param mIme
	 *            vrijednost string koje predstavlja ime clana
	 * @param mPrezime
	 *            vrijednost string koje predstavlja prezime clana
	 * @param mOib
	 *            vrijednost string koje predstavlja oib clana
	 */
	public Clan(int idClana, String Ime, String Prezime, int Oib) {
		this.idClana = idClana;
		this.Ime = Ime;
		this.Prezime = Prezime;
		this.Oib = Oib;
	}

	/**
	 * Vraæa vrijednost koja predstavlja ime èlana
	 *
	 * @return mIme vraæa varijablu koja sadrži niz znak znakova
	 */
	public String getIme() {
		return Ime;
	}

	/**
	 * Vraæa vrijednost koja predstavlja prezime èlana
	 *
	 * @return mPrezime vraæa varijablu koja sadrži niz znak znakova
	 */
	public String getPrezime() {
		return Prezime;
	}

	/**
	 *
	 * Vraæa vrijednost koja predstavlja oib èlana
	 *
	 * @return mOib vraæa varijablu koja sadrži niz znakova
	 *
	 */
	public int getOib() {
		return Oib;
	}
	
	 public int getIdClana() {
		return idClana;
	}
	 public void setIdClana(int idClana) {
		this.idClana = idClana;
	}

	@Override
	public String toString() {
		return "Prezime èlana: " + Prezime + "\n" + "Ime èlana: " + Ime + "\n" + "OIB èlana: " + getOib();
	}

}
