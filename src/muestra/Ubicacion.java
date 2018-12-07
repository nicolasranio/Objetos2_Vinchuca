package muestra;

import java.util.List;
import java.util.stream.Collectors;

public class Ubicacion {

	private Double latitud;
	private Double longitud;
	
	/**
	 * Construye una ubicacion con sus elementos correspondientes.
	 * 
	 * @param latitud
	 * Es la latitud de dicha ubicacion.
	 *
	 * @param longitud
	 * Es la longitud de dicha ubicacion.
	 * 
	 */
	public Ubicacion(Double latitud, Double longitud){
		this.latitud=latitud;
		this.longitud=longitud;
	}
	
	/**
	 * Retorna la latitud de dicha ubicacion.
	 * 
	 * 
	 * @return La latitud de dicha ubicacion.
	 */
	public Double getLatitud() {
		return latitud;
	}

	/**
	 * Retorna la longitud de dicha ubicacion.
	 * 
	 * 
	 * @return La longitud de dicha ubicacion.
	 */
	public Double getLongitud() {
		return longitud;
	}
	
	/**
	 * Calcula distancia entre este punto y un punto B
	 * @param ubicacionB
	 * @return Distancia entre los puntos (Double)
	 */
	public Double calcularDistancia(Ubicacion ubicacionB){
        double radioTierra = 6371;//en kilómetros  
        double dLat = Math.toRadians(ubicacionB.getLatitud() - this.latitud);  
        double dLng = Math.toRadians(ubicacionB.getLongitud() - this.longitud);  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(this.latitud)) * Math.cos(Math.toRadians(ubicacionB.getLatitud()));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;
        distancia = Math.round(distancia * Math.pow(10,2)) / Math.pow(10,2);

        return distancia;  
	}

	/**
	 * Devuelve las ubicaciones cercanas en {distancia} kilometros a esta ubicacion
	 * @param ubicaciones (lista)
	 * @param distancia
	 * @return Lista de ubicaciones cercanas
	 */
	public List<Ubicacion> ubicacionesCercanas(List<Ubicacion> ubicaciones, Double distancia){
		
		return ubicaciones.stream()
		.filter(ubicacion -> this.calcularDistancia(ubicacion) < distancia)
		.collect(Collectors.toList());
	}
}
