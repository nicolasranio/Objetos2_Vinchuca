package testOrganizacion;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import muestra.Muestra;
import muestra.Ubicacion;
import observer.EMensajesObservables;
import observer.MensajeObserver;
import observer.MensajeObserverAlta;
import observer.MensajeObserverModificacion;
import organizacion.*;
import zonaDeCobertura.ZonaCobertura;

public class TestOrganizacion {

	private Organizacion organizacion;
	private Ubicacion ubicacion;
	private TipoOrganizacion tipoOrganizacion;
	private Integer trabajadores;
	private FuncionalidadExterna funcionalidadCarga;
	private FuncionalidadExterna funcionalidadVerificacion;
	
	private MensajeObserverAlta mensajeAlta;
	private MensajeObserverModificacion mensajeModificacion;
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
		
		mensajeAlta = mock(MensajeObserverAlta.class);
		mensajeModificacion = mock(MensajeObserverModificacion.class);
		muestra = mock(Muestra.class);
		zona = mock(ZonaCobertura.class);
	
	}

	@Test
	public void testSeEjecutaElUpdateDeOrganizacionCuandoSeCargaUnaMuestra() {
		
		
		when(mensajeAlta.getMuestra()).thenReturn(muestra);
		doCallRealMethod().when(mensajeAlta).ejecutarFuncionalidad(zona, organizacion);
		
		organizacion.setFuncionalidadCarga(funcionalidadCarga);
		organizacion.update(zona, mensajeAlta);
		
		verify(mensajeAlta).ejecutarFuncionalidad(zona, organizacion);
		verify(funcionalidadCarga).nuevoEvento(organizacion, zona, muestra);
	}
	
	@Test
	public void testSeEjecutaElUpdateDeOrganizacionCuandoSeVerificaUnaMuestra() {
		
		
		when(mensajeModificacion.getMuestra()).thenReturn(muestra);
		doCallRealMethod().when(mensajeModificacion).ejecutarFuncionalidad(any(ZonaCobertura.class), any(Organizacion.class));
		
		organizacion.setFuncionalidadValidacion(funcionalidadVerificacion);
		organizacion.update(zona, mensajeModificacion);
		
		verify(mensajeModificacion).ejecutarFuncionalidad(zona, organizacion);
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
