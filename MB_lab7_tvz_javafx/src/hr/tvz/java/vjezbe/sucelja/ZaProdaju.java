package hr.tvz.java.vjezbe.sucelja;

import java.math.BigDecimal;

import hr.tvz.java.vjezbe.enumeracije.VrstaPublikacije;

/**
 *
 * Su�elje koje sadr�i samo jednu metodu za izra�un cijene publikacija.
 *
 * @author Matej Bili�
 * @version 1.0
 *
 *
 */
public interface ZaProdaju {

	/**
	 *
	 * Vra�a umno�ak tipa BigDecimal koji se ra�una na osnovu ulaznih parametara broj stranica, vrsta publikacije i cijena po stranici.
	 * <p>
	 * Ako je publikacija <b>Papirnata</b> u varijablu umno�ak se sprema umno�ak broja stranica(cjelobrojni broj) i cijene po stranici(double broj).
	 * Te se kroz metodu valueOf() <code>BigDecimal.valueOf(bs * cijenaPoStranici)</code> pretvara u vrijednost BigDecimal
	 * Ako je publikacija <b>Elektroni�ka</b> onda se gore navedeni umno�ak dijeli sa dva i tek se onda sprema u varijablu umno�ak tipa BigDecimala.
 	 *
	 * @param BrojStranica cijelobrojna vrijednost koju korisnik unosi prilikom unosa knjige
	 * @param VrstaPublikacije vrsta publikacije definirana kroz enumeraciju koju tak�er korisnik unosi prilikom unosa knjige
	 * @param cijenaPoStranici varijabla cijena po stranici definirana je na osnovu napisanog jezika publikacije koji je konstanta
	 * @see VrstaPublikacije
	 * @return umno�ak vrijedost BigDecimal koja predstavlja cijenu stranice
	 *
	 */

	public default BigDecimal CijenaPublikacije(int BrojStranica, VrstaPublikacije VrstaPublikacije, BigDecimal cijenaPoStranici){
			
			BigDecimal umno�ak=null;
			BigDecimal bs = BigDecimal.valueOf(BrojStranica);
			if(VrstaPublikacije.equals(hr.tvz.java.vjezbe.enumeracije.VrstaPublikacije.PAPIRNATA)){

				umno�ak= bs.multiply(cijenaPoStranici);

			}else if(VrstaPublikacije.equals(hr.tvz.java.vjezbe.enumeracije.VrstaPublikacije.ELEKTRONI�KA)){
				
				umno�ak= bs.multiply(cijenaPoStranici);
				umno�ak = umno�ak.divide(BigDecimal.valueOf(1,5));
			}

		return umno�ak;
	}

}
