package testOrganizacion;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import Observer.EMensajesObservables;
import Observer.MensajeObserver;
import muestra.Muestra;
import muestra.Ubicacion;
import organizacion.*;
import zonaDeCobertura.ZonaCobertura;

public class TestOrganizacion {

	private Organizacion organizacion;
	private Ubicacion ubicacion;
	private TipoOrganizacion tipoOrganizacion;
	private Integer trabajadores;
	private FuncionalidadExterna funcionalidadCarga;
	private FuncionalidadExterna funcionalidadVerificacion;
	
	private MensajeObserver mensaje;
	private Muestra muestra;
	private ZonaCobertura zona;
	
	@Before
	public void setUp() throws Exception {
		
		ubicacion = mock(Ubicacion.class);
		trabajadores = 200;
		funcionalidadCarga = mock(FuncionalidadExterna.class);
		funcionalidadVerificacion = mock(FuncionalidadExterna.class);
		tipoOrganizacion = tipoOrganizacion.salud;
		
		organizacion = new Organizacion(ubicacion,tipoOrganizacion,trabajadores,funcionalidadCarga,funcionalidadVerificacion);
		
		mensaje = mock(MensajeObserver.class);
		muestra = mock(Muestra.class);
		zona = mock(ZonaCobertura.class);
	
	}

	@Test
	public void testSeEjecutaElUpdateDeOrganizacionCuandoSeCargaUnaMuestra() {
		
		when(mensaje.getMensaje()).thenReturn(EMensajesObservables.Alta);
		when(mensaje.getMuestra()).thenReturn(muestra);
		
		organizacion.setFuncionalidadCarga(funcionalidadCarga);
		organizacion.update(zona, mensaje);
		
		verify(funcionalidadCarga).nuevoEvento(organizacion, zona, muestra);
	}
	
	@Test
	public void testSeEjecutaElUpdateDeOrganizacionCuandoSeVerificaUnaMuestra() {
		
		when(mensaje.getMensaje()).thenReturn(EMensajesObservables.Modificacion);
		when(mensaje.getMuestra()).thenReturn(muestra);
		
		organizacion.setFuncionalidadValidacion(funcionalidadVerificacion);
		organizacion.update(zona, mensaje);
		
		verify(funcionalidadVerificacion).nuevoEvento(organizacion, zona, muestra);
	}
	
	@Test
	public void testOrganizacionSeSuscribeAZona() {
		
		organizacion.suscribirAZona(zona);
		
		verify(zona).addObserver(organizacion);
	}
	
	@Test
	public void testOrganizacionSeDesuscribeDeZona() {
		
		organizacion.desuscribirDeZona(zona);
		
		verify(zona).deleteObserver(organizacion);
	}

}
