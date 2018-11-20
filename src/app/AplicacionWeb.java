package app;

import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;
import zonaCobertura.ZonaCobertura;

public class AplicacionWeb {
	
	private List <Muestra> muestras;
	private List <ZonaCobertura> zonasCobertura;  //zonaCobertura tiene acceso ala app para conocer las zonas solapadas y muestras
	
	public AplicacionWeb(){
		this.muestras = new ArrayList<Muestra>();
	}

	public void agregarMuestra(Muestra muestra) {
		this.muestras.add(muestra);
	}

}
