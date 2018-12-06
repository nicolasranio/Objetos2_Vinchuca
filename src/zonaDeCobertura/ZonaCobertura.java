package zonaDeCobertura;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import Observer.GestorObserver;
import Observer.MensajeObserver;
import app.AplicacionWeb;
import muestra.*;

public class ZonaCobertura extends Observable implements GestorObserver{

	//observable por las organizaciones
	//observador del gestor
	
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
	
	
	/**
	 * Devuelve lista de zonas que se solapan con esta
	 * @return
	 */
	public List<ZonaCobertura> zonasSolapadas() {
		return this.app.getZonasCobertura().stream()
				.filter(zona -> zona.getEpicentro().calcularDistancia(this.epicentro)<(this.radio + zona.getRadio()))
				.collect(Collectors.toList());
	}

	

	/**
	 * Retorna true si la muestra esta incluida en esta zona de cobertura
	 * @param muestra
	 * @return
	 */
	public Boolean incluyeMuestra(Muestra muestra) {
		return this.distanciaDeMuestra(muestra) < radio;
	}
	

	@Override
	public void updateNotificacion(Object object) {
		MensajeObserver mensaje = (MensajeObserver) object;
		if (this.incluyeMuestra(mensaje.getMuestra())){
			enviarNotificacion(mensaje);
		}
	}

	
	private void enviarNotificacion(MensajeObserver mensaje) {
		this.setChanged();
		this.notifyObservers(mensaje);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		//no se usa, es un metodo de Observer no de GestorObserver
	}
	
	
	
	
	


}
