package hr.tvz.java.vjezbe.entitet;

import java.time.LocalDate;

import hr.tvz.java.vjezbe.apstraktne.Publikacija;

/**
 *
 * Generièka klasa Posudba koja je prima objekte koji nasljeðuju klasu
 * Publikacija. Definirana je kroz {@link Clan} klasu, Generièku varijablu koja
 * prima samo objekte koji su nasljedili klasu {@link Publikacija}, varijable
 * koje predstavljaju trenutno vrijeme i odreðenog formata(dd.MM.yyyy. HH:mm) .
 * Sadrži i konstruktor, metode get i nadjaèanu metodu toString.
 *
 *
 * @author Matej Biliæ
 * @version 1.0
 *
 *
 */

public class Posudba<T extends Publikacija> {

	private Clan ClanVar;
	private T PublikacijaVar;
	private LocalDate DatumPosudbe;
	//private DateTimeFormatter mTimeFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm ");
	//private String mDatum = mDatumPosudbe.format(mTimeFormat);

	/**
	 *
	 * Konstruktor generièke klase posudba koji inicializira clana i publikaciju
	 *
	 * @param mClanVar predstavlja objekt clana
	 * @param KnjigaILICasopis predstavlja objekt publikacije
	 */

	public Posudba(Clan clanVar, T publikacijaVar, LocalDate datumPosudbe) {

		this.ClanVar = clanVar;
		this.PublikacijaVar = publikacijaVar;
		this.DatumPosudbe = datumPosudbe;
	}

	/**
	 * Metoda vraæa Èlana knjižnice
	 *
	 * @return mClanVar koji predstavlja èlana knjižnice koji je posudio knjigu
	 */
	public Clan getClanVar() {
		return ClanVar;
	}
	

	/**
	 * Vraèa posudenu publikaciju tipa knjiga ili casopis
	 *
	 * @return mPublikacijaVar sadrži publikaciju tipa knjiga ili casopis koja
	 *         je posudena
	 */
	public T getPublikacijaVar() {
		return PublikacijaVar;
	}

	public LocalDate getDatumPosudbe() {
		return DatumPosudbe;
	}
	
	/**
	 * Metoda vraæa datum posudbe publikcije
	 *
	 * @return mDatum koji predstavlja trenutno vrijeme publikacije
	 */
//	public String getmDatum() {
//		return mDatum;
//	}

	@Override
	public String toString() {
		return "STANJE POSUDBE: \n" + getPublikacijaVar().toString() + "\n" + "PODACI KORISNIKA: \n"
				+ getClanVar().toString() + "\n " + "DATUM POSUDBE: " + getDatumPosudbe();
	}

}
