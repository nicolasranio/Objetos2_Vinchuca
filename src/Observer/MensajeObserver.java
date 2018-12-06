package Observer;

import muestra.Muestra;

public class MensajeObserver {

	Muestra muestra;
	EMensajesObservables mensaje;
	
	public MensajeObserver(EMensajesObservables mensaje, Muestra muestra) {
		this.mensaje = mensaje;
		this.muestra = muestra;
	}

	public EMensajesObservables getMensaje() {
		return mensaje;
	}

	public Muestra getMuestra() {
		return muestra;
	}
	
}
