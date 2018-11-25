package testMuestra;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import muestra.Ubicacion;

public class TestUbicacion {

	private Ubicacion ubicacionA;
	private Ubicacion ubicacionB;
	private Ubicacion ubicacionC;
	
	@Before
	public void setUp() throws Exception {
		ubicacionA = new Ubicacion(36.00,47.00);
	 	ubicacionB = new Ubicacion(23.00,11.00);
	 	ubicacionC = new Ubicacion(41.00,39.00);
		
	}

	@Test
	public void testMismaUbicacionDistanciaCero() {
		assertEquals(new Double(0.00), this.ubicacionA.calcularDistancia(ubicacionA));
	}
	
	@Test
	public void testDistintaUbicacion() {
		assertEquals(new Double(3745.09), this.ubicacionA.calcularDistancia(ubicacionB));
	}
	
	@Test
	public void testUbicacionesAMenosDe1000Km(){

		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(ubicacionC);
		ubicaciones.add(ubicacionB);
		
		List<Ubicacion> ubicacionesCercanas = ubicacionA.ubicacionesCercanas(ubicaciones, 1000.00);
		
		assertEquals(1,ubicacionesCercanas.size());
		
	}
	

	
}
