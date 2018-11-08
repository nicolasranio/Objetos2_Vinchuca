package test;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import src.Muestra;
import src.Ubicacion;



public class TestMuestra {
	
	private Muestra muestra;
	private Ubicacion ubicacion;
	private Muestra muestraB;
	private Muestra muestraC;
	
	@Before
	public void setUp() {
		
		ubicacion = Mockito.mock(Ubicacion.class);
		muestra = new Muestra("Chinche_Foliada","foto",ubicacion,"Rogelio");
		muestraB = new Muestra("Ninguna","foto",ubicacion,"Fort");
		muestraC = new Muestra("Vinchuca","foto",ubicacion,"Comandante");
	}
	
	@Test
	public void testMuestraCalculaLaDistanciaConMuestaBEntreSusUbicaciones() {
		
		muestra.distanciaAMuestra(muestraB);
		
		Mockito.verify(ubicacion).calcularDistancia(muestraB.getUbicacion());
		
	}
	
	@Test 
	public void testMuestraFiltraMuestrasCercanasA1800km() {
		
		List <Muestra> listaMuestras = new ArrayList <Muestra>();
		Mockito.when(ubicacion.calcularDistancia(muestra.getUbicacion())).thenReturn(900.00);
		listaMuestras.add(muestraB);
		listaMuestras.add(muestraC);
		
		
		List<Muestra> muestras = muestra.muestrasCercanas(listaMuestras, new Double(1800.00));
		
		assertEquals(2,muestras.size());
		//Hay que verificar las ubicaciones de las muestra 
	}
	

	
	
}
