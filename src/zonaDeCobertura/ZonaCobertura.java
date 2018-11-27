package zonaDeCobertura;

import java.util.List;
import java.util.stream.Collectors;

import app.AplicacionWeb;
import muestra.*;

public class ZonaCobertura {

	private String nombre;
	private Ubicacion epicentro;
	private Double radio;
	private AplicacionWeb app;
	
	public ZonaCobertura(String nombre,Ubicacion epicentro,Double radio,AplicacionWeb app) {
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.radio = radio;
		this.app = app;
		this.app.agregarZonaCobertura(this);
	}
	
	public Ubicacion getEpicentro() {
		return this.epicentro;
	}
	
	public Double getRadio() {
		return this.radio;
	}
	
	
	/**
	 *  Retorna la distancia de la muestra al epicentro de la zona de cobertura
	 * @param muestra
	 * @return distancia 
	 */
	public Double distanciaDeMuestra(Muestra muestra){
		return this.epicentro.calcularDistancia(muestra.getUbicacion());
	}
	
	
	public List<Muestra> muestrasReportadas(){
		return this.app.getMuestras().stream().
				filter(muestra -> this.distanciaDeMuestra(muestra) < this.radio)
				.collect(Collectors.toList());
	}
	
	
	public List<ZonaCobertura> zonasSolapadas() {
		return this.app.getZonasCobertura().stream()
				.filter(zona -> zona.getEpicentro().calcularDistancia(this.epicentro)<(this.radio + zona.getRadio()))
				.collect(Collectors.toList());
	}
	
}
