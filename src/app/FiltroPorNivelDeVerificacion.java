package app;

import muestra.INivelVerificacion;
import muestra.Muestra;

public class FiltroPorNivelDeVerificacion implements Filtro {

	private INivelVerificacion nivelVerificacion;
	
	public FiltroPorNivelDeVerificacion(INivelVerificacion nivelVerificacion) {
		super();
		this.nivelVerificacion = nivelVerificacion;
	}
	
	
	@Override
	public Boolean aplicar(Muestra muestra) {
		return muestra.getNivelVerificacion().equals(this.nivelVerificacion);
	}

}
