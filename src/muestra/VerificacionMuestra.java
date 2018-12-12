package muestra;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import participante.NivelConocimientoExperto;
import participante.Participante;

public class VerificacionMuestra {
	
	private Muestra muestra;
	private Participante participante;
	private TipoVinchuca tipoVinchuca;
	private LocalDate fechaVerificacion;
	
	
	/**
	 * Construye una verificacion de muestra con sus elementos correspondientes.
	 * 
	 * @param muestra
	 * La muestra que ha sido verificada.
	 * 
	 * @param participante
	 * Es el participante que verifico dicha muestra.
	 * 
	 * @param validacion
	 * El tipo de vinchuca a validar.
	 * 
	 */
	public VerificacionMuestra(Muestra muestra, Participante participante, TipoVinchuca validacion) {
		this.muestra=muestra;
		this.participante = participante;
		this.tipoVinchuca = validacion;
		this.fechaVerificacion= LocalDate.now();
	}
	
	/**
	 * Retorna el participante que verifico dicha muestra.
	 * 
	 * @return El participante que verifico dicha muestra.
	 */
	public Participante getParticipante() {
		return participante;
	}
	
	/**
	 * Retorna el tipo de vinchuca a validar.
	 * 
	 * @return El tipo de vinchuca a validar.
	 */
	public TipoVinchuca getTipoVinchuca() {
		return tipoVinchuca;
	}
	
	
	/**
	 * Retorna la fecha de la verificacion.
	 *
	 * 
	 * @return La fecha de la verificacion.
	 */
	public LocalDate getFechaVerificacion(){
		return fechaVerificacion;
	}
	
	
	/**
	 * Retorna la muestra que ha sido verificada.
	 * 
	 * @return La muestra que ha sido verificada.
	 */
	public Muestra getMuestra(){
		return muestra;
	}

	
	public boolean esMenorAXDias(Integer dias){
		
		Period period =Period.between(this.getFechaVerificacion(), LocalDate.now());
		return period.getDays()<dias;
	}

	public boolean fueRealizadaPorExperto() {
		
		return this.getParticipante().getNivel().esDefinitorio();
	}
	
}
