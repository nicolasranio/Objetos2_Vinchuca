package muestra;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import participante.Participante;
import zonaDeCobertura.ZonaCobertura;

public class Muestra extends Observable{

	private TipoVinchuca tipoVinchuca;
	private String fotoVinchuca;  //url de la imagen
	private Ubicacion ubicacion;
	private String aliasRecolector;
	private INivelVerificacion nivelVerificacion;  //Patron state
	private List<VerificacionMuestra> verificaciones;
	private LocalDate fechaEnvio;
	


	public Muestra(TipoVinchuca tipoVinchuca, String fotoVinchuca, Ubicacion ubicacion, String alias){
		super();
		this.tipoVinchuca=tipoVinchuca;
		this.fotoVinchuca= fotoVinchuca;
		this.ubicacion= ubicacion;
		this.aliasRecolector=alias;
		this.nivelVerificacion= new NivelVerificacionBajo(this); 
		this.verificaciones= new ArrayList<VerificacionMuestra>();
		this.fechaEnvio=LocalDate.now(); //fecha actual
	}
	
	public Double distanciaAMuestra(Muestra muestraB){
		return this.ubicacion.calcularDistancia(muestraB.getUbicacion());
	}

	public List<VerificacionMuestra> getVerificaciones() {
		return verificaciones;
	}
	
	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}
	
	public TipoVinchuca getTipoVinchuca() {
		return this.tipoVinchuca;
	}
	
	public String getFotoVinchuca() {
		return this.fotoVinchuca;
	}
	
	public void setFechaEnvio(LocalDate fecha){
		this.fechaEnvio=fecha;
	}
	
	public String getAliasRecolector() {
		return this.aliasRecolector;
	}

	public LocalDate getFechaEnvio() {
		return this.fechaEnvio;
	}
	
	public INivelVerificacion getNivelVerificacion() {
		return this.nivelVerificacion;
	}
	
	/**
	 * Retorna lista de muestras cercanas a menos de {distancia} km de esta ubicacion
	 * @param muestras (Lista)
	 * @param distancia en km
	 * @return lista de muestras cercanas
	 */
	public List<Muestra> muestrasCercanas(List<Muestra> muestras, Double distancia){
		return muestras.stream()
				.filter(muestra -> this.distanciaAMuestra(muestra)<distancia)
				.collect(Collectors.toList());
	}

	public void setNivelVerificacion(INivelVerificacion nivelVerificacion) {
		this.nivelVerificacion=nivelVerificacion;
	}
	
	
	public List<Participante> getVerificadores() {
		return verificaciones.stream().map(val -> val.getParticipante()).collect(Collectors.toList());
	}
	
	/**
	 * Devuelve la m�xima cantidad de validaciones coincidentes de una muestra
	 * @return numero de coincidencias en la validacion
	 */
	public int verificacionesValidas(){

		Map<TipoVinchuca, Long> frecuencias = verificaciones.stream()
															.map(val->val.getTipoVinchuca())
															.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		frecuencias = frecuencias.entrySet()
	                			 .stream()
	                			 .sorted((Map.Entry.<TipoVinchuca, Long>comparingByValue().reversed()))
	                			 .limit(1)  //solo el primer resultado
	                		     .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		return Integer.valueOf(frecuencias.values().stream().findFirst().get().intValue());
	}

	
	public boolean esMenorAXDias(Integer dias){
		
		Period period =Period.between(this.getFechaEnvio(), LocalDate.now());
		return period.getDays()<dias;
	}
	
	
	
	
	
	public void verificar(VerificacionMuestra verificacion) {
		this.verificaciones.add(verificacion);
		//notify de verificacion
		
	}	
	
	
}










