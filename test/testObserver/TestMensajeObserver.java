package testObserver;

import org.junit.Before;
import org.junit.Test;

import Observer.EMensajesObservables;
import Observer.MensajeObserver;
import muestra.Muestra;
import muestra.NivelVerificacionBajo;
import muestra.TipoVinchuca;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class TestMensajeObserver {

	private Muestra muestra;
	private EMensajesObservables mensaje;
	private MensajeObserver mensajero;
	
	@Before
	public void setUp(){
		muestra = mock(Muestra.class);
		mensaje = EMensajesObservables.Alta;
		mensajero = new MensajeObserver(mensaje, muestra);
	}
	
	@Test
	public void testCreacionDeObjetoYGetters(){
		assertTrue(mensajero.getMensaje().equals(mensaje));
		assertTrue(mensajero.getMuestra().equals(muestra));
	}
	
	
	
	
	
	
}
