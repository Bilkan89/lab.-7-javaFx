package hr.tvz.java.vjezbe.entitet;

/**
 *
 * Klasa Clan koja je definirana kroz varijable ime, prezime i oib. Sadr�i
 * konstruktor, get metode i nadja�anu toString metodu
 *
 * @author Matej Bili�
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
	 * Vra�a vrijednost koja predstavlja ime �lana
	 *
	 * @return mIme vra�a varijablu koja sadr�i niz znak znakova
	 */
	public String getIme() {
		return Ime;
	}

	/**
	 * Vra�a vrijednost koja predstavlja prezime �lana
	 *
	 * @return mPrezime vra�a varijablu koja sadr�i niz znak znakova
	 */
	public String getPrezime() {
		return Prezime;
	}

	/**
	 *
	 * Vra�a vrijednost koja predstavlja oib �lana
	 *
	 * @return mOib vra�a varijablu koja sadr�i niz znakova
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
		return "Prezime �lana: " + Prezime + "\n" + "Ime �lana: " + Ime + "\n" + "OIB �lana: " + getOib();
	}

}
