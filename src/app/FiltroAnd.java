package app;

import muestra.Muestra;

public class FiltroAnd extends FiltroCompuesto{

	public FiltroAnd(Filtro filtroA, Filtro filtroB) {
		super(filtroA, filtroB);
	}
	
	@Override
	public Boolean aplicar(Muestra muestra) {
		return filtroA.aplicar(muestra) && filtroB.aplicar(muestra);
	}
}
