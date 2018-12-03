package testApplicacionWeb;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import app.AplicacionWeb;
import app.Filtro;
import muestra.Muestra;
import zonaDeCobertura.ZonaCobertura;

public class TestAplicacionWeb {

	private AplicacionWeb app;
	private Muestra muestraA;
	private Muestra muestraB;
	private Muestra muestraC;
	private ZonaCobertura zonaCobertura;
	private Filtro filtro;
	
	@Before
	public void setUp() {
		app = new AplicacionWeb();
		muestraA = mock(Muestra.class);
		muestraB = mock(Muestra.class);
		muestraC = mock(Muestra.class);
		zonaCobertura = mock(ZonaCobertura.class);
		filtro = mock(Filtro.class);
	}
	
	@Test
	public void testSeAgregaUnaMuestraALaAplicacionWeb() {
	
		app.agregarMuestra(muestraA);
		app.agregarMuestra(muestraC);
		
		assertEquals(2,app.getMuestras().size());
	}
	
	@Test
	public void testSeAgregaUnaZonaDeCoberturaALaAplicacionWeb() {
	
		app.agregarZonaCobertura(zonaCobertura);
	
		assertEquals(1,app.getZonasCobertura().size());
	}
	
	@Test
	public void testAplicacionWebFiltraMuestrasQueAplicanAlFiltro() {
		
		when(filtro.aplicar(muestraC)).thenReturn(false);
		when(filtro.aplicar(muestraA)).thenReturn(true);
		when(filtro.aplicar(muestraB)).thenReturn(true);
		
		app.agregarMuestra(muestraC);
		app.agregarMuestra(muestraA);
		app.agregarMuestra(muestraB);
		
		assertEquals(2,app.filtrarMuestras(filtro).size());
		
		
	}


}
