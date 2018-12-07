package app;

import muestra.INivelVerificacion;
import muestra.Muestra;
import muestra.NivelVerificacionAlto;
import muestra.NivelVerificacionBajo;
import muestra.NivelVerificacionMedio;

public class FiltroPorNivelDeVerificacion implements Filtro {

	private INivelVerificacion nivelVerificacion;
	
	/**
	 * Construye un filtro especializado en comprobar si el nivel de verificacion
	 * de una muestra es el mismo que el especificado en el filtro.
	 * 
	 * @param nivelVerificacion
	 * El nivel de verificacion del cual se quiere saber si el mismo nivel 
	 * de la muestra dada.
	 */
	public FiltroPorNivelDeVerificacion(INivelVerificacion nivelVerificacion) {
		super();
		this.nivelVerificacion = nivelVerificacion;
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
		return ((muestra.getNivelVerificacion() instanceof NivelVerificacionBajo) && (this.nivelVerificacion instanceof NivelVerificacionBajo))
			|| ((muestra.getNivelVerificacion() instanceof NivelVerificacionMedio) && (this.nivelVerificacion instanceof NivelVerificacionMedio))
			|| ((muestra.getNivelVerificacion() instanceof NivelVerificacionAlto) && (this.nivelVerificacion instanceof NivelVerificacionAlto));
	}

}
