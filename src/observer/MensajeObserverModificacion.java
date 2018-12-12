package observer;

import muestra.Muestra;
import organizacion.Organizacion;
import zonaDeCobertura.ZonaCobertura;

public class MensajeObserverModificacion extends MensajeObserver {

	public MensajeObserverModificacion(Muestra muestra) {
		super(muestra);
	}

	@Override
	public void ejecutarFuncionalidad(ZonaCobertura zona, Organizacion organizacion) {
		organizacion.ejecucionFuncionalidadTrasValidacion(zona, this.getMuestra());
	}



}
