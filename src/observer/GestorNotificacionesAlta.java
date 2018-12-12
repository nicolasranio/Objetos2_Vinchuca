package observer;

import java.util.Observable;
import java.util.Observer;

public class GestorNotificacionesAlta extends Observable implements Observer {

	//public List<IGestorObserver> notificacionAlta;
	private static GestorNotificacionesAlta gestorNotificacion;
	
	public  static GestorNotificacionesAlta getGestorNotificaciones() {
		 if (gestorNotificacion==null) {
			 gestorNotificacion = new GestorNotificacionesAlta();
		 }
		 return gestorNotificacion;
	}
	
	/**
	 * Construye un gestor de notificaciones a partir de una lista de notificaciones de carga 
	 * y una lista de notificaciones de verificacion.
	 */
	private GestorNotificacionesAlta(){
		super();
	}
	
	/**
	 * Agrega un objeto a las listas de notificaciones de carga y de notificaciones de verificacion.
	 * 
	 */
	public void agregarObserver(Observer obs){
		this.addObserver(obs);
	}
	
	/**
	 * Se dispara cuando algun objeto que estoy observando me envia un msj
	 */
	@Override
	public void update(Observable o, Object obj) {
		MensajeObserver mensaje = (MensajeObserver) obj;
		if (mensaje instanceof MensajeObserverAlta){
			this.setChanged();
			this.notifyObservers(obj);
		}
	}

}
