package testApplicacionWeb;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;

import app.*;
import muestra.*;

import org.junit.Before;
import org.junit.Test;


public class TestFiltros {

	private FiltroPorFechaDeCreacionAnterior filtroPorFechaCreacionAnterior;
	private FiltroPorFechaDeCreacionPosterior filtroPorFechaCreacionPosterior;
	private FiltroPorFechaDeUltimaVerificacionAnterior filtroPorFechaUltimaVerificacionAnterior;
	private FiltroPorFechaDeUltimaVerificacionPosterior filtroPorFechaUltimaVerificacionPosterior;
	private FiltroPorNivelDeVerificacion filtroPorNivelVerificacion;
	private FiltroPorTipoDeVinchucaDetectado filtroPorTipoVinchuca;
	
	private FiltroOr filtroOr;
	private FiltroAnd filtroAnd;
	
	private Muestra muestra;
	private INivelVerificacion nivelVerificacion;
	//private TipoVinchuca tipoVinchuca;
	
	@Before
	public void setUp() throws Exception {
		
		muestra = mock(Muestra.class);
		nivelVerificacion = mock(INivelVerificacion.class);
		//tipoVinchuca = mock(TipoVinchuca.class);
		
		filtroPorFechaCreacionAnterior = new FiltroPorFechaDeCreacionAnterior(LocalDate.of(2018, 11, 25));
		filtroPorFechaCreacionPosterior = new FiltroPorFechaDeCreacionPosterior(LocalDate.of(2018, 1, 26));
		filtroPorFechaUltimaVerificacionAnterior = new FiltroPorFechaDeUltimaVerificacionAnterior(LocalDate.of(2018, 11, 29));
		filtroPorFechaUltimaVerificacionPosterior = new FiltroPorFechaDeUltimaVerificacionPosterior(LocalDate.of(2018, 6, 5));
		filtroPorNivelVerificacion = new FiltroPorNivelDeVerificacion(nivelVerificacion);
		//filtroPorTipoVinchuca = new FiltroPorTipoDeVinchucaDetectado(tipoVinchuca);
		
		filtroOr = new FiltroOr(filtroPorFechaCreacionPosterior,filtroPorNivelVerificacion);
		filtroAnd = new FiltroAnd(filtroPorFechaUltimaVerificacionPosterior,filtroPorTipoVinchuca);
		
	}

	@Test
	public void testMuestraEsFiltradaPorFechaDeCreacionAnteriorYSiAplica() {
		when(muestra.getFechaEnvio()).thenReturn(LocalDate.of(2018, 9, 7));
		
		assertTrue(filtroPorFechaCreacionAnterior.aplicar(muestra));
	}
	
	@Test
	public void testMuestraEsFiltradaPorFechaDeCreacionAnteriorYNoAplica() {
		when(muestra.getFechaEnvio()).thenReturn(LocalDate.of(2018, 12, 14));
		
		assertFalse(filtroPorFechaCreacionAnterior.aplicar(muestra));
	}
	
	@Test
	public void testMuestraEsFiltradaPorFechaDeCreacionPosteriorYSiAplica() {
		when(muestra.getFechaEnvio()).thenReturn(LocalDate.of(2018, 10, 9));
		
		assertTrue(filtroPorFechaCreacionPosterior.aplicar(muestra));
	}
	
	@Test
	public void testMuestraEsFiltradaPorFechaDeCreacionPosteriorYNoAplica() {
		when(muestra.getFechaEnvio()).thenReturn(LocalDate.of(2018, 1, 14));
		
		assertFalse(filtroPorFechaCreacionPosterior.aplicar(muestra));
	}
	
	@Test
	public void testMuestraEsFiltradaPorFechaDeUltimaVerificacionAnteriorYSiAplica() {
		ArrayList<VerificacionMuestra> verificaciones = mock(ArrayList.class);
		VerificacionMuestra verificacion = mock(VerificacionMuestra.class);
		
		when(muestra.getVerificaciones()).thenReturn(verificaciones);
		when(verificaciones.size()).thenReturn(1);
		when(verificaciones.get(verificaciones.size()-1)).thenReturn(verificacion);
		when(verificacion.getFechaVerificacion()).thenReturn(LocalDate.of(2018, 4, 17));
		
		
		assertTrue(filtroPorFechaUltimaVerificacionAnterior.aplicar(muestra));
		
	}
	
	@Test
	public void testMuestraEsFiltradaPorFechaDeUltimaVerificacionAnteriorYNoAplica() {
		ArrayList<VerificacionMuestra> verificaciones = mock(ArrayList.class);
		VerificacionMuestra verificacion = mock(VerificacionMuestra.class);
		
		when(muestra.getVerificaciones()).thenReturn(verificaciones);
		when(verificaciones.size()).thenReturn(1);
		when(verificaciones.get(verificaciones.size()-1)).thenReturn(verificacion);
		when(verificacion.getFechaVerificacion()).thenReturn(LocalDate.of(2018, 12, 1));
		
		
		assertFalse(filtroPorFechaUltimaVerificacionAnterior.aplicar(muestra));
	}
	
	@Test
	public void testMuestraEsFiltradaPorFechaDeUltimaVerificacionPosteriorYSiAplica() {
		ArrayList<VerificacionMuestra> verificaciones = mock(ArrayList.class);
		VerificacionMuestra verificacion = mock(VerificacionMuestra.class);
		
		when(muestra.getVerificaciones()).thenReturn(verificaciones);
		when(verificaciones.size()).thenReturn(1);
		when(verificaciones.get(verificaciones.size()-1)).thenReturn(verificacion);
		when(verificacion.getFechaVerificacion()).thenReturn(LocalDate.of(2018, 8, 30));
		
		
		assertTrue(filtroPorFechaUltimaVerificacionPosterior.aplicar(muestra));
		
	}
	
	@Test
	public void testMuestraEsFiltradaPorFechaDeUltimaVerificacionPosteriorYNoAplica() {
		ArrayList<VerificacionMuestra> verificaciones = mock(ArrayList.class);
		VerificacionMuestra verificacion = mock(VerificacionMuestra.class);
		
		when(muestra.getVerificaciones()).thenReturn(verificaciones);
		when(verificaciones.size()).thenReturn(1);
		when(verificaciones.get(verificaciones.size()-1)).thenReturn(verificacion);
		when(verificacion.getFechaVerificacion()).thenReturn(LocalDate.of(2018, 3, 12));
		
		
		assertFalse(filtroPorFechaUltimaVerificacionPosterior.aplicar(muestra));
	}
	
	


}
