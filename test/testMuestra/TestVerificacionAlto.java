package testMuestra;

import static org.junit.Assert.*;
import muestra.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.Test;

import exceptions.EstadoInvalidoException;
import exceptions.MuestraYaEnviadaException;



public class TestVerificacionAlto {
	private Muestra muestra;
	private NivelVerificacionAlto nivelAlto; 
	
	@Before
	public void setup() throws Exception{
		muestra = mock(Muestra.class);
		nivelAlto = new NivelVerificacionAlto(muestra);
			}
	
	@Test(expected = EstadoInvalidoException.class)
	public void testVerificacionAltoLanzaExcepcionAlCambiarEstado() throws Exception{
		nivelAlto.cambiarEstado();
		verify(muestra, never()).setNivelVerificacion(isA(INivelVerificacion.class));
	}

}
