package app;

import muestra.Muestra;

public class FiltroOr extends FiltroCompuesto {

	
	/**
	 * Construye un filtro compuesto por otros dos filtros.
	 * 
	 * @param filtroA
	 * Es uno de los filtros que componen al filtro compuesto.
	 * 
	 * @param filtroB
	 * Es el otro filtro que compone al filtro compuesto.
	 */
	public FiltroOr(Filtro filtroA, Filtro filtroB) {
		super(filtroA, filtroB);
	}
	
	/**
	 * Retorna verdadero si la muestra aplica para el filtro.
	 * 
	 * @param muestra
	 * La muestra que se somete al criterio del filtro.
	 * 
	 * @return Verdadero si la muestra aplica para alguno de los dos filtros dados.
	 */
	@Override
	public Boolean aplicar(Muestra muestra) {
		return filtroA.aplicar(muestra) || filtroB.aplicar(muestra);
	}
}
