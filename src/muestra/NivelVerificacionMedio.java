package muestra;

public class NivelVerificacionMedio extends INivelVerificacion {

	public NivelVerificacionMedio(Muestra muestra) {
		super(muestra);
	}

	@Override
	public void cambiarEstado() {
		if (this.muestra.getVerificadores().size()==3) this.muestra.setNivelVerificacion(new NivelVerificacionAlto(this.muestra));
	}

}
