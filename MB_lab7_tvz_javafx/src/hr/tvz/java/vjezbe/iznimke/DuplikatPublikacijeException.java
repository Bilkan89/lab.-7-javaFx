package hr.tvz.java.vjezbe.iznimke;
/**
 * Prilagoðena iznimka koja nasljeðuje klasu {@link Exception}. Sastoje se od èetiri konstruktora.
 *
 * @author Matej Biliæ
 * @version 1.0
 *
 */
public class DuplikatPublikacijeException extends Exception{


	public DuplikatPublikacijeException(){
		super("Unesena publikacija veæ postoji!! Molim unesite drugu publikaciju!");
		//super("Neispravan rad pograma bacanje oznaèene iznimke na koju program ne utjeèe");
	}

	public DuplikatPublikacijeException(String poruka){
		super(poruka);
	}

	public DuplikatPublikacijeException(Throwable uzrok){
		super(uzrok);
	}

	public DuplikatPublikacijeException(String poruka, Throwable uzrok){
		super(poruka, uzrok);
	}




}
