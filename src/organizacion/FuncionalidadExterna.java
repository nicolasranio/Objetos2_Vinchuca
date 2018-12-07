package organizacion;

import muestra.Muestra;
import zonaDeCobertura.ZonaCobertura;
/**
 * Contrato que deben cumplir las diferentes funcionalides Externas.
 * Una funcionalidad Externa es configurable por una organización tanta para actuar ante la carga o validacion 
 * de una muestra.
 * En el ejercicio pueden llegar a ser la misma.
 */
public interface FuncionalidadExterna {
	
	public void nuevoEvento(Organizacion organizacion,ZonaCobertura zona,Muestra muestra);
}
