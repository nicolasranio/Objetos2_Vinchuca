package organizacion;

import muestra.Muestra;
import zonaDeCobertura.ZonaCobertura;

public interface FuncionalidadExterna {

	public void nuevoEvento(Organizacion organizacion,ZonaCobertura zona,Muestra muestra);
}
