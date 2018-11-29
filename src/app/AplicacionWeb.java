package app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
		this.buscarZonasQueAplican(muestra).stream()
			.forEach(zona -> zona.suscribe(muestra));
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

	public List<ZonaCobertura> buscarZonasQueAplican(Muestra muestra){
		return this.zonasCobertura.stream()
		.filter(zona -> zona.incluyeMuestra(muestra))
		.collect(Collectors.toList());
		
	}
	
	
}
