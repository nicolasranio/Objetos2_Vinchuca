package testParticipante;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import muestra.Muestra;
import muestra.NivelVerificacionAlto;
import participante.NivelConocimientoExperto;

public class TestConocimientoExperto {

	private NivelConocimientoExperto experto;
	private Muestra muestra;
	
	@Before
	public void setUp() throws Exception {
		experto = new NivelConocimientoExperto();
		muestra = mock(Muestra.class);
	} 
	
	@Test
	public void testNivelConocimientoExpertoCambiaNivelVerificacionAltoAMuestra() {
		
		experto.verificarMuestra(muestra);
		
		verify(muestra).setNivelVerificacion(isA(NivelVerificacionAlto.class));
	}
	
	@Test
	public void testNivelConocimientoExpertoDevuelveVerdaderoCuandoLeConsultanEsDefinitorio() {
		
		assertEquals(experto.esDefinitorio(),true);
	}


}
