package app;

import java.time.LocalDate;

import muestra.Muestra;

public class FiltroPorFechaDeCreacionPosterior implements Filtro{

	private LocalDate fecha;
	
	public FiltroPorFechaDeCreacionPosterior(LocalDate fecha) {
		super();
		this.fecha = fecha;
	}

	@Override
	public Boolean aplicar(Muestra muestra) {
		return muestra.getFechaEnvio().isEqual(this.fecha)
				|| muestra.getFechaEnvio().isAfter(this.fecha);
	}

}