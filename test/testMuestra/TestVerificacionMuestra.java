package testMuestra;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import muestra.TipoVinchuca;
import muestra.VerificacionMuestra;
import participante.Participante;

public class TestVerificacionMuestra {

	private VerificacionMuestra verificacion;
	private Participante participante;
	private TipoVinchuca tipoVinchuca;
	
	@Before
	public void setUp() throws Exception {
	
		participante = mock(Participante.class);
		tipoVinchuca = mock(TipoVinchuca.class);
		verificacion = new VerificacionMuestra(participante,tipoVinchuca);
	}

	@Test
	public void testConstructor() {
				
	}

}
