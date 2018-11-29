package testMuestra;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import muestra.*;
import participante.Participante;

public class TestVerificacionMuestra {

	private VerificacionMuestra verificacion;
	private Participante participante;
	private TipoVinchuca tipoVinchuca;
	private Muestra muestra;
	
	@Before
	public void setUp() throws Exception {
	
		participante = mock(Participante.class);
		tipoVinchuca = mock(TipoVinchuca.class);
		muestra = mock(Muestra.class);
		verificacion = new VerificacionMuestra(muestra,participante,tipoVinchuca);
	}

	@Test
	public void testConstructor() {
				
	}

}
