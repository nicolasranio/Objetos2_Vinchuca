package test;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import exceptions.MuestraYaEnviadaException;
import src.AplicacionWeb;
import src.Muestra;
import src.Participante;
import src.TipoVinchuca;

public class TestParticipante {
	
	private Participante participante;
	private AplicacionWeb aplicacion;
	private Muestra muestra;
	private TipoVinchuca validacion;

	@Before
	public void setUp() throws Exception {
		participante = new Participante("Ricky");
		aplicacion = mock(AplicacionWeb.class);
		muestra = mock(Muestra.class);
		validacion = TipoVinchuca.Vinchuca;
		participante.enviarMuestra(muestra, aplicacion);
	}
	
	@Test
	public void testParticipanteEnviaMuestraAAplicacionYSumaUnaMuestraEnviada() {
				
		verify(aplicacion).agregarMuestra(muestra);
		assertEquals(1,participante.getMuestrasEnviadas().size());
	}
	
	@Test(expected = MuestraYaEnviadaException.class)
	public void testParticipanteVerificaMuestraPeroYaLaHabiaEnviado() throws MuestraYaEnviadaException {
		participante.verificarMuestra(muestra, validacion);
	}

}
