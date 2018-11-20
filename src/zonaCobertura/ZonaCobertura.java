package zonaCobertura;

import java.util.List;

import app.AplicacionWeb;
import muestra.*;

public class ZonaCobertura {

	private Ubicacion epicentro;
	private Integer radio;
	private String nombre;
	private AplicacionWeb aplicacion;
	
	
	
	public ZonaCobertura(Ubicacion epicentro, Integer radio, String nombre, AplicacionWeb aplicacion) {
		super();
		this.epicentro = epicentro;
		this.radio = radio;
		this.nombre = nombre;
		this.aplicacion = aplicacion;
	}


	public List<Muestra> muestrasEnZona(){
		return null;
	}
	

	public List<ZonaCobertura> zonasSolapadas(){
		return null;
	}
	
	
}
