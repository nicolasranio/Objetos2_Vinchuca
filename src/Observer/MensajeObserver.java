package Observer;

import muestra.Muestra;

public class MensajeObserver {

	Muestra muestra;
	EMensajesObservables mensaje;
	
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
	public MensajeObserver(EMensajesObservables mensaje, Muestra muestra) {
		this.mensaje = mensaje;
		this.muestra = muestra;
	}

	/**
	 * Retorna el tipo de mensaje de notificacion de muestra.
	 * 
	 * @return El tipo de mensaje de notificacion.
	 * 
	 */
	public EMensajesObservables getMensaje() {
		return mensaje;
	}

	/**
	 * Retorna la muestra que manda el mensaje de notificacion.
	 * 
	 * @return La muestra que manda el mensaje de notificacion.
	 */
	public Muestra getMuestra() {
		return muestra;
	}
	
}
