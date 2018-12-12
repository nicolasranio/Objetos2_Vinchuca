package testParticipante;

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
	private NivelConocimientoBasico basico;
	private Participante participante;
	private List<Muestra> mockList;

	
	@Before
	public void setUp() throws Exception {
		mockList = mock(ArrayList.class);
		participante = mock(Participante.class);
		basico = new NivelConocimientoBasico();
	}
	
	@Test
	public void testNivelConocimientoBasicoDevuelveFalsoCuandoLeConsultanEsDefinitorio() {
		
		assertEquals(basico.esDefinitorio(),false);
	}

}	
