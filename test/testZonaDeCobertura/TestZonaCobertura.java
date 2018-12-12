package testZonaDeCobertura;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import app.AplicacionWeb;
import muestra.Muestra;
import muestra.Ubicacion;
import observer.MensajeObserverAlta;
import organizacion.Organizacion;
import zonaDeCobertura.ZonaCobertura;

public class TestZonaCobertura {

	private ZonaCobertura zonaCobertura;
	private Ubicacion epicentro;
	private AplicacionWeb app;
	private Muestra muestra;
	private Muestra muestraBis;
	private ZonaCobertura zonaCobertura2;
	private ZonaCobertura zonaCobertura3;
	private Ubicacion epicentro2;
	private Ubicacion epicentro3;
	
	@Before
	public void setUp() throws Exception {
	
		app = mock(AplicacionWeb.class);
		epicentro = mock(Ubicacion.class);
		zonaCobertura = new ZonaCobertura("Quilmes Centro",epicentro,10.00,app);
		muestra = mock(Muestra.class);
		muestraBis = mock(Muestra.class);
		epicentro2 = mock(Ubicacion.class);
		epicentro3 = mock(Ubicacion.class);
		zonaCobertura2 = new ZonaCobertura("Bernal",epicentro2,5.00,app);
		zonaCobertura3 = new ZonaCobertura("Berazategui",epicentro3,7.00,app);
		
		
	}

	@Test
	public void testZonaDeCoberturaCalculaDistanciaDeMuestra() {
		
		zonaCobertura.distanciaDeMuestra(muestra);
		
		verify(epicentro).calcularDistancia(muestra.getUbicacion());
	}
	
	@Test
	public void testZonaCoberturaObtieneLasMuestrasReportadasEnSuRadio() {
		ArrayList<Muestra> listaMuestras = new ArrayList<Muestra> ();
		listaMuestras.add(muestra);
		listaMuestras.add(muestraBis);
		
		when(app.getMuestras()).thenReturn(listaMuestras);
		when(epicentro.calcularDistancia(muestra.getUbicacion())).thenReturn(6.00);
		when(epicentro.calcularDistancia(muestraBis.getUbicacion())).thenReturn(8.00);
		
		zonaCobertura.muestrasReportadas();
		
		assertEquals(2,zonaCobertura.muestrasReportadas().size());
		
	}

	@Test
	public void testZonaCoberturaObtieneZonasSolapadas() {
		ArrayList<ZonaCobertura> listaZonas = new ArrayList<ZonaCobertura> ();
		listaZonas.add(zonaCobertura2);
		listaZonas.add(zonaCobertura3);
		
		when(app.getZonasCobertura()).thenReturn(listaZonas);
		
		when(epicentro2.calcularDistancia(zonaCobertura.getEpicentro())).thenReturn(10.00);
		when(epicentro3.calcularDistancia(zonaCobertura.getEpicentro())).thenReturn(18.00);
		
		zonaCobertura.zonasSolapadas();
		
		assertEquals(1,zonaCobertura.zonasSolapadas().size());
		
	}

	
	@Test
	public void testUpdateNotificacionAltaDisparaEventoEnObserverCuandoLaMuestraNoEstaDentroDeLaZona(){
		
		Organizacion obs = mock(Organizacion.class);
		zonaCobertura.addObserver(obs);
		
		MensajeObserverAlta msj = mock(MensajeObserverAlta.class);
		when(msj.getMuestra()).thenReturn(muestra);
		when(epicentro.calcularDistancia(muestra.getUbicacion())).thenReturn(50.00);
		zonaCobertura.update(muestra, msj);
		verify(obs, never()).update(zonaCobertura, msj);
	}
	
	@Test
	public void testUpdateNotificacionNoDisparaEventoObserverCuandoLaMuestraEstaEnAreaDeCobertura(){
		Organizacion obs = mock(Organizacion.class);
		zonaCobertura.addObserver(obs);
		
		MensajeObserverAlta msj = mock(MensajeObserverAlta.class);
		when(msj.getMuestra()).thenReturn(muestra);
		when(epicentro.calcularDistancia(muestra.getUbicacion())).thenReturn(6.00);
		zonaCobertura.update(zonaCobertura, msj);
		verify(obs).update(zonaCobertura, msj);
		
	}
	
	
	
	
	

}
