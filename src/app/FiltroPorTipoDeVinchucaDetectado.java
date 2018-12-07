package app;

import muestra.Muestra;
import muestra.TipoVinchuca;

public class FiltroPorTipoDeVinchucaDetectado implements Filtro {

	private TipoVinchuca tipoVinchuca;
	
	/**
	 * Construye un filtro especializado en comprobar si el tipo de vinchuca
	 * detectado de una muestra es el mismo que el especificado en el filtro.
	 * 
	 * @param tipoVinchuca
	 * El tipo de vinchuca de la cual se quiere saber si el mismo detectado
	 * de la muestra dada.
	 */
	public FiltroPorTipoDeVinchucaDetectado(TipoVinchuca tipoVinchuca) {
		super();
		this.tipoVinchuca = tipoVinchuca;
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
	public Boolean aplicar(Muestra muestra) {
		return muestra.getTipoVinchuca().equals(this.tipoVinchuca);
	}
}
