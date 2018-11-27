package muestra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import participante.Participante;

public class Muestra {

	private TipoVinchuca tipoVinchuca;
	private String fotoVinchuca;  //url de la imagen
	private Ubicacion ubicacion;
	private String aliasRecolector;
	private INivelVerificacion nivelVerificacion;  //Patron state
	//private Map<Participante, TipoVinchuca> verificaciones;  //map con veredictos de validacion
	private List<VerificacionMuestra> verificaciones;
	private LocalDate fechaEnvio;
	


	public Muestra(TipoVinchuca tipoVinchuca, String fotoVinchuca, Ubicacion ubicacion, String alias){
		this.tipoVinchuca=tipoVinchuca;
		this.fotoVinchuca= fotoVinchuca;
		this.ubicacion= ubicacion;
		this.aliasRecolector=alias;
		this.nivelVerificacion= new NivelVerificacionBajo(this); //ver esto con prof.
		//this.verificaciones = new HashMap<Participante, TipoVinchuca>();
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
	
	public String getAliasRecolector() {
		return this.aliasRecolector;
	}

	public LocalDate getFechaEnvio() {
		return fechaEnvio;
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
	 * Devuelve la máxima cantidad de validaciones coincidentes de una muestra
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

	
	public void verificar(VerificacionMuestra verificacion) {
		this.verificaciones.add(verificacion);
	}	
	
}










