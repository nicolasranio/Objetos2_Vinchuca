package observer;

import muestra.Muestra;

public class FactoryMensajes {
	
	public static MensajeObserver crearMensaje(EMensajesObservables tipo, Muestra muestra) {
		
		if (tipo.equals(EMensajesObservables.Alta)) return  new MensajeObserverAlta(muestra);
		else return new MensajeObserverModificacion(muestra); 
		
	}
}
