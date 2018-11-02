package src;

import java.util.List;

public class Participante {

	private String alias;
	private INivelConocimiento nivelConocimiento; //patron state
	private List <Muestra> muestrasEnviadas;
	private List <Muestra> muestrasVerificadas;
	
	public Participante(String alias){
		this.alias = alias;
		this.nivelConocimiento = new NivelConocimientoBasico(this);
	}
	
	public List<Muestra> getMuestrasEnviadas(){
		return muestrasEnviadas;
	}
	
	public List<Muestra> getMuestrasVerificadas(){
		return muestrasEnviadas;
	}

	public void setEstado(INivelConocimiento nivelConocimiento) {
		this.nivelConocimiento = nivelConocimiento;
	}
	
	public void enviarMuestra(Muestra muestra, AplicacionWeb aplicacion){
		aplicacion.agregarMuestra(muestra);
		this.muestrasEnviadas.add(muestra);
	}
	
	
	public void verificarMuestra(Muestra muestra){
		if (!(muestrasEnviadas.contains(muestra) || muestrasVerificadas.contains(muestra))){
			//verificacion de la muestra..
		}
		
	}
	
}
