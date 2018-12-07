package app;

import muestra.Muestra;

public abstract class FiltroCompuesto implements Filtro {

	protected Filtro filtroA;
	protected Filtro filtroB;
	
	
	/**
	 * Construye un filtro compuesto por otros dos filtros.
	 * 
	 * @param filtroA
	 * Es uno de los filtros que componen al filtro compuesto.
	 * 
	 * @param filtroB
	 * Es el otro filtro que compone al filtro compuesto.
	 */
	public FiltroCompuesto(Filtro filtroA, Filtro filtroB) {
		super();
		this.filtroA = filtroA;
		this.filtroB = filtroB;
	}
	
	/**
	 * Retorna verdadero si la muestra aplica para el filtro.
	 * 
	 * @param muestra           
	 * La muestra que se somete al criterio del filtro.
	 * 
	 * @return Verdadero si la muestra aplica para el filtro.
	 */
	@Override
	public abstract Boolean aplicar(Muestra muestra);
}
