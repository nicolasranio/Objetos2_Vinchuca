package testParticipante;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import app.AplicacionWeb;
import exceptions.MuestraYaEnviadaException;
import exceptions.MuestraYaVerificadaException;
import muestra.Muestra;
import muestra.TipoVinchuca;
import participante.NivelConocimientoBasico;
import participante.NivelConocimientoExperto;
import participante.Participante;

public class TestParticipante {
	
	private Participante participante;
	private Participante participantePro;
	private AplicacionWeb aplicacion;
	private Muestra muestra;
	private TipoVinchuca validacion;
	private NivelConocimientoExperto pro;

	@Before
	public void setUp() throws Exception {
		
		participante = new Participante("Ricky");
		pro = mock(NivelConocimientoExperto.class);
		participantePro = new Participante("MacriCat", pro); 
		aplicacion = mock(AplicacionWeb.class);
		muestra = mock(Muestra.class);
		validacion = TipoVinchuca.Chinche_Foliada;

	}
	
	@Test
	public void testParticipanteProVerificarConocimientoYLoSeteaABasico() {
		
		
		
		participantePro.verificarConocimiento();
		
		assertEquals(participantePro.getNivel(),isA(NivelConocimientoBasico.class));
		
	}
	
	@Test
	public void testParticipanteVerificarConocimientoYLoSeteaAExperto() {
		
		
		
		
		participante.verificarConocimiento();
		
		assertEquals(participante.getNivel(),isA(NivelConocimientoExperto.class));
		
		
	}
	
	
	@Test
	public void testParticipanteEnviaMuestraAAplicacionYSumaUnaMuestraEnviada() {
		
		participante.enviarMuestra(muestra, aplicacion);
		
		verify(aplicacion).agregarMuestra(muestra);
		assertEquals(1,participante.getMuestrasEnviadasUltimoMes().size());
	} 
	
	@Test(expected = MuestraYaEnviadaException.class)
	public void testParticipanteVerificaMuestraPeroYaLaHabiaEnviado() throws Exception {
		
		participante.enviarMuestra(muestra, aplicacion);
		
		participante.verificarMuestra(muestra, validacion);
	}
	
	@Test(expected = MuestraYaVerificadaException.class)
	public void testParticipanteVerificaUnaMuestraPeroYaLaHabiaVerificado() throws Exception {
		
		participante.verificarMuestra(muestra, validacion);
		participante.verificarMuestra(muestra, validacion);	
	
		
	}
	
	@Test
	public void testParticipanteVerificaUnaMuestra() throws Exception {
		
		int cantidadMuestrasVerificadas = participante.getMuestrasVerificadas().size();
		
		participante.verificarMuestra(muestra, validacion);
		
		assertEquals(cantidadMuestrasVerificadas+1,participante.getMuestrasVerificadas().size());
		
	}
	
	
	

}
