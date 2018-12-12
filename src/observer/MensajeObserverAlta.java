package observer;

import muestra.Muestra;
import organizacion.Organizacion;
import zonaDeCobertura.ZonaCobertura;

public class MensajeObserverAlta extends MensajeObserver {

	public MensajeObserverAlta(Muestra muestra) {
		super(muestra);
	}

	@Override
	public void ejecutarFuncionalidad(ZonaCobertura zona, Organizacion organizacion) {
		organizacion.ejecucionFuncionalidadTrasCarga(zona, this.getMuestra());
		
	}




}
