package app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import muestra.Muestra;
import zonaDeCobertura.ZonaCobertura;


public class AplicacionWeb  {
	
	private List <Muestra> muestras;
	private List <ZonaCobertura> zonasCobertura;
	
	
	/**
	 * Construye una aplicacion web a partir por una lista de muestra, una lista de
	 * zonas de cobertura y un gestor de notificaciones.
	 * 
	 * 
	 */
	
	public AplicacionWeb(){
		this.muestras =   new ArrayList<Muestra>();
		this.zonasCobertura = new ArrayList<ZonaCobertura>();
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
		this.agregarZonasInteresadas(muestra);
	}
	
	/**
	 * Suscribe una muestra a todas las zonas donde esa muestra esta incluida
	 * @param muestra
	 */
	private void agregarZonasInteresadas(Muestra muestra) {
		this.zonasCobertura.stream().filter(zona -> zona.incluyeMuestra(muestra))
									.forEach(zona -> muestra.addObserver(zona));
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
		this.suscribirZonaAMuestras(zona);
	}

	/**
	 * Suscribe una zona a las muestras que estan ubicadas dentro su perimetro
	 * @param zona
	 */
	private void suscribirZonaAMuestras(ZonaCobertura zona) {
		this.muestras.stream().filter(muestra -> zona.incluyeMuestra(muestra))
							  .forEach(muestra -> muestra.addObserver(zona));		
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
