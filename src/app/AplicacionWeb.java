package app;

import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;

import zonaDeCobertura.ZonaCobertura;


public class AplicacionWeb {
	
	private List <Muestra> muestras;

	private List <ZonaCobertura> zonasCobertura;
	
	public AplicacionWeb(){
		this.muestras = new ArrayList<Muestra>();
		this.zonasCobertura = new ArrayList<ZonaCobertura>();
	}

	public void agregarMuestra(Muestra muestra) {
		this.muestras.add(muestra);
	}

	public List<Muestra> getMuestras() {
		return this.muestras;
	}
	
	public void agregarZonaCobertura(ZonaCobertura zona) {
		this.zonasCobertura.add(zona);
	}

	public List<ZonaCobertura> getZonasCobertura() {
		return this.zonasCobertura;
	}

}
