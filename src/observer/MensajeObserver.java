package observer;

import muestra.Muestra;
import organizacion.Organizacion;
import zonaDeCobertura.ZonaCobertura;

public abstract class MensajeObserver {

	Muestra muestra;
	
	/**
	 * Construye una mensaje observer con sus elementos
	 * 
	 * @param mensaje 
	 * donde la organizazión realiza su ejercicio
	 * 
	 * @param muestra
	 * la misma puede dedicarse a distintas areas, como salud, educativa, entre otras.
	 * 
	 */
	public MensajeObserver(Muestra muestra) {
		this.muestra = muestra;
	}

	/**
	 * Retorna la muestra que manda el mensaje de notificacion.
	 * 
	 * @return La muestra que manda el mensaje de notificacion.
	 */
	public Muestra getMuestra() {
		return muestra;
	}
	

	public abstract void ejecutarFuncionalidad(ZonaCobertura zona, Organizacion organizacion);
	
}
