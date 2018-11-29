package organizacion;

import java.util.Observable;
import java.util.Observer;

import muestra.Ubicacion;
import zonaDeCobertura.ZonaCobertura;

public class Organizacion implements Observer{

	private Ubicacion ubicacion;
	private TipoOrganizacion tipo;
	private Integer trabajadores;
	
	public Organizacion(Ubicacion ubicacion, TipoOrganizacion tipo, Integer trabajadores) {
		this.ubicacion = ubicacion;
		this.tipo = tipo;
		this.trabajadores = trabajadores;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		//llamar a strategy de funcionalidad externa
		
	}
	
	public void suscribe(ZonaCobertura zona){
		zona.addObserver(this);
	}
	
	public void desuscribe(ZonaCobertura zona){
		zona.deleteObserver(this);
	}
	
	
}
