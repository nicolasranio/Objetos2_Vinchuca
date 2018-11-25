package testApplicacionWeb;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import app.AplicacionWeb;
import muestra.Muestra;
import zonaDeCobertura.ZonaCobertura;

public class TestAplicacionWeb {

	private AplicacionWeb app;
	private Muestra muestra;
	private ZonaCobertura zonaCobertura;
	
	@Before
	public void setUp() {
		app = new AplicacionWeb();
		muestra = mock(Muestra.class);
		zonaCobertura = mock(ZonaCobertura.class);
	}
	
	@Test
	public void testSeAgregaUnaMuestraALaAplicacionWeb() {
	
		app.agregarMuestra(muestra);
		
		assertEquals(1,app.getMuestras().size());
	}
	
	@Test
	public void testSeAgregaUnaZonaDeCoberturaALaAplicacionWeb() {
	
		app.agregarZonaCobertura(zonaCobertura);
	
		assertEquals(1,app.getZonasCobertura().size());
	}


}