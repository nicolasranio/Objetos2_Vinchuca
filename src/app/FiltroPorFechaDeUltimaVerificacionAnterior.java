package app;

import java.time.LocalDate;

import muestra.Muestra;

public class FiltroPorFechaDeUltimaVerificacionAnterior implements Filtro {

	private LocalDate fecha;
	

	/**
	 * Construye un filtro especializado en comprobar si la ultima verificacion
	 * de una muestra se realizo antes de una determinada fecha.
	 * 
	 * @param fecha
	 * La fecha de la cual se quiere saber si la ultima verificacion de una
	 * muestra se realizo antes de la misma.
	 * 
	 */
	public FiltroPorFechaDeUltimaVerificacionAnterior(LocalDate fecha) {
		super();
		this.fecha = fecha;
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
		return muestra.getVerificaciones().get(muestra.getVerificaciones().size()-1).getFechaVerificacion().compareTo(this.fecha) <= 0;
	}


}
