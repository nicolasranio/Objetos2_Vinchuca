package src;

import java.util.ArrayList;
import java.util.List;

public class AplicacionWeb {
	
	private List <Muestra> muestras;
	
	public AplicacionWeb(){
		this.muestras = new ArrayList<Muestra>();
	}

	public void agregarMuestra(Muestra muestra) {
		this.muestras.add(muestra);
	}
	
	
	
	
}
