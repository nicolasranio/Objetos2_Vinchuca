package testParticipante;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import app.AplicacionWeb;
import exceptions.MuestraYaEnviadaException;
import exceptions.MuestraYaVerificadaException;
import muestra.Muestra;
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
	private List<VerificacionMuestra> mockList2;
	private Participante participanteSpy;
	private VerificacionMuestra verificacion;

	@Before
	public void setUp() throws Exception {
		
		participante = new Participante("Ricky");
		participanteSpy = spy(participante);
		pro = mock(NivelConocimientoExperto.class);
		participantePro = new Participante("MacriCat", pro); 
		aplicacion = mock(AplicacionWeb.class);
		muestra = mock(Muestra.class);
		validacion = TipoVinchuca.Chinche_Foliada;
		mockList = mock(List.class);
		mockList2 = mock(List.class);
		verificacion = mock(VerificacionMuestra.class);
			
	}
	
	@Test
	public void testParticipanteProVerificarConocimientoYLoSeteaABasico() {		
		
		participantePro.verificarConocimiento();
		assertTrue(participantePro.getNivel() instanceof NivelConocimientoBasico);
	}
	  
	@Test
	public void testParticipanteBasicoVerificaConocimientoYLoSeteaAExperto() {
		when(mockList.size()).thenReturn(30);
		when(mockList2.size()).thenReturn(30);
		when(participanteSpy.getMuestrasEnviadasUltimoMes()).thenReturn(mockList);
		when(participanteSpy.getMuestrasVerificadasUltimoMes()).thenReturn(mockList2);
		participanteSpy.verificarConocimiento();
		assertTrue(participanteSpy.getNivel() instanceof NivelConocimientoExperto);		
	}
	
	@Test
	public void testParticipanteBasicoVerificaConocimientoYEsteNocambia() {
		when(mockList.size()).thenReturn(30);
		when(mockList2.size()).thenReturn(5);
		when(participanteSpy.getMuestrasEnviadasUltimoMes()).thenReturn(mockList);
		when(participanteSpy.getMuestrasVerificadasUltimoMes()).thenReturn(mockList2);
		participanteSpy.verificarConocimiento();
		assertTrue(participanteSpy.getNivel() instanceof NivelConocimientoBasico);		
	}

	
	@Test
	public void testParticipanteEnviaMuestraAAplicacionYSumaUnaMuestraEnviada() {
		
		when(muestra.esMenorAXDias(31)).thenReturn(true);
		
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
		int cantidadDeVerificaciones = participante.getMuestrasVerificadasUltimoMes().size();
		
		participante.verificarMuestra(muestra, validacion);
		assertEquals(cantidadDeVerificaciones+1, participante.getVerificacioneDeMuestras().size());
			
	}
	
	@Test
	public void testParticipanteVerificaUnaMuestra() throws Exception {
		
		participante.verificarMuestra(muestra, validacion);
		verify(muestra).verificar(isA(VerificacionMuestra.class));
	}
	
	@Test
	public void testParticipanteNoTieneMuestrasEnviadasUltimoMes(){
		
		when(muestra.esMenorAXDias(31)).thenReturn(false);
		participante.enviarMuestra(muestra, aplicacion);
		
		assertTrue(participante.getMuestrasEnviadasUltimoMes().isEmpty());
	}

}
