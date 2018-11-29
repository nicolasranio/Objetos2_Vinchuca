package testParticipante;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.internal.matchers.InstanceOf;

import app.AplicacionWeb;
import exceptions.MuestraYaEnviadaException;
import exceptions.MuestraYaVerificadaException;
import muestra.Muestra;
import muestra.NivelVerificacionBajo;
import muestra.TipoVinchuca;
import muestra.VerificacionMuestra;
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
	private List<Muestra> mockList;
	private Participante participanteSpy;

	@Before
	public void setUp() throws Exception {
		
		participante = new Participante("Ricky");
		participanteSpy = spy(participante);
		pro = mock(NivelConocimientoExperto.class);
		participantePro = new Participante("MacriCat", pro); 
		aplicacion = mock(AplicacionWeb.class);
		muestra = mock(Muestra.class);
		validacion = TipoVinchuca.Chinche_Foliada;
		mockList = mock(ArrayList.class);
			
	}
	
	@Test
	public void testParticipanteProVerificarConocimientoYLoSeteaABasico() {		
		
		participantePro.verificarConocimiento();
		assertTrue(participantePro.getNivel() instanceof NivelConocimientoBasico);
	}
	  
	@Test
	public void testParticipanteBasicoVerificaConocimientoYLoSeteaAExperto() {
		when(mockList.size()).thenReturn(30);
		when(participanteSpy.getMuestrasEnviadasUltimoMes()).thenReturn(mockList);
		when(participanteSpy.getMuestrasVerificadas()).thenReturn(mockList);
		participanteSpy.verificarConocimiento();
		assertTrue(participanteSpy.getNivel() instanceof NivelConocimientoExperto);		
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
	public void testParticipanteVerificaUnaMuestraYSumaUnaverificacion() throws Exception {
		int cantidadDeVerificaciones = participante.getMuestrasVerificadas().size();
		
		participante.verificarMuestra(muestra, validacion);
		assertEquals(cantidadDeVerificaciones+1, participante.getVerificacioneDeMuestras().size());
		assertTrue(participante.getMuestrasVerificadas().contains(muestra));
		
	}
	
	@Test
	public void testParticipanteVerificaUnaMuestra() throws Exception {
		
		participante.verificarMuestra(muestra, validacion);
		verify(muestra).verificar(isA(VerificacionMuestra.class));
	}
	
	@Test
	public void testParticipanteMuestrasEnviadasUltimoMes(){
		
		participante.enviarMuestra(muestra, aplicacion);
		
		
		
	}
	

}
