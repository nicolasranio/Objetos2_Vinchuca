package observer;

import java.util.Observable;
import java.util.Observer;

public class GestorNotificacionesModificacion extends Observable implements Observer {

	private static GestorNotificacionesModificacion gestorNotificacion;
	
	public  static GestorNotificacionesModificacion getGestorNotificaciones() {
		 if (gestorNotificacion==null) {
			 gestorNotificacion = new GestorNotificacionesModificacion();
		 }
		 return gestorNotificacion;
	}
	
	
	public GestorNotificacionesModificacion() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public void update(Observable obs, Object obj) {
		MensajeObserver mensaje = (MensajeObserver) obj;
		if (mensaje instanceof MensajeObserverModificacion){
			this.setChanged();
			this.notifyObservers(obj);
		}	
	}
	
	
	public void agregarObserver(Observer obs){
		this.addObserver(obs);
	}

}
