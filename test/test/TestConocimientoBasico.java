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
	private Participante participante;
	private Participante participanteBobo;
	private NivelConocimientoBasico rookie;
	private List<Muestra> mockList;
	
	@Before
	public void setUp() throws Exception {
		mockList = mock(ArrayList.class);
		participante = new Participante("Rookie");
		participanteBobo = spy(participante);
		
	}
	@Test
	public void testParticipanteCambiaEstado() {
		when(mockList.size()).thenReturn(30);
		when(participanteBobo.getMuestrasEnviadas()).thenReturn(mockList);
		when(participanteBobo.getMuestrasVerificadas()).thenReturn(mockList);
		assertEquals(mockList, participanteBobo.getMuestrasVerificadas());
		assertEquals(30, participanteBobo.getMuestrasVerificadas().size());
		assertEquals(30, participanteBobo.getMuestrasEnviadas().size());
		assertTrue(participanteBobo.getMuestrasEnviadas().size() > 10);
		assertTrue(participanteBobo.getMuestrasEnviadas().size() > 20);
		
		
		participanteBobo.getNivel().cambiarEstado();
		participanteBobo.setEstado(new NivelConocimientoExperto(participanteBobo));
		System.out.println(participanteBobo.getMuestrasEnviadas().size());
		System.out.println(participanteBobo.getMuestrasVerificadas().size());
		System.out.println(participanteBobo.getNivel().getClass());
		System.out.println(participanteBobo.getNivel());
		//assertTrue(participanteBobo.getNivel() instanceof NivelConocimientoExperto);
		
	}

}
