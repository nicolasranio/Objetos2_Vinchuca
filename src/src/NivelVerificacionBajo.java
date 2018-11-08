package src;

public class NivelVerificacionBajo extends INivelVerificacion {


	public NivelVerificacionBajo(Muestra muestra) {
		super(muestra);
	}

	//cambia a estado Medio cuando se den las condiciones, alcanza con validar que existan dos validadores
	public void cambiarEstado(Muestra muestra) {
		if (muestra.getVerificadores().size()>=2) muestra.setNivelVerificacion(new NivelVerificacionMedio(muestra));
	}
	
}
