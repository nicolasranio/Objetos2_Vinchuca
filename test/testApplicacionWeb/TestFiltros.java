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
	private NivelVerificacionAlto nivelVerificacion;
	private TipoVinchuca tipoVinchuca;
	private VerificacionMuestra verificacion;
	private ArrayList<VerificacionMuestra> verificaciones;
	
	@Before
	public void setUp() throws Exception {
		
		muestra = mock(Muestra.class);
		nivelVerificacion = mock(NivelVerificacionAlto.class);
		tipoVinchuca = TipoVinchuca.Phtia_Chinche;
		verificaciones = mock(ArrayList.class);
		verificacion = mock(VerificacionMuestra.class);
		
		filtroPorFechaCreacionAnterior = new FiltroPorFechaDeCreacionAnterior(LocalDate.of(2018, 11, 25));
		filtroPorFechaCreacionPosterior = new FiltroPorFechaDeCreacionPosterior(LocalDate.of(2018, 1, 26));
		filtroPorFechaUltimaVerificacionAnterior = new FiltroPorFechaDeUltimaVerificacionAnterior(LocalDate.of(2018, 11, 29));
		filtroPorFechaUltimaVerificacionPosterior = new FiltroPorFechaDeUltimaVerificacionPosterior(LocalDate.of(2018, 6, 5));
		filtroPorNivelVerificacion = new FiltroPorNivelDeVerificacion(new NivelVerificacionAlto(muestra));
		filtroPorTipoVinchuca = new FiltroPorTipoDeVinchucaDetectado(tipoVinchuca);
		
		filtroOr = new FiltroOr(filtroPorFechaCreacionPosterior,filtroPorNivelVerificacion);
		filtroAnd = new FiltroAnd(filtroPorFechaUltimaVerificacionPosterior,filtroOr);
		
		
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
		
		when(muestra.getVerificaciones()).thenReturn(verificaciones);
		when(verificaciones.size()).thenReturn(1);
		when(verificaciones.get(verificaciones.size()-1)).thenReturn(verificacion);
		when(verificacion.getFechaVerificacion()).thenReturn(LocalDate.of(2018, 4, 17));
		
		
		assertTrue(filtroPorFechaUltimaVerificacionAnterior.aplicar(muestra));
		
	}
	
	@Test
	public void testMuestraEsFiltradaPorFechaDeUltimaVerificacionAnteriorYNoAplica() {
		
		when(muestra.getVerificaciones()).thenReturn(verificaciones);
		when(verificaciones.size()).thenReturn(4);
		when(verificaciones.get(verificaciones.size()-1)).thenReturn(verificacion);
		when(verificacion.getFechaVerificacion()).thenReturn(LocalDate.of(2018, 12, 1));
		
		
		assertFalse(filtroPorFechaUltimaVerificacionAnterior.aplicar(muestra));
	}
	
	@Test
	public void testMuestraEsFiltradaPorFechaDeUltimaVerificacionPosteriorYSiAplica() {
		
		
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
	
	@Test
	public void testMuestraEsFiltradaPorNivelVerificacionYAplica() {
		
		when(muestra.getNivelVerificacion()).thenReturn(mock(NivelVerificacionAlto.class));
		
		assertTrue(filtroPorNivelVerificacion.aplicar(muestra));
	}
	
	@Test
	public void testMuestraEsFiltradaPorNivelVerificacionYNoAplica() {
	
		when(muestra.getNivelVerificacion()).thenReturn(mock(NivelVerificacionMedio.class));
		
		assertFalse(filtroPorNivelVerificacion.aplicar(muestra));
	}
	
	@Test
	public void testMuestraEsFiltradaPorTipoDeVinchucaDetectadoYAplica() {
		
		when(muestra.getTipoVinchuca()).thenReturn(TipoVinchuca.Phtia_Chinche);
		
		assertTrue(filtroPorTipoVinchuca.aplicar(muestra));
	}
	
	@Test
	public void testMuestraEsFiltradaPorTipoDeVinchucaDetectadoYNoAplica() {
		
		when(muestra.getTipoVinchuca()).thenReturn(TipoVinchuca.Chinche_Foliada);
		
		assertFalse(filtroPorTipoVinchuca.aplicar(muestra));
	}
	

	@Test
	public void testMuestraEsFiltradaFiltroOrYAplicaEnAmbosCasos() {
		
		when(muestra.getFechaEnvio()).thenReturn(LocalDate.of(2018, 1, 9));
		
		when(muestra.getNivelVerificacion()).thenReturn(mock(NivelVerificacionAlto.class));
		
		assertTrue(filtroOr.aplicar(muestra));
	}
	
	@Test
	public void testMuestraEsFiltradaFiltroOrYAplicaAlMenosUnCaso() {
		
		when(muestra.getFechaEnvio()).thenReturn(LocalDate.of(2017, 9, 17));
		
		when(muestra.getNivelVerificacion()).thenReturn(mock(NivelVerificacionBajo.class));
		
		assertFalse(filtroOr.aplicar(muestra));
	}
	
	@Test
	public void testMuestraEsFiltradaFiltroOrYNoAplicaEnNingunCaso() {
		
		when(muestra.getFechaEnvio()).thenReturn(LocalDate.of(2016, 12, 23));
		
		when(muestra.getNivelVerificacion()).thenReturn(mock(NivelVerificacionMedio.class));
		
		assertFalse(filtroOr.aplicar(muestra));
	}
	
	
	@Test
	public void testMuestraEsFiltradaFiltroAndYAplicaEnAmbosCasos() {
		
		when(muestra.getVerificaciones()).thenReturn(verificaciones);
		when(verificaciones.size()).thenReturn(7);
		when(verificaciones.get(verificaciones.size()-1)).thenReturn(verificacion);
		when(verificacion.getFechaVerificacion()).thenReturn(LocalDate.of(2018, 8, 30));
		
		when(muestra.getFechaEnvio()).thenReturn(LocalDate.of(2015, 12, 27));
		when(muestra.getNivelVerificacion()).thenReturn(mock(NivelVerificacionAlto.class));
		
		
		assertTrue(filtroAnd.aplicar(muestra));
	}
	
	@Test
	public void testMuestraEsFiltradaFiltroAndYAplicaSoloUnCaso() {
		
		when(muestra.getVerificaciones()).thenReturn(verificaciones);
		when(verificaciones.size()).thenReturn(14);
		when(verificaciones.get(verificaciones.size()-1)).thenReturn(verificacion);
		when(verificacion.getFechaVerificacion()).thenReturn(LocalDate.of(2018, 5, 30));
		
		when(muestra.getFechaEnvio()).thenReturn(LocalDate.of(2018, 10, 30));
		when(muestra.getNivelVerificacion()).thenReturn(mock(NivelVerificacionAlto.class));
		
		assertFalse(filtroAnd.aplicar(muestra));
	}
	
	@Test
	public void testMuestraEsFiltradaFiltroAndYNoAplicaEnNingunCaso() {
		
		when(muestra.getVerificaciones()).thenReturn(verificaciones);
		when(verificaciones.size()).thenReturn(29);
		when(verificaciones.get(verificaciones.size()-1)).thenReturn(verificacion);
		when(verificacion.getFechaVerificacion()).thenReturn(LocalDate.of(2017, 5, 10));
		
		when(muestra.getFechaEnvio()).thenReturn(LocalDate.of(2018, 11, 14));
		when(muestra.getNivelVerificacion()).thenReturn(mock(NivelVerificacionBajo.class));
		
		
		assertFalse(filtroAnd.aplicar(muestra));
	}
	
	
	


}
