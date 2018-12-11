package organizacion;
import muestra.*;

import java.util.Observable;
import java.util.Observer;

import muestra.Ubicacion;
import observer.EMensajesObservables;
import observer.MensajeObserver;
import zonaDeCobertura.ZonaCobertura;

public class Organizacion implements Observer{

	private Ubicacion ubicacion;
	private TipoOrganizacion tipo;
	private Integer trabajadores;
	private FuncionalidadExterna funcionalidadConfigurableCarga;
	private FuncionalidadExterna funcionalidadConfigurableValidacion;
	
	
	/**
	 * Construye una Organización a partir de los siguientes parametros.
	 * @param ubicacion, donde la organizazión realiza su ejercicio
	 * @param tipo, la misma puede dedicarse a distintas areas, como salud, educativa, entre otras.
	 * @param trabajadores, la cantidad de empleados de la misma
	 * @param fc, es una funcionalidad Externa configurable que se que actua ante la carga de una Muestra
	 * @param fv, es una funcionalidad Externa configurable que se que actua ante la validacion de una Muestra
	 */
	public Organizacion(Ubicacion ubicacion, TipoOrganizacion tipo, Integer trabajadores, FuncionalidadExterna fc, FuncionalidadExterna fv) {
		this.ubicacion = ubicacion;
		this.tipo = tipo;
		this.trabajadores = trabajadores;
		this.funcionalidadConfigurableCarga = fc;
		this.funcionalidadConfigurableValidacion = fv;
	}

	
	/**
	 * LLas organización son suscriptores de lo que sucede en las Zonas de Cobertura.
	 * Este el e metodo que se ejecuta, cuando uno de las zonas de coberturas notifica un cambio.
	 */
	@Override
	public void update(Observable zona, Object msj) {
		// TODO Auto-generated method stub
		MensajeObserver mensaje = (MensajeObserver) msj;
		mensaje.ejecutarFuncionalidad((ZonaCobertura)zona,this);
		
//		if (mensaje.getMensaje().equals(EMensajesObservables.Alta)){
//			this.ejecucionFuncionalidadTrasCarga((ZonaCobertura) zona, mensaje.getMuestra());
//		}
//		else if(mensaje.getMensaje().equals(EMensajesObservables.Modificacion)){
//			this.ejecucionFuncionalidadTrasValidacion((ZonaCobertura) zona, mensaje.getMuestra());
//		}
	}
	/**
	 * Metodo que permite la suscripción a una 
	 * @param zona
	 */
	public void suscribirAZona(Observable zona){
		zona.addObserver(this);
	}
	
	/**
	 * Metodo que permite desuscribirse a una 
	 * @param zona
	 */
	public void desuscribirDeZona(Observable zona){
		zona.deleteObserver(this);
	}
	
	/**
	 * Configura la funcionalidad Externa que la organización utilizara para la carga de una nueva muestra
	 * en alguna de las zonas a las que esta suscripta
	 * @param funcionalidad
	 */
	public void setFuncionalidadCarga(FuncionalidadExterna funcionalidad){
		this.funcionalidadConfigurableCarga = funcionalidad;
		
	}
	
	/**
	 * Permite la configuración de la funcionalidad Externa que la organización utilizara ante la modificación de 
	 * una muestra en alguna de las zonas a las que esta suscripta
	 * @param funcionalidad
	 */
	public void setFuncionalidadValidacion(FuncionalidadExterna funcionalidad) {
		this.funcionalidadConfigurableValidacion = funcionalidad;
	}
	
	/**
	 * Ejecución de del ejercicio de la funcionalidad externa ante la carga de una @param muestra en alguna de las
	 * @param zona a las que esta suscripta.
	 * 
	 */
	public void ejecucionFuncionalidadTrasCarga(ZonaCobertura zona, Muestra muestra) {
		this.ejecucionFuncionalidad(this.funcionalidadConfigurableCarga, this, zona, muestra);
	}
	/**
	 * Ejecución de la funcionalidad Externa tras la validación de @param muestra en alguna de las  @param zona a
	 * las que la org esta suscripta. 
	 */
	public void ejecucionFuncionalidadTrasValidacion(ZonaCobertura zona, Muestra muestra) {
		this.ejecucionFuncionalidad(this.funcionalidadConfigurableValidacion, this, zona, muestra);
	}
	
	private void ejecucionFuncionalidad(FuncionalidadExterna func, Organizacion org, ZonaCobertura zona, Muestra muestra) {
		func.nuevoEvento(this, zona, muestra);
	}
		
}
