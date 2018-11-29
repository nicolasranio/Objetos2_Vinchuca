package testMuestra;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import muestra.*;

public class TestVerificacionBajo {
	private Muestra muestra;
	private NivelVerificacionBajo nivelBajo; 
	
	
	@Before
	public void setup() throws Exception{
		muestra = mock(Muestra.class);
		nivelBajo = new NivelVerificacionBajo(muestra);
		
	}
	
	@Test
	public void testNivelVerificacionBajoCambiaDeEstadoAlPasarValidacion() {
		when(muestra.verificacionesValidas()).thenReturn(2);
		nivelBajo.cambiarEstado();
		verify(muestra).setNivelVerificacion(isA(NivelVerificacionMedio.class));
	}
	
	@Test
	public void testNivelVerificacionBajoNOCambiaDeEstadoPorqueNoPasaValidacion() {
		when(muestra.verificacionesValidas()).thenReturn(1);
		nivelBajo.cambiarEstado();
		verify(muestra, never()).setNivelVerificacion(isA(NivelVerificacionMedio.class));
	}

}
