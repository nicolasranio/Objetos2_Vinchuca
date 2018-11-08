package src;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Muestra {

	private String tipoVinchuca;
	private String fotoVinchuca;  //url de la imagen
	private Ubicacion ubicacion;
	private String aliasRecolector;
	private INivelVerificacion nivelVerificacion;  //Patron state
	private List<Verificacion> verificaciones;
	private ArrayList<Participante> verificadores;
	

	public Muestra(String tipoVinchuca, String fotoVinchuca, Ubicacion ubicacion, String alias){
		this.tipoVinchuca=tipoVinchuca;
		this.fotoVinchuca= fotoVinchuca;
		this.ubicacion= ubicacion;
		this.aliasRecolector=alias;
		this.nivelVerificacion= new NivelVerificacionBajo(this);
		this.verificadores= new ArrayList<Participante>();  
	}
	
	public Double distanciaAMuestra(Muestra muestraB){
		return this.ubicacion.calcularDistancia(muestraB.getUbicacion());
	}

	
	private Ubicacion getUbicacion() {
		return this.ubicacion;
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
		return verificadores;
	}
	
}
