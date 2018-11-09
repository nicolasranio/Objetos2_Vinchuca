package src;

import java.util.ArrayList;
import java.util.List;

public class Participante {

	private String alias;
	private INivelConocimiento nivelConocimiento; //patron state
	private List <Muestra> muestrasEnviadas;
	private List <Muestra> muestrasVerificadas;
	
	public Participante(String alias){
		this.alias = alias;
		this.nivelConocimiento = new NivelConocimientoBasico(this); //siempre inicia con nivel básico
		this.muestrasEnviadas = new ArrayList <Muestra> ();
		this.muestrasVerificadas = new ArrayList <Muestra> ();
	}
	
	//esto me sirve para instanciar un experto
	public Participante(String alias, INivelConocimiento nivelConocimiento){
		this.alias = alias;
		this.nivelConocimiento=nivelConocimiento;
		this.muestrasEnviadas = new ArrayList <Muestra> ();
		this.muestrasVerificadas = new ArrayList <Muestra> ();
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
		this.nivelConocimiento.verificarMuestra(muestra);
		aplicacion.agregarMuestra(muestra);
		this.muestrasEnviadas.add(muestra);
	}
	
	
	public void verificarMuestra(Muestra muestra, TipoVinchuca validacion){
		if (muestrasEnviadas.contains(muestra) || muestrasVerificadas.contains(muestra)){
			new RuntimeException("No se puede verificar");
			
		}
		else {
			this.nivelConocimiento.verificarMuestra(muestra);
			muestra.verificar(this,validacion);
		}	
	}
	
}
