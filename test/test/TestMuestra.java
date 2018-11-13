package test;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import muestra.Muestra;
import muestra.TipoVinchuca;
import muestra.Ubicacion;
import participante.Participante;



public class TestMuestra {
	
	private Muestra muestra;
	private Muestra muestraB;
	private Muestra muestraC;
	private Ubicacion ubicacion;
	private Ubicacion ubicacionB;
	private Participante participante;
	private TipoVinchuca tipoVinchuca;
	
	@Before
	public void setUp() {
		
		ubicacion = Mockito.mock(Ubicacion.class);
		ubicacionB = Mockito.mock(Ubicacion.class);
		muestra = new Muestra(TipoVinchuca.Chinche_Foliada,"foto",ubicacion,"Rogelio");
		muestraB = new Muestra(TipoVinchuca.Imagen_poco_clara,"foto",ubicacionB,"Fort");
		muestraC = new Muestra(TipoVinchuca.Ninguna,"foto",ubicacion,"Comandante");
		participante = Mockito.mock(Participante.class);
		
	}
	
	@Test
	public void testMuestraCalculaLaDistanciaConMuestaBEntreSusUbicaciones() {
		
		muestra.distanciaAMuestra(muestraB);
		
		Mockito.verify(ubicacion).calcularDistancia(muestraB.getUbicacion());
		
	}
	
	@Test 
	public void testMuestraFiltraMuestrasCercanasA1800km() {
		
		List <Muestra> listaMuestras = new ArrayList <Muestra>();
		listaMuestras.add(muestraB);
		listaMuestras.add(muestraC);
		
		Mockito.when(ubicacion.calcularDistancia(muestraB.getUbicacion())).thenReturn(900.00);
		Mockito.when(ubicacion.calcularDistancia(muestraC.getUbicacion())).thenReturn(1900.00);
		
		List<Muestra> muestras = muestra.muestrasCercanas(listaMuestras, new Double(1800.00));
		
		assertEquals(1,muestras.size());
	}
	
	@Test
	public void testMuestraEsVerificadaYAgregaAParicipanteComoVerificador() {
		muestra.verificar(participante,tipoVinchuca);
		
		assertTrue(muestra.getVerificadores().contains(participante));
	}
	
	@Test
	public void testMuestraASidoVerificadoPorUnParticipante() {
		
		int cantVerificaciones = muestra.getVerificaciones().size();
		
		muestra.verificar(participante,tipoVinchuca);
		
		assertEquals(cantVerificaciones+1,muestra.getVerificaciones().size());
		
	}

	
	
}
