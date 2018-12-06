package testObserver;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Observer.GestorNotificaciones;
import Observer.IGestorObserver;
import static org.mockito.Mockito.*;

public class TestGestorNotificaciones {

	
	private GestorNotificaciones gestor;
	private IGestorObserver observer;
	
	@Before
	public void setUp(){
		gestor = GestorNotificaciones.getGestorNotificaciones();
		observer = mock(IGestorObserver.class);
	}
	
	@Test
	public void testCreoDosVecesUnGestorDeNotificacionesYObtengoElMismo(){
		
		GestorNotificaciones gestorAdicional = GestorNotificaciones.getGestorNotificaciones();
		assertEquals(gestorAdicional,gestor);
		assertSame(gestorAdicional, gestor);
	}
	
	@Test
	public void testAgregarUnObserver(){
	
	}
	
}
