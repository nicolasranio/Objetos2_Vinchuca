package participante;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
	private List <VerificacionMuestra> muestrasVerificadas;  //verificacion y fecha de verificacion
	
	/**
	 * Constructor por default
	 * @param alias
	 */
	public Participante(String alias){
		this.alias = alias;
		this.nivelConocimiento = new NivelConocimientoBasico(); //siempre inicia con nivel básico
		this.muestrasEnviadas = new ArrayList <Muestra> ();
		this.muestrasVerificadas = new ArrayList <VerificacionMuestra> ();
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
		this.muestrasVerificadas = new ArrayList <VerificacionMuestra> ();
	}
	
	
	public List<Muestra> getMuestrasEnviadasUltimoMes(){
		return muestrasEnviadas.stream().filter(muestra -> Duration.between(muestra.getFechaEnvio().atStartOfDay(), LocalDate.now().atStartOfDay())
				.toDays() < 31)
				.collect(Collectors.toList());
	}
	
	public List<Muestra> getMuestrasEnviadas(){
		//return muestrasEnviadas.stream().filter(muestras -> muestra.getFechaEnvio() < )
		//to do
		return this.muestrasEnviadas;
	}
	
	public List<VerificacionMuestra> getMuestrasVerificadas(){
		return muestrasVerificadas;
	}
	
	public INivelConocimiento getNivel() {
		return this.nivelConocimiento;
	}
	
	public String getAlias() {
		return this.alias;
	}
	
	public void agregarMuestraEnviada(Muestra muestra) {
		this.muestrasEnviadas.add(muestra);
	}
	
	public void agregarVerificacionMuestra(VerificacionMuestra muestra) {
		this.muestrasVerificadas.add(muestra);
	}

	public void verificarConocimiento() {
		if ((this.getMuestrasEnviadasUltimoMes().size()>10) && (this.getMuestrasVerificadas().size()>20)){
			this.nivelConocimiento = new NivelConocimientoExperto();
		}
		else {
			this.nivelConocimiento = new NivelConocimientoBasico();
		}
	}
	
	
	/**
	 * Agrega la muestra a la coleccion del participante y verifico si hubo cambios en el conocimiento
	 * @param muestra
	 * @param aplicacion
	 */
	public void enviarMuestra(Muestra muestra, AplicacionWeb aplicacion){
		this.nivelConocimiento.verificarMuestra(muestra);
		aplicacion.agregarMuestra(muestra);
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
		this.validarQueNoHayaSidoEnviada(muestra);
		this.validarQueNoHayaSidoVerificada(muestra);
		this.agregarVerificacionMuestra(new VerificacionMuestra(this,tipoVinchuca));
		muestra.verificar(new VerificacionMuestra(this,tipoVinchuca));
		this.nivelConocimiento.verificarMuestra(muestra);
		this.verificarConocimiento();
	}
	
	public void validarQueNoHayaSidoEnviada(Muestra muestra) throws Exception{
		if (muestra.getAliasRecolector() == this.getAlias()){
			throw new MuestraYaEnviadaException();
		}
	}
	
	public void validarQueNoHayaSidoVerificada(Muestra muestra) throws Exception{
		if  (this.getMuestrasEnviadas().contains(muestra)){
			throw new MuestraYaVerificadaException();
		}
	}

	
}
