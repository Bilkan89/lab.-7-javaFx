package hr.tvz.java.vjezbe.enumeracije;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumeracija koja sadrži vrste jezika publikacija.
 *
 * @author Matej Biliæ
 * @version 1.0
 *
 */
public enum Jezik {

	HRVATSKI(1),
	ENGLESKI(2),
	NJEMAÈKI(3),
	FRANCUSKI(4),
	TALIJANSKI(5),
	RUSKI(6),
	KINESKI(7);

	private int jezikId;
	
	private Jezik(int jezikId) {
		this.jezikId = jezikId;
	}
	
	public int getJezikId() {
		return jezikId;
	}
	
	public void setJezikId(int jezikId) {
		this.jezikId = jezikId;
	}
	
	
	public static List<Jezik> VrijednostiJezika(){
		
		List<Jezik> lista = new ArrayList<Jezik>();
			
		for(Jezik element : Jezik.values()){
			lista.add(element);
		}
		
		return lista;
		
	}
	
	
	
	
	
	
	
	
	
	
}


