package app;

import muestra.Muestra;

public abstract class FiltroCompuesto implements Filtro {

	protected Filtro filtroA;
	protected Filtro filtroB;
	
	public FiltroCompuesto(Filtro filtroA, Filtro filtroB) {
		super();
		this.filtroA = filtroA;
		this.filtroB = filtroB;
	}
	
	@Override
	public abstract Boolean aplicar(Muestra muestra);
}
