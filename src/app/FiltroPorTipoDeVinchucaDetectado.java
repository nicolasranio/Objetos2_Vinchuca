package app;

import muestra.Muestra;
import muestra.TipoVinchuca;

public class FiltroPorTipoDeVinchucaDetectado implements Filtro {

	private TipoVinchuca tipoVinchuca;
	
	public FiltroPorTipoDeVinchucaDetectado(TipoVinchuca tipoVinchuca) {
		super();
		this.tipoVinchuca = tipoVinchuca;
	}

	@Override
	public Boolean aplicar(Muestra muestra) {
		return muestra.getTipoVinchuca().equals(this.tipoVinchuca);
	}
}
