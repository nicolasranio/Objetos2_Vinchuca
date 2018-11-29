package zonaDeCobertura;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import app.AplicacionWeb;
import muestra.*;

public class ZonaCobertura extends Observable implements Observer{

	//observable por las organizaciones y observador de los cambios en las muestras
	
	private String nombre;
	private Ubicacion epicentro;
	private Double radio;
	private AplicacionWeb app;
	
	public ZonaCobertura(String nombre,Ubicacion epicentro,Double radio,AplicacionWeb app) {
		super();
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
				filter(muestra -> this.incluyeMuestra(muestra))
				.collect(Collectors.toList());
	}
	
	
	
	public List<ZonaCobertura> zonasSolapadas() {
		return this.app.getZonasCobertura().stream()
				.filter(zona -> zona.getEpicentro().calcularDistancia(this.epicentro)<(this.radio + zona.getRadio()))
				.collect(Collectors.toList());
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	public Boolean incluyeMuestra(Muestra muestra) {
		return this.distanciaDeMuestra(muestra) < radio;
	}
	
	public void suscribe (Muestra muestra){
		muestra.addObserver(this);
		this.notificarCarga(muestra);
		
	}
	
	private void notificarCarga(Muestra muestra) {
		this.setChanged();
		this.notifyObservers(muestra);		
	}

	public void desuscribe(Muestra muestra){
		muestra.deleteObserver(this);
	}
}
