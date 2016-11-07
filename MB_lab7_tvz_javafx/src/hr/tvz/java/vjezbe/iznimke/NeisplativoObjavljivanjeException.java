package hr.tvz.java.vjezbe.iznimke;
/**
 * Prilagoðena iznimka koja nasljeðuje klasu {@link RuntimeException}. Sastoje se od èetiri konstruktora.
 *
 * @author Matej Biliæ
 * @version 1.0
 *
 */
public class NeisplativoObjavljivanjeException extends RuntimeException {


	public NeisplativoObjavljivanjeException(){
		super("Pogreška prilikom unosa knjige! Ne isplati se objavljivati knjigu, neka mala knjiga!");
		//super("Neispravan rad pograma bacanje oznaèene iznimke na koju program ne utjeèe");
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
