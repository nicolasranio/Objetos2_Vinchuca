package organizacion;
import muestra.*;

import java.util.Observable;
import java.util.Observer;

import Observer.EMensajesObservables;
import Observer.MensajeObserver;
import muestra.Ubicacion;
import zonaDeCobertura.ZonaCobertura;

public class Organizacion implements Observer{

	private Ubicacion ubicacion;
	private TipoOrganizacion tipo;
	private Integer trabajadores;
	private FuncionalidadExterna funcionalidadConfigurableCarga;
	private FuncionalidadExterna funcionalidadConfigurableValidacion;
	
	public Organizacion(Ubicacion ubicacion, TipoOrganizacion tipo, Integer trabajadores, FuncionalidadExterna fc, FuncionalidadExterna fv) {
		this.ubicacion = ubicacion;
		this.tipo = tipo;
		this.trabajadores = trabajadores;
		this.funcionalidadConfigurableCarga = fc;
		this.funcionalidadConfigurableValidacion = fv;
	}

	
	/**
	 * arg0 es la zona de cobertura
	 */
	@Override
	public void update(Observable zona, Object msj) {
		// TODO Auto-generated method stub
		MensajeObserver mensaje = (MensajeObserver) msj;
		if (mensaje.getMensaje().equals(EMensajesObservables.Alta)){
			this.ejecucionFuncionalidadTrasCarga((ZonaCobertura) zona, mensaje.getMuestra());
		}
		else if(mensaje.getMensaje().equals(EMensajesObservables.Modificacion)){
			this.ejecucionFuncionalidadTrasValidacion((ZonaCobertura) zona, mensaje.getMuestra());
		}
	}
	
	public void suscribirAZona(Observable zona){
		zona.addObserver(this);
	}
	

	public void desuscribirDeZona(Observable zona){
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
	
	
	public void ejecucionFuncionalidadTrasCarga(ZonaCobertura zona, Muestra muestra) {
		this.ejecucionFuncionalidad(this.funcionalidadConfigurableCarga, this, zona, muestra);
	}
	
	public void ejecucionFuncionalidadTrasValidacion(ZonaCobertura zona, Muestra muestra) {
		this.ejecucionFuncionalidad(this.funcionalidadConfigurableValidacion, this, zona, muestra);
	}
	
	private void ejecucionFuncionalidad(FuncionalidadExterna func, Organizacion org, ZonaCobertura zona, Muestra muestra) {
		func.nuevoEvento(this, zona, muestra);
	}
		
}
