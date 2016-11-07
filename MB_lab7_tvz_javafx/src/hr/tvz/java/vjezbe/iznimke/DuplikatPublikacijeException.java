package hr.tvz.java.vjezbe.iznimke;
/**
 * Prilago�ena iznimka koja naslje�uje klasu {@link Exception}. Sastoje se od �etiri konstruktora.
 *
 * @author Matej Bili�
 * @version 1.0
 *
 */
public class DuplikatPublikacijeException extends Exception{


	public DuplikatPublikacijeException(){
		super("Unesena publikacija ve� postoji!! Molim unesite drugu publikaciju!");
		//super("Neispravan rad pograma bacanje ozna�ene iznimke na koju program ne utje�e");
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
