package hr.tvz.java.vjezbe.iznimke;
/**
 * Prilago�ena iznimka koja naslje�uje klasu {@link RuntimeException}. Sastoje se od �etiri konstruktora.
 *
 * @author Matej Bili�
 * @version 1.0
 *
 */
public class NeisplativoObjavljivanjeException extends RuntimeException {


	public NeisplativoObjavljivanjeException(){
		super("Pogre�ka prilikom unosa knjige! Ne isplati se objavljivati knjigu, neka mala knjiga!");
		//super("Neispravan rad pograma bacanje ozna�ene iznimke na koju program ne utje�e");
	}

	public NeisplativoObjavljivanjeException(String poruka){
		super(poruka);
	}

	public NeisplativoObjavljivanjeException(Throwable uzrok){
		super(uzrok);
	}

	public NeisplativoObjavljivanjeException(String poruka, Throwable uzrok){
		super(poruka, uzrok);
	}



}
