package hr.tvz.java.vjezbe.enumeracije;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Enumeracija koja sadr�i dvije mogu�e vrste publikacije
 * ELEKTRONI�KA i PAPIRNATA
 *
 * @author Matej Bili�
 * @version 1.0
 */
public enum VrstaPublikacije {

	ELEKTRONI�KA(1),
	PAPIRNATA(2);	
	
	private int vrstaPublikacijeId;
	
	private VrstaPublikacije(int vrstaPublikacijeId) {
		this.vrstaPublikacijeId = vrstaPublikacijeId;
	}
	
	public int getVrstaPublikacijeId() {
		return vrstaPublikacijeId;
	}
	
	public void setVrstaPublikacijeId(int vrstaPublikacijeId) {
		this.vrstaPublikacijeId = vrstaPublikacijeId;
	}	
	
	public static List<VrstaPublikacije> VrijednostiVrstePub(){
		
		List<VrstaPublikacije> lista = new ArrayList<VrstaPublikacije>();
			
		for(VrstaPublikacije element : VrstaPublikacije.values()){
			lista.add(element);
		}
		
		return lista;
	}
	
	
}
