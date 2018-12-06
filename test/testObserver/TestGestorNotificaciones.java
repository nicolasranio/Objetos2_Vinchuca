package testObserver;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Observer.EMensajesObservables;
import Observer.GestorNotificaciones;
import Observer.IGestorObserver;
import Observer.MensajeObserver;
import muestra.Muestra;
import organizacion.Organizacion;
import zonaDeCobertura.ZonaCobertura;

import static org.mockito.Mockito.*;

public class TestGestorNotificaciones {

	
	private GestorNotificaciones gestor;
	private ZonaCobertura zona;
	private MensajeObserver mensaje;
	private EMensajesObservables mensajeAlta;
	private EMensajesObservables mensajeModificacion;
	private Muestra muestra;
	
	@Before
	public void setUp(){
		gestor = GestorNotificaciones.getGestorNotificaciones();
		zona = mock(ZonaCobertura.class);
		mensaje = mock(MensajeObserver.class);
		mensajeAlta = EMensajesObservables.Alta;
		mensajeModificacion = EMensajesObservables.Modificacion;
		muestra = mock(Muestra.class);
	}
	
	@Test
	public void testCreoDosVecesUnGestorDeNotificacionesYObtengoElMismo(){
		
		GestorNotificaciones gestorAdicional = GestorNotificaciones.getGestorNotificaciones();
		assertEquals(gestorAdicional,gestor);
		assertSame(gestorAdicional, gestor);
	
	}
	
	@Test
	public void testSeRealizaUpdateConMensajeDeAltaYEnviaNotificacionAObservadores(){
		when(mensaje.getMensaje()).thenReturn(mensajeAlta);
		when(mensaje.getMuestra()).thenReturn(muestra);
		
		gestor.agregarObserver(zona);
		gestor.update(muestra, mensaje);
		
		verify(zona).updateNotificacion(isA(MensajeObserver.class));
		
	}
	
	@Test
	public void testSeRealizaUpdateConMensajeDeModificacionYEnviaNotificacionAObservadores(){
		when(mensaje.getMensaje()).thenReturn(mensajeModificacion);
		when(mensaje.getMuestra()).thenReturn(muestra);
		
		gestor.agregarObserver(zona);
		gestor.update(muestra, mensaje);
		
		verify(zona).updateNotificacion(isA(MensajeObserver.class));
		
	}
	
}
