package src;

public abstract class INivelConocimiento {

	private Participante participante;

	public INivelConocimiento(Participante participante){
		this.participante = participante;	
	}
	
	public abstract void cambiarEstado();
	
}
