package testObserver;

import static org.junit.Assert.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import muestra.Muestra;
import observer.GestorNotificacionesAlta;
import observer.GestorNotificacionesModificacion;
import observer.MensajeObserver;
import observer.MensajeObserverAlta;
import observer.MensajeObserverModificacion;
import zonaDeCobertura.ZonaCobertura;

public class TestGestorNotificacionesModificacion {

	private GestorNotificacionesModificacion gestorModificacion;
	private ZonaCobertura zona;
	private MensajeObserver mensajeModificacion;
	private Muestra muestra;
	
	@Before
	public void setUp(){
		gestorModificacion = GestorNotificacionesModificacion.getGestorNotificaciones();
		zona = mock(ZonaCobertura.class);
		mensajeModificacion = mock(MensajeObserverModificacion.class);
		muestra = mock(Muestra.class);
	}
	
	@Test
	public void testCreoDosVecesUnGestorDeNotificacionesYObtengoElMismo(){
		
		GestorNotificacionesModificacion gestorAdicional = GestorNotificacionesModificacion.getGestorNotificaciones();
		assertEquals(gestorAdicional,gestorModificacion);
		assertSame(gestorAdicional, gestorModificacion);
	}
	
	@Test
	public void testSeRealizaUpdateConMensajeDeModificacionYEnviaNotificacionAObservadores(){
		when(mensajeModificacion.getMuestra()).thenReturn(muestra);
		
		gestorModificacion.agregarObserver(zona);
		gestorModificacion.update(gestorModificacion, muestra);
		
		verify(zona).update(isA(MensajeObserverModificacion));
		
	}

}
