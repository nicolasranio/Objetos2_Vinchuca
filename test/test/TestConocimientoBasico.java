package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.matchers.InstanceOf;

import muestra.Muestra;
import participante.NivelConocimientoBasico;
import participante.NivelConocimientoExperto;
import participante.Participante;

public class TestConocimientoBasico {
	private NivelConocimientoBasico rookie;
	private Participante participante;
	private List<Muestra> mockList;

	
	@Before
	public void setUp() throws Exception {
		mockList = mock(ArrayList.class);
		participante = mock(Participante.class);
		rookie = new NivelConocimientoBasico(participante);
	}
	@Test
	public void testParticipanteCambiaEstado() {
		when(mockList.size()).thenReturn(30);
		when(participante.getMuestrasEnviadas()).thenReturn(mockList);
		when(participante.getMuestrasVerificadas()).thenReturn(mockList);
		
		rookie.cambiarEstado();
		verify(participante).setEstado(isA(NivelConocimientoExperto.class));	
		
		
	}
	
	@Test
	public void testParticipanteNoCambiaEstado() {
		when(mockList.size()).thenReturn(0);
		when(participante.getMuestrasEnviadas()).thenReturn(mockList);
		when(participante.getMuestrasVerificadas()).thenReturn(mockList);
		
		rookie.cambiarEstado();
		verify(participante, times(0)).setEstado(isA(NivelConocimientoExperto.class));	
	}
	
	@Test
	public void testParticipanteNoCambiaEstadoPorUnaRazon() {
		when(mockList.size()).thenReturn(15);
		when(participante.getMuestrasEnviadas()).thenReturn(mockList);
		when(participante.getMuestrasVerificadas()).thenReturn(mockList);
		
		rookie.cambiarEstado();
		verify(participante, times(0)).setEstado(isA(NivelConocimientoExperto.class));	
	}

}	
