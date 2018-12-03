package testMuestra;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

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
		tipoVinchuca = TipoVinchuca.Imagen_poco_clara;
		muestra = mock(Muestra.class);
		verificacion = new VerificacionMuestra(muestra,participante,tipoVinchuca);
	}

	@Test
	public void testConstructor() {
		
		assert(verificacion.getMuestra().equals(muestra));
		assert(verificacion.getParticipante().equals(participante));
		assert(verificacion.getTipoVinchuca().equals(TipoVinchuca.Imagen_poco_clara));
		assert(verificacion.getFechaVerificacion().isEqual(LocalDate.now()));
	}

}
