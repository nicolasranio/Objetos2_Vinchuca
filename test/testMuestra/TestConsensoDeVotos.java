package testMuestra;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import muestra.ConsensoDeVotos;
import muestra.Muestra;
import muestra.TipoVinchuca;
import muestra.VerificacionMuestra;
import participante.NivelConocimientoBasico;
import participante.NivelConocimientoExperto;
import participante.Participante;

public class TestConsensoDeVotos {
	private NivelConocimientoExperto experto;
	private NivelConocimientoBasico basico;
	private VerificacionMuestra verificacion;
	private VerificacionMuestra verificacionB;
	private VerificacionMuestra verificacionC;
	private Participante recolector;
	private ConsensoDeVotos censista;
	private Muestra muestra;
	private List<VerificacionMuestra> ls;
	
	@Before
	public void setUp() throws Exception {
		experto = mock(NivelConocimientoExperto.class);
		basico = mock(NivelConocimientoBasico.class);
		verificacion = mock(VerificacionMuestra.class);
		verificacionB = mock(VerificacionMuestra.class);
		verificacionC = mock(VerificacionMuestra.class);
		recolector = mock(Participante.class);
		muestra = mock(Muestra.class);
		ls = new ArrayList<>();
		censista = ConsensoDeVotos.getConsensoDeVotos();
		
		
		ls.add(verificacion);
		ls.add(verificacionB);
		
	}
	
	@Test
	public void testCreoDosVecesUnConsensoDeVotosYObtengoElMismo(){
		
		ConsensoDeVotos censistaAdicional = ConsensoDeVotos.getConsensoDeVotos();

		assertEquals(censistaAdicional, censista);
	
	}
	@Test
	public void consensoDeVotosDeUnaMuestraDeterminaVinchucaIndefinidaCuandoLaEnviaUnBasicoYLaVerificanBasicos() {
		
		//when(recolector.getNivel()).thenReturn(basico);
		//when(basico.esDefinitorio()).thenReturn(false);
		
		when(verificacion.fueRealizadaPorExperto()).thenReturn(false);
		when(verificacion.getTipoVinchuca()).thenReturn(TipoVinchuca.Vinchuca);
		when(verificacionB.fueRealizadaPorExperto()).thenReturn(false);
		when(verificacionB.getTipoVinchuca()).thenReturn(TipoVinchuca.Chinche_Foliada);
		when(muestra.getVerificaciones()).thenReturn(ls);		
		
		assertEquals(TipoVinchuca.Indefinido,censista.getTipoVinchuca(muestra));
		
	}
	
	@Test
	public void consensoDeVotosDeUnaMuestraDeterminaVinchucaCuandoLaEnviaUnBasicoYLaVerificanExpertos() {
		ls.add(verificacionC);	
		when(verificacion.fueRealizadaPorExperto()).thenReturn(true);
		when(verificacion.getTipoVinchuca()).thenReturn(TipoVinchuca.Vinchuca);
		when(verificacionB.fueRealizadaPorExperto()).thenReturn(true);
		when(verificacionB.getTipoVinchuca()).thenReturn(TipoVinchuca.Chinche_Foliada);
		when(verificacionC.fueRealizadaPorExperto()).thenReturn(true);
		when(verificacionC.getTipoVinchuca()).thenReturn(TipoVinchuca.Vinchuca);
		when(muestra.getVerificaciones()).thenReturn(ls);		
		
		assertEquals(TipoVinchuca.Vinchuca,censista.getTipoVinchuca(muestra));
		
	}
	
	@Test
	public void consensoDeVotosDeUnaMuestraDeterminaNingunaCuandoLaEnviaUnExpertoYLaVerificanBasicos() {
		ls.add(verificacionC);
		when(verificacion.fueRealizadaPorExperto()).thenReturn(true);
		when(verificacion.getTipoVinchuca()).thenReturn(TipoVinchuca.Ninguna);
		when(verificacionB.fueRealizadaPorExperto()).thenReturn(false);
		when(verificacionB.getTipoVinchuca()).thenReturn(TipoVinchuca.Chinche_Foliada);
		when(verificacionC.fueRealizadaPorExperto()).thenReturn(false);
		when(verificacionC.getTipoVinchuca()).thenReturn(TipoVinchuca.Chinche_Foliada);
		when(muestra.getVerificaciones()).thenReturn(ls);	
		
		assertEquals(TipoVinchuca.Ninguna,censista.getTipoVinchuca(muestra));
		
	}
	
	@Test
	public void consensoDeVotosDeUnaMuestraDeterminaPhtia_ChincheCuandoLaEnviaUnExpertoYLaVerificaUnBasicoConDosExpertos() {
		ls.add(verificacionC);		
		when(verificacion.fueRealizadaPorExperto()).thenReturn(true);
		when(verificacion.getTipoVinchuca()).thenReturn(TipoVinchuca.Ninguna);
		when(verificacionB.fueRealizadaPorExperto()).thenReturn(true);
		when(verificacionB.getTipoVinchuca()).thenReturn(TipoVinchuca.Phtia_Chinche);
		when(verificacionC.fueRealizadaPorExperto()).thenReturn(true);
		when(verificacionC.getTipoVinchuca()).thenReturn(TipoVinchuca.Phtia_Chinche);
		when(muestra.getVerificaciones()).thenReturn(ls);
		
		assertEquals(TipoVinchuca.Phtia_Chinche,censista.getTipoVinchuca(muestra));
		
	}	
	
	@Test
	public void consensoDeVotosDeUnaMuestraDeterminaCuandoLaEnviaUnExpertoYLaVerificaUnBasicoConDosExpertos() {
		ls.add(verificacionC);		
		when(verificacion.fueRealizadaPorExperto()).thenReturn(true);
		when(verificacion.getTipoVinchuca()).thenReturn(TipoVinchuca.Phtia_Chinche);
		when(verificacionB.fueRealizadaPorExperto()).thenReturn(true);
		when(verificacionB.getTipoVinchuca()).thenReturn(TipoVinchuca.Chinche_Foliada);
		when(verificacionC.fueRealizadaPorExperto()).thenReturn(true);
		when(verificacionC.getTipoVinchuca()).thenReturn(TipoVinchuca.Phtia_Chinche);
		when(muestra.getVerificaciones()).thenReturn(ls);
		
		assertEquals(TipoVinchuca.Phtia_Chinche,censista.getTipoVinchuca(muestra));
		
	}	

}
