package muestra;

public class NivelVerificacionBajo extends INivelVerificacion {

	public NivelVerificacionBajo(Muestra muestra) {
		super(muestra);
	}

	//cambia a estado Medio cuando se den las condiciones, alcanza con validar que existan dos validadores
	public void cambiarEstado() {
		if (this.muestra.verificacionesValidas()==2) this.muestra.setNivelVerificacion(new NivelVerificacionMedio(this.muestra));
	}
	
}
