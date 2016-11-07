package hr.tvz.java.vjezbe.sucelja;

import hr.tvz.java.vjezbe.entitet.Knjiga;

public interface ZaPosudbu {

	/**
	 *
	 * Su�elje koje sadr�i prazne metode: posudba(ne vra�a ni�ta), vracanje(ne
	 * vra�a ni�ta) i provjeraRaspolozivosti(vra�a vrijednost true ili false)
	 * Metode je potrebno nadja�ati.
	 *
	 * @author Matej Bili�
	 * @version 1.0
	 * @see Knjiga
	 *
	 *
	 */

	public void posudba();

	public void vracanje();

	public boolean provjeriRaspolozivost();

}
