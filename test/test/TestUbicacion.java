package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import src.Ubicacion;

public class TestUbicacion {

	private Ubicacion ubicacionA;
	private Ubicacion ubicacionB;
	
	@Before
	public void setUp() throws Exception {
		ubicacionA = new Ubicacion(100.00,100.00);
	 	ubicacionB = new Ubicacion (200.00,200.00);
	 	
		
	}

	@Test
	public void testMismaUbicacionDistanciaCero() {
		assertEquals(new Double(0.00), this.ubicacionA.calcularDistancia(ubicacionA));
	}
	
	@Test
	public void testDistintaUbicacion() {
		//assertEquals(new Double(12400.97), this.ubicacionA.calcularDistancia(ubicacionB));
		assertEquals(12400.97,this.ubicacionA.calcularDistancia(ubicacionB),15.00);
	}
	
	@Test
	public void testUbicacionesAMenosDe100Km(){
		Ubicacion ubicacionC = new Ubicacion(99.99,99.99);
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(ubicacionC);
		ubicaciones.add(ubicacionB);
		List<Ubicacion> ubicacionesCercanas = ubicacionA.ubicacionesCercanas(ubicaciones, 10.00);
		assertEquals(1,ubicacionesCercanas.size());
		
	}
	
}
