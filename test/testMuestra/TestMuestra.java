package testMuestra;


import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
	public void testConstructorDeMuestra() {
		
		assertTrue(muestra.getTipoVinchuca().equals(TipoVinchuca.Chinche_Foliada));
		assertTrue(muestra.getFotoVinchuca().equals("foto"));
		assertTrue(muestra.getUbicacion().equals(ubicacion));
		assertTrue(muestra.getAliasRecolector().equals("Rogelio"));
		
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
	
	

	
	
}
