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
	
	/**
	 * Construye una aplicacion web a partir por una lista de muestra, una lista de
	 * zonas de cobertura y un gestor de notificaciones.
	 * 
	 * 
	 */
	
	public AplicacionWeb(){
		this.muestras =   new ArrayList<Muestra>();
		this.zonasCobertura = new ArrayList<ZonaCobertura>();
		this.gestorNotificaciones= GestorNotificaciones.getGestorNotificaciones();
	}


	/**
	 * Registra la muestra dada agregandola a la lista de muestras resgistradas a la aplicacion web,
	 * y se agrega el gestor de notificacion como observer de muestra.
	 * 
	 * @param muestra
	 * Es la muestra a agregar y la que agrega como observer a gestor de notificacion.
	 * 
	 */
	
	public void agregarMuestra(Muestra muestra) {
		this.muestras.add(muestra);
		muestra.addObserver(this.gestorNotificaciones);
	}
	
	/**
	 * Retorna una lista de todas las muestras registradas en la aplicacion web.
	 * 
	 * @return La lista de todas las muestras registradas.
	 * 
	 */
	public List<Muestra> getMuestras() {
		return this.muestras;
	}
	
	/**
	 * Agrega una nueva zona de cobertura y la suscribe como observer de la aplicacion
	 * 
	 * @param zona 
	 * Es la nueva zona a agregar
	 * 
	 */
	public void agregarZonaCobertura(ZonaCobertura zona) {
		this.zonasCobertura.add(zona);
		this.gestorNotificaciones.agregarObserver(zona);
	}

	
	/**
	 * Retorna una lista de todas las zonas de cobertura registradas en la aplicacion web.
	 * 
	 * @return La lista de todas las zonas de cobertura registradas.
	 * 
	 */
	public List<ZonaCobertura> getZonasCobertura() {
		return this.zonasCobertura;
	}

	/**
	 * Filtra todas las muestras de la aplicacion web que apliquen al filtro, retornando una lista
	 * que apliquen al filtro dado.
	 * 
	 * @param filtro
	 * El filtro al que deberá someterse cada muestra de la aplicacion web para evaluar si debe 
	 * contenerse a la lista retornada.
	 * 
	 * @return Retorna una lista con todas las muestras que hayan aplicado al filtro dado.
	 * 
	 */
	public List<Muestra> filtrarMuestras(Filtro filtro){
		return this.muestras.stream().filter(muestra -> filtro.aplicar(muestra)).collect(Collectors.toList());
	}
	
	
}
