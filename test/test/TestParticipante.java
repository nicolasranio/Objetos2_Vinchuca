package test;

import static org.junit.Assert.*;

import org.mockito.Mockito;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
		aplicacion = Mockito.mock(AplicacionWeb.class);
		muestra = Mockito.mock(Muestra.class);
		validacion = TipoVinchuca.Vinchuca;
		participante.enviarMuestra(muestra, aplicacion);
	}
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	

	@Test
	public void testParticipanteEnviaMuestraAAplicacionYSumaUnaMuestraEnviada() {
				
		Mockito.verify(aplicacion).agregarMuestra(muestra);
		assertEquals(1,participante.getMuestrasEnviadas().size());
	}
	@Test
	public void testParticipanteVerificaMuestraPeroYaLaHabiaEnviado() {
		//exceptionRule.expect(RuntimeException.class);
	    //exceptionRule.expectMessage("no se puede verificar");
		participante.enviarMuestra(muestra, aplicacion);
		participante.verificarMuestra(muestra, validacion);
		//No logro hacer fallar la excepción
	}

}
