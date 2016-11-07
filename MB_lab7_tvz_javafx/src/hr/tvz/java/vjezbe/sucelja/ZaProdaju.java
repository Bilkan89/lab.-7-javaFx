package hr.tvz.java.vjezbe.sucelja;

import java.math.BigDecimal;

import hr.tvz.java.vjezbe.enumeracije.VrstaPublikacije;

/**
 *
 * Suèelje koje sadrži samo jednu metodu za izraèun cijene publikacija.
 *
 * @author Matej Biliæ
 * @version 1.0
 *
 *
 */
public interface ZaProdaju {

	/**
	 *
	 * Vraèa umnožak tipa BigDecimal koji se raèuna na osnovu ulaznih parametara broj stranica, vrsta publikacije i cijena po stranici.
	 * <p>
	 * Ako je publikacija <b>Papirnata</b> u varijablu umnožak se sprema umnožak broja stranica(cjelobrojni broj) i cijene po stranici(double broj).
	 * Te se kroz metodu valueOf() <code>BigDecimal.valueOf(bs * cijenaPoStranici)</code> pretvara u vrijednost BigDecimal
	 * Ako je publikacija <b>Elektronièka</b> onda se gore navedeni umnožak dijeli sa dva i tek se onda sprema u varijablu umnožak tipa BigDecimala.
 	 *
	 * @param BrojStranica cijelobrojna vrijednost koju korisnik unosi prilikom unosa knjige
	 * @param VrstaPublikacije vrsta publikacije definirana kroz enumeraciju koju takðer korisnik unosi prilikom unosa knjige
	 * @param cijenaPoStranici varijabla cijena po stranici definirana je na osnovu napisanog jezika publikacije koji je konstanta
	 * @see VrstaPublikacije
	 * @return umnožak vrijedost BigDecimal koja predstavlja cijenu stranice
	 *
	 */

	public default BigDecimal CijenaPublikacije(int BrojStranica, VrstaPublikacije VrstaPublikacije, BigDecimal cijenaPoStranici){
			
			BigDecimal umnožak=null;
			BigDecimal bs = BigDecimal.valueOf(BrojStranica);
			if(VrstaPublikacije.equals(hr.tvz.java.vjezbe.enumeracije.VrstaPublikacije.PAPIRNATA)){

				umnožak= bs.multiply(cijenaPoStranici);

			}else if(VrstaPublikacije.equals(hr.tvz.java.vjezbe.enumeracije.VrstaPublikacije.ELEKTRONIÈKA)){
				
				umnožak= bs.multiply(cijenaPoStranici);
				umnožak = umnožak.divide(BigDecimal.valueOf(1,5));
			}

		return umnožak;
	}

}
