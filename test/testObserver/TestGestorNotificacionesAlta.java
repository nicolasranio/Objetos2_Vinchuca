package testObserver;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.AplicacionWeb;
import muestra.Muestra;
import muestra.Ubicacion;
import observer.EMensajesObservables;
import observer.GestorNotificacionesAlta;
import observer.MensajeObserver;
import observer.MensajeObserverAlta;
import observer.MensajeObserverModificacion;
import organizacion.Organizacion;
import zonaDeCobertura.ZonaCobertura;

import static org.mockito.Mockito.*;

public class TestGestorNotificacionesAlta {

	
	private GestorNotificacionesAlta gestorAlta;
	private ZonaCobertura zona;
	private MensajeObserverAlta mensajeAlta;
	private Muestra muestra;
	
	@Before
	public void setUp(){
		gestorAlta = GestorNotificacionesAlta.getGestorNotificaciones();
		zona = mock(ZonaCobertura.class);
		mensajeAlta = mock(MensajeObserverAlta.class);
		muestra = mock(Muestra.class);
	}
	
	@Test
	public void testCreoDosVecesUnGestorDeAltaYObtengoElMismo(){
		
		GestorNotificacionesAlta gestorAdicional = GestorNotificacionesAlta.getGestorNotificaciones();
		assertEquals(gestorAdicional,gestorAlta);
		assertSame(gestorAdicional, gestorAlta);
	}
	
	@Test
	public void testSeRealizaUpdateConMensajeDeAltaYEnviaNotificacionAObservadores(){
		
		when(mensajeAlta.getMuestra()).thenReturn(muestra);
		
		gestorAlta.agregarObserver(zona);
		gestorAlta.update(muestra, mensajeAlta);
		
		verify(zona).update(isA(GestorNotificacionesAlta.class),isA(MensajeObserverAlta.class));
		
	}
	
	
	
	
}
