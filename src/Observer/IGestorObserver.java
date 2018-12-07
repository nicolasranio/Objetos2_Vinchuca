package Observer;

import java.util.Observer;

public interface IGestorObserver extends Observer {

	public void updateNotificacion(MensajeObserver mensaje);
	
}
