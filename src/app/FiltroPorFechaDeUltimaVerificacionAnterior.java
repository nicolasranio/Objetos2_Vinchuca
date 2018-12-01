package app;

import java.time.LocalDate;

import muestra.Muestra;

public class FiltroPorFechaDeUltimaVerificacionAnterior implements Filtro {

private LocalDate fecha;
	
	public FiltroPorFechaDeUltimaVerificacionAnterior(LocalDate fecha) {
		super();
		this.fecha = fecha;
	}
	
	@Override
	public Boolean aplicar(Muestra muestra) {
		return muestra.getVerificaciones().get(muestra.getVerificaciones().size()-1).getFechaVerificacion().isEqual(this.fecha)
				|| muestra.getVerificaciones().get(muestra.getVerificaciones().size()-1).getFechaVerificacion().isBefore(this.fecha);
	}


}
