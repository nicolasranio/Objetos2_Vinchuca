package participante;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.time.temporal.ChronoUnit;

import app.AplicacionWeb;
import exceptions.MuestraYaEnviadaException;
import exceptions.MuestraYaVerificadaException;
import muestra.Muestra;
import muestra.TipoVinchuca;
import muestra.VerificacionMuestra;

public class Participante {

	private String alias;
	private INivelConocimiento nivelConocimiento; //patron state
	private List <Muestra> muestrasEnviadas;  //muestra tiene fecha de envio
	private List <VerificacionMuestra> verificacionesDeMuestras;  //verificacion y fecha de verificacion
	
	/**
	 * Constructor por default
	 * @param alias
	 */
	public Participante(String alias){
		this.alias = alias;
		this.nivelConocimiento = new NivelConocimientoBasico(); //siempre inicia con nivel básico
		this.muestrasEnviadas = new ArrayList <Muestra> ();
		this.verificacionesDeMuestras = new ArrayList <VerificacionMuestra> ();
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
		this.verificacionesDeMuestras = new ArrayList <VerificacionMuestra> ();
	}
	
	
	public List<Muestra> getMuestrasEnviadasUltimoMes(){
		return muestrasEnviadas.stream()
				.filter(muestra -> muestra.esMenorAXDias(31))
				.collect(Collectors.toList());
	}
	
	public List<VerificacionMuestra> getVerificacioneDeMuestras(){
		return verificacionesDeMuestras;
	}
	
	public List<Muestra> getMuestrasVerificadas(){
		return this.verificacionesDeMuestras.stream()
				.map(verificacion -> verificacion.getMuestra()).collect(Collectors.toList());
	}
	public List<VerificacionMuestra> getMuestrasVerificadasUltimoMes(){
		return this.verificacionesDeMuestras.stream()
				.filter(verificacion -> verificacion.esMenorAXDias(31))
				.collect(Collectors.toList());
	}
	
	public INivelConocimiento getNivel() {
		return this.nivelConocimiento;
	}
	
	public void agregarMuestraEnviada(Muestra muestra) {
		this.muestrasEnviadas.add(muestra);
	}
	
	public void agregarVerificacionMuestra(VerificacionMuestra verificacionMuestra) {
		this.verificacionesDeMuestras.add(verificacionMuestra);
	}

	public void verificarConocimiento() {
		if (this.condicionAVerificar()){
			this.nivelConocimiento = new NivelConocimientoExperto();
		}
		else{
			this.nivelConocimiento = new NivelConocimientoBasico();
		}
		
	}
	/**
	 * Condición por la que un participante cambiaría su nivelDeConociemiento
	 * @return
	 */
	public boolean condicionAVerificar() {
		return (this.getMuestrasEnviadasUltimoMes().size()>10) 
				&& (this.getMuestrasVerificadasUltimoMes().size()>20);
	}

	/**
	 * Agrega la muestra a la coleccion del participante y verifico si hubo cambios en el conocimiento
	 * @param muestra
	 * @param aplicacion
	 */
	public void enviarMuestra(Muestra muestra, AplicacionWeb aplicacion){
		this.nivelConocimiento.verificarMuestra(muestra);
		aplicacion.agregarMuestra(muestra);
		muestra.informarCarga();
		this.agregarMuestraEnviada(muestra);
		this.verificarConocimiento();
	}
	
	/**
	 * Valida la muestra y verifica cambios en el conocimiento
	 * @param muestra
	 * @param tipoVinchuca
	 * @throws Exception
	 */
	public void verificarMuestra(Muestra muestra, TipoVinchuca tipoVinchuca) throws Exception{
		//validaciones
		VerificacionMuestra verificacion = new VerificacionMuestra(muestra, this, tipoVinchuca);
		this.validarQueNoHayaSidoEnviada(muestra);
		this.validarQueNoHayaSidoVerificada(muestra);
		this.agregarVerificacionMuestra(verificacion);
		muestra.verificar(verificacion);
		this.nivelConocimiento.verificarMuestra(muestra);
		this.verificarConocimiento();
	}
	/**
	 * Valida que la @param muestra no haya sido enviada anteriormente y en caso de haberlo hecho
	 * @throws Exception
	 */
	public void validarQueNoHayaSidoEnviada(Muestra muestra) throws Exception{
		if (this.muestrasEnviadas.contains(muestra)) {
			throw new MuestraYaEnviadaException();
		}
	}
	/**
	 * Valida que @param muestra NUNCA haya sido validada anteriormente y en caso de que lo haya sido
	 * @throws Exception
	 */
	public void validarQueNoHayaSidoVerificada(Muestra muestra) throws Exception{
		if  (this.getMuestrasVerificadas().contains(muestra)){
			throw new MuestraYaVerificadaException();
		}
	}

	
}
