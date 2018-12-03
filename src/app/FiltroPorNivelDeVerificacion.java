package app;

import muestra.INivelVerificacion;
import muestra.Muestra;
import muestra.NivelVerificacionAlto;
import muestra.NivelVerificacionBajo;
import muestra.NivelVerificacionMedio;

public class FiltroPorNivelDeVerificacion implements Filtro {

	private INivelVerificacion nivelVerificacion;
	
	public FiltroPorNivelDeVerificacion(INivelVerificacion nivelVerificacion) {
		super();
		this.nivelVerificacion = nivelVerificacion;
	}
	
	
	@Override
	public Boolean aplicar(Muestra muestra) {
		return ((muestra.getNivelVerificacion() instanceof NivelVerificacionBajo) && (this.nivelVerificacion instanceof NivelVerificacionBajo))
			|| ((muestra.getNivelVerificacion() instanceof NivelVerificacionMedio) && (this.nivelVerificacion instanceof NivelVerificacionMedio))
			|| ((muestra.getNivelVerificacion() instanceof NivelVerificacionAlto) && (this.nivelVerificacion instanceof NivelVerificacionAlto));
	}

}
