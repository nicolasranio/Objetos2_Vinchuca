package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Observer.GestorNotificaciones;
import Observer.MensajeObserver;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import muestra.Muestra;

import zonaDeCobertura.ZonaCobertura;


public class AplicacionWeb  {
	
	private List <Muestra> muestras;
	private List <ZonaCobertura> zonasCobertura;
	private GestorNotificaciones gestorNotificaciones;
	
	public AplicacionWeb(){
		this.muestras =   new ArrayList<Muestra>();
		this.zonasCobertura = new ArrayList<ZonaCobertura>();
		this.gestorNotificaciones= GestorNotificaciones.getGestorNotificaciones();
	}


	public void agregarMuestra(Muestra muestra) {
		this.muestras.add(muestra);
		muestra.addObserver(this.gestorNotificaciones);
	}
	

	public List<Muestra> getMuestras() {
		return this.muestras;
	}
	
	/**
	 * Agrega una nueva zona de cobertura y la suscribe como observer de la aplicacion
	 * @param zona
	 */
	public void agregarZonaCobertura(ZonaCobertura zona) {
		this.zonasCobertura.add(zona);
		this.gestorNotificaciones.agregarObserver(zona);
	}

	public List<ZonaCobertura> getZonasCobertura() {
		return this.zonasCobertura;
	}
	
	/**
	 * Devuelve una lista de zonas de cobertura a las cuales la muestra pertenece
	 * @param muestra
	 * @return
	 */
//	public List<ZonaCobertura> buscarZonasQueAplican(Muestra muestra){
//		return this.zonasCobertura.stream()
//		.filter(zona -> zona.incluyeMuestra(muestra))
//		.collect(Collectors.toList());		
//	}
//	
	
	public List<Muestra> filtrarMuestras(Filtro filtro){
		return this.muestras.stream().filter(muestra -> filtro.aplicar(muestra)).collect(Collectors.toList());
	}
	
	
}
