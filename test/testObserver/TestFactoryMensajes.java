package testObserver;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import muestra.Muestra;
import observer.EMensajesObservables;
import observer.FactoryMensajes;
import observer.MensajeObserver;
import observer.MensajeObserverAlta;

import static org.mockito.Mockito.*;

public class TestFactoryMensajes {

	
		private MensajeObserverAlta mensajeAlta;
		private Muestra muestra;
		
		
	@Before
	public void setUp() throws Exception {
		
		mensajeAlta = mock (MensajeObserverAlta.class);
		muestra = mock(Muestra.class);
	}

	
	//cuando genero un mensaje de alta retorna un objeto de clase MensajeAlta
	@Test
	public void test() {
		
		MensajeObserver msj = FactoryMensajes.crearMensaje(EMensajesObservables.Alta, muestra);
		
		assertEquals(MensajeObserverAlta.class, msj.getClass());	
		assertEquals(muestra, msj.getMuestra());
	}

}
