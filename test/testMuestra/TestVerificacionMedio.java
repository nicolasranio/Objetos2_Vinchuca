package testMuestra;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;


import muestra.*;
import muestra.NivelVerificacionMedio;
import participante.Participante;

public class TestVerificacionMedio {

	private Muestra muestra;
	private NivelVerificacionMedio nivelMedio; 
	private List<Participante> mockList;
	
	
	@Before
	public void setup() throws Exception{
		muestra = mock(Muestra.class);
		nivelMedio = new NivelVerificacionMedio(muestra);
		mockList = mock(List.class);
		
		
	}
	
	@Test
	public void testNivelVerificacionMedioCambiaDeEstadoAlPasarValidacion() {
		when(muestra.getVerificadores()).thenReturn(mockList);
		when(mockList.size()).thenReturn(3);
		nivelMedio.cambiarEstado();
		verify(muestra).setNivelVerificacion(isA(NivelVerificacionAlto.class));
	}
	
	@Test
	public void testNivelVerificacionBajoNoCambiaDeEstadoAlNOPasarValidacion() {
		when(muestra.getVerificadores()).thenReturn(mockList);
		when(mockList.size()).thenReturn(2);
		nivelMedio.cambiarEstado();
		verify(muestra, never()).setNivelVerificacion(isA(NivelVerificacionAlto.class));
		
	}

}

