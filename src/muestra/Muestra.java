package muestra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import participante.Participante;

public class Muestra {

	private String tipoVinchuca;
	private String fotoVinchuca;  //url de la imagen
	private Ubicacion ubicacion;
	private String aliasRecolector;
	private INivelVerificacion nivelVerificacion;  //Patron state
	private Map<Participante, TipoVinchuca> verificaciones;  //map con veredictos de validacion

	
	public Muestra(String tipoVinchuca, String fotoVinchuca, Ubicacion ubicacion, String alias){
		this.tipoVinchuca=tipoVinchuca;
		this.fotoVinchuca= fotoVinchuca;
		this.ubicacion= ubicacion;
		this.aliasRecolector=alias;
		this.nivelVerificacion= new NivelVerificacionBajo(this); //ver esto con prof.
		this.verificaciones = new HashMap<Participante, TipoVinchuca>();
	}
	
	public Double distanciaAMuestra(Muestra muestraB){
		return this.ubicacion.calcularDistancia(muestraB.getUbicacion());
	}

	public Map<Participante, TipoVinchuca> getVerificaciones() {
		return verificaciones;
	}
	
	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}
	
	public String getTipoVinchuca() {
		return this.tipoVinchuca;
	}
	
	public String getFotoVinchuca() {
		return this.fotoVinchuca;
	}
	
	public String getAliasRecolector() {
		return this.aliasRecolector;
	}

	
	public List<Muestra> muestrasCercanas(List<Muestra> muestras, Double distancia){
		
	/*	return muestras.stream()
				.map(muestra -> muestra.getUbicacion())
				.filter(ubicacionB -> this.ubicacion.calcularDistancia(ubicacionB)<distancia)
				.collect(Collectors.toList());*/
		
		return muestras.stream()
				.filter(muestra -> this.distanciaAMuestra(muestra)<distancia)
				.collect(Collectors.toList());
	}

	public void setNivelVerificacion(INivelVerificacion nivelVerificacion) {
		this.nivelVerificacion=nivelVerificacion;
	}
	
	public ArrayList<Participante> getVerificadores() {
		verificaciones.keySet();
		Collection<Participante> keys = verificaciones.keySet();		         
		ArrayList <Participante> participantes = new ArrayList<Participante>(keys);
		return participantes;
	}
	
	public int verificacionesValidas(){
		
		//me quedo con las verificaciones que se hicieron sobre la muestra y cuento coincidencias
		Collection<TipoVinchuca> values = verificaciones.values();		         
		ArrayList <TipoVinchuca> veredictos = new ArrayList<TipoVinchuca>(values);  
		
		Map<TipoVinchuca, Long> frecuencias = veredictos.stream()
												 .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		Map<TipoVinchuca, Long> maxFrecuencia= frecuencias.entrySet()
	                										.stream()
	                										.sorted((Map.Entry.<TipoVinchuca, Long>comparingByValue().reversed()))
	                										.limit(1)  //solo el primer resultado
	                										.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		return Integer.valueOf(maxFrecuencia.values().stream().findFirst().get().intValue());
	}

	
	public void verificar(Participante participante, TipoVinchuca validacion) {
		this.verificaciones.put(participante, validacion);
		
	}	
	
}
