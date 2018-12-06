package testMuestra;


import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;

import Observer.GestorNotificaciones;
import Observer.MensajeObserver;
import muestra.*;
import participante.Participante;



public class TestMuestra {
	
	private Muestra muestra;
	private Muestra muestraB;
	private Muestra muestraC;
	private Ubicacion ubicacion;
	private Ubicacion ubicacionB;
	private VerificacionMuestra verificacion;
	
	@Before
	public void setUp() {
		
		ubicacion = mock(Ubicacion.class);
		ubicacionB = mock(Ubicacion.class);
		muestra = new Muestra(TipoVinchuca.Chinche_Foliada,"foto",ubicacion,"Rogelio");
		muestraB = new Muestra(TipoVinchuca.Imagen_poco_clara,"foto2",ubicacionB,"Fort");
		muestraC = new Muestra(TipoVinchuca.Ninguna,"foto3",ubicacion,"Comandante");
		verificacion = mock(VerificacionMuestra.class);
		
	}
	
	@Test
	public void testConstructorDeMuestraYGetters() {
		
		
		assertTrue(muestra.getTipoVinchuca().equals(TipoVinchuca.Chinche_Foliada));
		assertTrue(muestra.getFotoVinchuca().equals("foto"));
		assertTrue(muestra.getUbicacion().equals(ubicacion));
		assertTrue(muestra.getAliasRecolector().equals("Rogelio"));
		assertTrue(muestra.getNivelVerificacion() instanceof NivelVerificacionBajo);
	}
	
	
	
	@Test
	public void testMuestraCalculaLaDistanciaConMuestaBEntreSusUbicaciones() {
		
		muestra.distanciaAMuestra(muestraB);
		
		verify(ubicacion).calcularDistancia(muestraB.getUbicacion());
		
	}
	
	
	@Test 
	public void testMuestraFiltraMuestrasCercanasA1800km() {
		
		List <Muestra> listaMuestras = new ArrayList <Muestra>();
		listaMuestras.add(muestraB);
		listaMuestras.add(muestraC);
		
		when(ubicacion.calcularDistancia(muestraB.getUbicacion())).thenReturn(900.00);
		when(ubicacion.calcularDistancia(muestraC.getUbicacion())).thenReturn(1900.00);
		
		List<Muestra> muestras = muestra.muestrasCercanas(listaMuestras, new Double(1800.00));
		
		assertEquals(1,muestras.size());
	}
	
	@Test
	public void testMuestraASidoVerificadoPorUnParticipanteYAgregaPaticipanteAVerificadores() {
		
		int cantVerificaciones = muestra.getVerificaciones().size();
		Participante participante2 = mock(Participante.class);
		
		when(verificacion.getParticipante()).thenReturn(participante2);
		
		muestra.verificar(verificacion);
		
		assertEquals(cantVerificaciones+1,muestra.getVerificaciones().size());
		assertTrue(muestra.getVerificadores().contains(participante2));
	}

	@Test
	public void testMuestraCambiaSuNivelDeVerificacion() {
		
		muestra.setNivelVerificacion(mock(NivelVerificacionAlto.class));
		
		assertTrue(muestra.getNivelVerificacion() instanceof NivelVerificacionAlto);
	}
	
	@Test
	public void testFechaDeEnvioDeMuestraEsMenorA31Dias(){

		muestra.setFechaEnvio(LocalDate.of(2018, 11, 28));
		assertTrue(muestra.esMenorAXDias(31));
		assertFalse(muestra.esMenorAXDias(3));
	}
	
	@Test
	public void testMaximaCantidadDeValidacionesCoincidentesDeUnaMuestra() {
		
		VerificacionMuestra verificacionB = mock(VerificacionMuestra.class);
		VerificacionMuestra verificacionC = mock(VerificacionMuestra.class);
		
		when(verificacion.getTipoVinchuca()).thenReturn(TipoVinchuca.Chinche_Foliada);
		when(verificacionB.getTipoVinchuca()).thenReturn(TipoVinchuca.Vinchuca);
		when(verificacionC.getTipoVinchuca()).thenReturn(TipoVinchuca.Vinchuca);
		
		muestra.verificar(verificacion);
		muestra.verificar(verificacionB);
		muestra.verificar(verificacionC);
		
		assertEquals(2,muestra.verificacionesValidas());
	}

	@Test 
	public void testUnaMuestraNotificaCargaASusObservadores() {
		GestorNotificaciones gestor = mock(GestorNotificaciones.class);
		
		muestra.addObserver(gestor);
		muestra.informarCarga();
		
		verify(gestor).update(muestra, MensajeObserver.class);
	}
	
}
