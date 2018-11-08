package src;

public abstract class INivelConocimiento {

	protected Participante participante;

	public INivelConocimiento(Participante participante){
		this.participante = participante;	
	}
	
	public abstract void cambiarEstado();
	
	public abstract void verificarMuestra(Muestra muestra);
	
	public abstract void enviarMuestra(Muestra muestra);
}
