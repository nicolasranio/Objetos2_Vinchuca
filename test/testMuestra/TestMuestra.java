package testMuestra;


import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;

import muestra.*;
import observer.GestorNotificacionesAlta;
import observer.MensajeObserver;
import participante.NivelConocimientoBasico;
import participante.NivelConocimientoExperto;
import participante.Participante;



public class TestMuestra {
	
	private Muestra muestra;
	private Muestra muestraB;
	private Muestra muestraC;
	private Ubicacion ubicacion;
	private Ubicacion ubicacionB;
	private Participante recolector;
	private NivelConocimientoExperto experto;
	private NivelConocimientoBasico basico;
	private VerificacionMuestra verificacion;
	private VerificacionMuestra verificacionB;
	private VerificacionMuestra verificacionC;
	
	@Before
	public void setUp() {
		
		ubicacion = mock(Ubicacion.class);
		ubicacionB = mock(Ubicacion.class);
		recolector = mock(Participante.class);
		experto = mock(NivelConocimientoExperto.class);
		basico = mock(NivelConocimientoBasico.class);
		muestra = new Muestra(TipoVinchuca.Chinche_Foliada,"foto",ubicacion,recolector);
		muestraB = new Muestra(TipoVinchuca.Imagen_poco_clara,"foto2",ubicacionB,recolector);
		muestraC = new Muestra(TipoVinchuca.Vinchuca,"foto3",ubicacion,recolector);
		verificacion = mock(VerificacionMuestra.class);
		verificacionB = mock(VerificacionMuestra.class);
		verificacionC = mock(VerificacionMuestra.class);
		
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

		assertTrue(muestra.esMenorAXDias(31));
	}
	
	@Test
	public void testMaximaCantidadDeValidacionesCoincidentesDeUnaMuestra() {
		
		when(verificacion.getTipoVinchuca()).thenReturn(TipoVinchuca.Chinche_Foliada);
		when(verificacionB.getTipoVinchuca()).thenReturn(TipoVinchuca.Vinchuca);
		when(verificacionC.getTipoVinchuca()).thenReturn(TipoVinchuca.Vinchuca);
		
		muestra.verificar(verificacion);
		muestra.verificar(verificacionB);
		muestra.verificar(verificacionC);
		
		assertEquals(2,muestra.verificacionesValidas());
	}

	@Test
	public void consensoDeVotosDeUnaMuestraDeterminaVinchucaIndefinidaCuandoLaEnviaUnBasicoYLaVerificanBasicos() {
		
		when(recolector.getNivel()).thenReturn(basico);
		when(experto.esDefinitorio()).thenReturn(false);
		
		when(verificacion.fueRealizadaPorExperto()).thenReturn(false);
		when(verificacion.getTipoVinchuca()).thenReturn(TipoVinchuca.Vinchuca);
		when(verificacionB.fueRealizadaPorExperto()).thenReturn(false);
		when(verificacionB.getTipoVinchuca()).thenReturn(TipoVinchuca.Chinche_Foliada);
		when(verificacionC.fueRealizadaPorExperto()).thenReturn(false);
		when(verificacionC.getTipoVinchuca()).thenReturn(TipoVinchuca.Vinchuca);
		
		
		muestra.verificar(verificacion);
		muestra.verificar(verificacionB);
		muestra.verificar(verificacionC);
		
		assertEquals(TipoVinchuca.Indefinido,muestra.getTipoVinchuca());
		
	}
	
	@Test
	public void consensoDeVotosDeUnaMuestraDeterminaVinchucaCuandoLaEnviaUnBasicoYLaVerificanExpertos() {
		
		when(recolector.getNivel()).thenReturn(basico);
		when(experto.esDefinitorio()).thenReturn(false);
		
		when(verificacion.fueRealizadaPorExperto()).thenReturn(true);
		when(verificacion.getTipoVinchuca()).thenReturn(TipoVinchuca.Vinchuca);
		when(verificacionB.fueRealizadaPorExperto()).thenReturn(true);
		when(verificacionB.getTipoVinchuca()).thenReturn(TipoVinchuca.Chinche_Foliada);
		when(verificacionC.fueRealizadaPorExperto()).thenReturn(true);
		when(verificacionC.getTipoVinchuca()).thenReturn(TipoVinchuca.Vinchuca);
		
		muestraB.verificar(verificacion);
		muestraB.verificar(verificacionB);
		muestraB.verificar(verificacionC);
		
		assertEquals(TipoVinchuca.Vinchuca,muestraB.getTipoVinchuca());
		
	}
	
	@Test
	public void consensoDeVotosDeUnaMuestraDeterminaVinchucaCuandoLaEnviaUnExpertoYLaVerificanBasicos() {
		
		when(recolector.getNivel()).thenReturn(experto);
		when(experto.esDefinitorio()).thenReturn(true);
		
		when(verificacion.fueRealizadaPorExperto()).thenReturn(false);
		when(verificacion.getTipoVinchuca()).thenReturn(TipoVinchuca.Ninguna);
		when(verificacionB.fueRealizadaPorExperto()).thenReturn(false);
		when(verificacionB.getTipoVinchuca()).thenReturn(TipoVinchuca.Chinche_Foliada);
		when(verificacionC.fueRealizadaPorExperto()).thenReturn(false);
		when(verificacionC.getTipoVinchuca()).thenReturn(TipoVinchuca.Ninguna);
		
		muestraC.verificar(verificacion);
		muestraC.verificar(verificacionB);
		muestraC.verificar(verificacionC);
		
		assertEquals(TipoVinchuca.Vinchuca,muestraC.getTipoVinchuca());
		
	}
	
	@Test
	public void consensoDeVotosDeUnaMuestraDeterminaCuandoLaEnviaUnExpertoYLaVerificaUnBasicoConDosExpertos() {
		
		when(recolector.getNivel()).thenReturn(experto);
		when(experto.esDefinitorio()).thenReturn(true);
		
		when(verificacion.fueRealizadaPorExperto()).thenReturn(true);
		when(verificacion.getTipoVinchuca()).thenReturn(TipoVinchuca.Phtia_Chinche);
		when(verificacionB.fueRealizadaPorExperto()).thenReturn(false);
		when(verificacionB.getTipoVinchuca()).thenReturn(TipoVinchuca.Chinche_Foliada);
		when(verificacionC.fueRealizadaPorExperto()).thenReturn(true);
		when(verificacionC.getTipoVinchuca()).thenReturn(TipoVinchuca.Phtia_Chinche);
		
		muestraC.verificar(verificacion);
		muestraC.verificar(verificacionB);
		muestraC.verificar(verificacionC);
		
		assertEquals(TipoVinchuca.Phtia_Chinche,muestraC.getTipoVinchuca());
		
	}
	
	
	@Test 
	public void testUnaMuestraNotificaCargaASusObservadores() {
		GestorNotificacionesAlta gestor = mock(GestorNotificacionesAlta.class);
		
		muestra.addObserver(gestor);
		muestra.informarCarga();
		
		verify(gestor).update(isA(Muestra.class),isA(MensajeObserver.class));
	}
	
	
}
