package app;

import muestra.Muestra;

public class FiltroOr extends FiltroCompuesto {

	public FiltroOr(Filtro filtroA, Filtro filtroB) {
		super(filtroA, filtroB);
	}
	
	@Override
	public Boolean aplicar(Muestra muestra) {
		return filtroA.aplicar(muestra) || filtroB.aplicar(muestra);
	}
}
