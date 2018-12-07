package app;

import java.time.LocalDate;

import muestra.Muestra;

public class FiltroPorFechaDeCreacionPosterior implements Filtro{

	private LocalDate fecha;
	
	
	/**
	 * Construye un filtro especializado en comprobar si una muestra fue creada
	 * despues de una determinada fecha.
	 * 
	 * @param fecha
	 * La fecha de la cual se quiere saber si la muestra fue despues antes de la misma.
	 * 
	 */
	public FiltroPorFechaDeCreacionPosterior(LocalDate fecha) {
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
		return muestra.getFechaEnvio().compareTo(this.fecha) >= 0;
	}

}
