package src;

import java.util.List;
import java.util.stream.Collectors;

public class Ubicacion {

	private Integer latitud;
	private Integer longitud;
	
	public Ubicacion(Integer latitud, Integer longitud){
		this.latitud=latitud;
		this.longitud=longitud;
	}
	
	
	
	public Integer getLatitud() {
		return latitud;
	}



	public void setLatitud(Integer latitud) {
		this.latitud = latitud;
	}



	public Integer getLongitud() {
		return longitud;
	}



	public void setLongitud(Integer longitud) {
		this.longitud = longitud;
	}



	/*
	 * Calcula distancia entre el punto en cuestion y un punto B
	 * Parametro: una PosicionGeografica
	 * Return: Distancia entre los puntos (Double)
	 * */
	public Double calcularDistancia(Ubicacion ubicacionB){
        double radioTierra = 6371;//en kilómetros  
        double dLat = Math.toRadians(ubicacionB.getLatitud() - this.latitud);  
        double dLng = Math.toRadians(ubicacionB.getLongitud() - this.longitud);  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(this.latitud)) * Math.cos(Math.toRadians(ubicacionB.getLatitud()));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  

        return distancia;  
	}

	//lo hacemos en kilometros
	public List<Ubicacion> ubicacionesCercanas(List<Ubicacion> ubicaciones, Double distancia){
		
		return ubicaciones.stream()
		.filter(ubicacion -> this.calcularDistancia(ubicacion) < distancia)
		.collect(Collectors.toList());
	} 
}
