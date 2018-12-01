package app;

import java.time.LocalDate;

import muestra.Muestra;

public class FiltroPorFechaDeCreacionAnterior implements Filtro {

private LocalDate fecha;
	
	public FiltroPorFechaDeCreacionAnterior(LocalDate fecha) {
		super();
		this.fecha = fecha;
	}

	@Override
	public Boolean aplicar(Muestra muestra) {
		return muestra.getFechaEnvio().isEqual(this.fecha)
				|| muestra.getFechaEnvio().isBefore(this.fecha);
	}

}
