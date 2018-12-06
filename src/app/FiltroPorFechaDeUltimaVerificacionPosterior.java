package app;

import java.time.LocalDate;

import muestra.Muestra;

public class FiltroPorFechaDeUltimaVerificacionPosterior implements Filtro {

	private LocalDate fecha;
	
	public FiltroPorFechaDeUltimaVerificacionPosterior(LocalDate fecha) {
		super();
		this.fecha = fecha;
	}
	
	@Override
	public Boolean aplicar(Muestra muestra) {
		return muestra.getVerificaciones().get(muestra.getVerificaciones().size()-1).getFechaVerificacion().compareTo(this.fecha) >= 0;
	}

}
