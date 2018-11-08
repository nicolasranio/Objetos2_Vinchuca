package test;


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
		listaMuestras.add(muestraB);
		listaMuestras.add(muestraC);
		
		muestra.muestrasCercanas(listaMuestras, new Double(1800.00));
		
		//Hay que verificar las ubicaciones de las muestra 
	}
	
	@Test
	public void test_creacion_muestra() {
		
	}
	
	
}
