package Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.sun.corba.se.impl.ior.GenericTaggedComponent;

public class GestorNotificaciones implements Observer {

	public List<IGestorObserver> notificacionAlta;
	public List<IGestorObserver> notificacionValidacion;
	private static GestorNotificaciones gestorNotificacion;
	
	public  static GestorNotificaciones getGestorNotificaciones() {
		 if (gestorNotificacion==null) {
			 gestorNotificacion = new GestorNotificaciones();
		 }
		 return gestorNotificacion;
	}
	
	/**
	 * Construye un gestor de notificaciones a partir de una lista de notificaciones de carga 
	 * y una lista de notificaciones de verificacion.
	 */
	private GestorNotificaciones(){
		this.notificacionAlta=new ArrayList<IGestorObserver>();
		this.notificacionValidacion=new ArrayList<IGestorObserver>();
	}
	
	/**
	 * Agrega un objeto a las listas de notificaciones de carga y de notificaciones de verificacion.
	 * 
	 */
	public void agregarObserver(IGestorObserver obs){
		this.notificacionAlta.add(obs);
		this.notificacionValidacion.add(obs);
	}
	
	/**
	 * Se dispara cuando algun objeto que estoy observando me envia un msj
	 */
	@Override
	public void update(Observable o, Object obj) {
		MensajeObserver mensaje = (MensajeObserver) obj;
		if (mensaje.getMensaje().equals(EMensajesObservables.Alta)){
			this.enviarNotificacionMasiva(mensaje,this.notificacionAlta);
		}
		else if(mensaje.getMensaje().equals(EMensajesObservables.Modificacion)){
			this.enviarNotificacionMasiva(mensaje,this.notificacionValidacion);
		}
		
	}

	/**
	 * Envia un mensaje correspondiente a todos los elementos de la lista de notificacion.
	 */
	private void enviarNotificacionMasiva(MensajeObserver mensaje, List<IGestorObserver> listaNotificacion) {
		listaNotificacion.stream().forEach(obs -> obs.updateNotificacion(mensaje));
	}
	
	
	

}
