package organizacion;
import muestra.*;

import java.util.Observable;
import java.util.Observer;

import muestra.Ubicacion;
import zonaDeCobertura.ZonaCobertura;

public class Organizacion implements Observer{

	private Ubicacion ubicacion;
	private TipoOrganizacion tipo;
	private Integer trabajadores;
	private FuncionalidadExterna funcionalidadConfigurableCarga;
	private FuncionalidadExterna funcionalidadConfigurableValidacion;
	
	public Organizacion(Ubicacion ubicacion, TipoOrganizacion tipo, Integer trabajadores, FuncionalidadExterna f, FuncionalidadExterna f2) {
		this.ubicacion = ubicacion;
		this.tipo = tipo;
		this.trabajadores = trabajadores;
		this.funcionalidadConfigurableCarga = f;
		this.funcionalidadConfigurableCarga = f2;
	}

	
	/**
	 * arg0 es la zona de cobertura
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		//llamar a strategy de funcionalidad externa
		this.ejecutarFuncionalidadExt(this,(ZonaCobertura) arg0, (Muestra) arg1)
		
	}
	
	public void suscribe(ZonaCobertura zona){
		zona.addObserver(this);
	}
	

	public void desuscribe(ZonaCobertura zona){
		zona.deleteObserver(this);
	}
	
	/**
	 * Configura la funcionalidad que la organización utilizara para la carga de una nueva muestra
	 * @param funcionalidad
	 */
	public void setFuncionalidadCarga(FuncionalidadExterna funcionalidad){
		this.funcionalidadConfigurableCarga = funcionalidad;
		
	}
	
	public void setFuncionalidadValidacion(FuncionalidadExterna funcionalidad) {
		this.funcionalidadConfigurableValidacion = funcionalidad;
	}
	
	public void EjecucionFunciolidadTrasCarga(ZonaCobertura z, Muestra m) {
		this.ejecucionFuncionalidadCargaOValidacion(this.funcionalidadConfigurableCarga, this, z, m);
	}
	
	public void EjecucionFuncionalidadTrasValidacion(ZonaCobertura z, Muestra m) {
		this.ejecucionFuncionalidadCargaOValidacion(this.funcionalidadConfigurableValidacion, this, z, m);
	}
	
	private void ejecucionFuncionalidadCargaOValidacion(FuncionalidadExterna f, Organizacion org, ZonaCobertura zona, Muestra muestra) {
		f.nuevoEvento(this, zona, muestra);
	}
		
}
