package hr.tvz.java.vjezbe.sucelja;

import hr.tvz.java.vjezbe.entitet.Knjiga;

public interface ZaPosudbu {

	/**
	 *
	 * Suèelje koje sadrži prazne metode: posudba(ne vraèa ništa), vracanje(ne
	 * vraæa ništa) i provjeraRaspolozivosti(vraèa vrijednost true ili false)
	 * Metode je potrebno nadjaèati.
	 *
	 * @author Matej Biliæ
	 * @version 1.0
	 * @see Knjiga
	 *
	 *
	 */

	public void posudba();

	public void vracanje();

	public boolean provjeriRaspolozivost();

}
