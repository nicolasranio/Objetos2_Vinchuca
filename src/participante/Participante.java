package participante;

import java.util.ArrayList;
import java.util.List;

import app.AplicacionWeb;
import exceptions.MuestraYaEnviadaException;
import exceptions.MuestraYaVerificadaException;
import muestra.Muestra;
import muestra.TipoVinchuca;

public class Participante {

	private String alias;
	private INivelConocimiento nivelConocimiento; //patron state
	private List <Muestra> muestrasEnviadas;
	private List <Muestra> muestrasVerificadas;
	
	/**
	 * Constructor por default
	 * @param alias
	 */
	public Participante(String alias){
		this.alias = alias;
		this.nivelConocimiento = new NivelConocimientoBasico(); //siempre inicia con nivel básico
		this.muestrasEnviadas = new ArrayList <Muestra> ();
		this.muestrasVerificadas = new ArrayList <Muestra> ();
	} 
	 
	/**
	 * Sobrecarga del constructor para instanciar expertos
	 * @param alias
	 * @param nivelConocimiento
	 */
	public Participante(String alias, INivelConocimiento nivelConocimiento){
		this.alias = alias;
		this.nivelConocimiento=nivelConocimiento;
		this.muestrasEnviadas = new ArrayList <Muestra> ();
		this.muestrasVerificadas = new ArrayList <Muestra> ();
	}
	
	public List<Muestra> getMuestrasEnviadas(){
		return muestrasEnviadas;
	}
	
	public void agregarMuestraEnviada(Muestra muestra) {
		this.muestrasEnviadas.add(muestra);
	}
	
	public List<Muestra> getMuestrasVerificadas(){
		return muestrasVerificadas;
	}
	
	public void agregarMuestraVerificada(Muestra muestra) {
		this.muestrasVerificadas.add(muestra);
	}

	public void verificarEstado() {
		if ((this.getMuestrasEnviadas().size()>10) && (this.getMuestrasVerificadas().size()>20)){
			this.nivelConocimiento = new NivelConocimientoExperto();
		}
		else {
			this.nivelConocimiento = new NivelConocimientoBasico();
		}
	}
	
	
	/**
	 * Agrega la muestra a la coleccion del participante 
	 * @param muestra
	 * @param aplicacion
	 */
	public void enviarMuestra(Muestra muestra, AplicacionWeb aplicacion){
		this.nivelConocimiento.verificarMuestra(muestra);
		aplicacion.agregarMuestra(muestra);
		this.agregarMuestraEnviada(muestra);
		this.verificarEstado();
	}
	
	/**
	 * Valida la muestra
	 * @param muestra
	 * @param validacion
	 * @throws Exception
	 */
	public void verificarMuestra(Muestra muestra, TipoVinchuca validacion) throws Exception{
		this.validarQueNoHayaSidoEnviada(muestra);
		this.validarQueNoHayaSidoVerificada(muestra);
		this.agregarMuestraVerificada(muestra);
		muestra.verificar(this,validacion);
		this.nivelConocimiento.verificarMuestra(muestra);
		this.verificarEstado();
	}
	
	public void validarQueNoHayaSidoEnviada(Muestra muestra) throws Exception{
		if (muestrasEnviadas.contains(muestra)){
			throw new MuestraYaEnviadaException();
		}
	}
	
	public void validarQueNoHayaSidoVerificada(Muestra muestra) throws Exception{
		if  (muestrasVerificadas.contains(muestra)){
			throw new MuestraYaVerificadaException();
		}
	}

	public INivelConocimiento getNivel() {
		return this.nivelConocimiento;
	}
}
