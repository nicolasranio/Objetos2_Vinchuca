package muestra;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.function.Function;
import java.util.stream.Collectors;

import observer.EMensajesObservables;
import observer.FactoryMensajes;
import observer.MensajeObserver;
import participante.Participante;

public class Muestra extends Observable{

	//observable por Gestor de notificaciones
	
	private String fotoVinchuca;  //url de la imagen
	private Ubicacion ubicacion;
	private Participante participanteRecolector;
	private INivelVerificacion nivelVerificacion;  //Patron state
	private List<VerificacionMuestra> verificaciones;
	private LocalDate fechaEnvio;
	private ConsensoDeVotos censista;

	

	/**
	 * Construye una muestra con sus elementos correspondientes.
	 * 
	 * @param tipoVinchuca
	 * Es el tipo de vinchuca detectado en la muestra.
	 * @param fotoVinchuca
	 * Es la foto del tipo de vinchuca detectado en la muestra. 
	 * @param ubicacion
	 * Es la ubicacion donde se tomo la muestra.
	 * @param alias
	 * Es es alias de quien recolecto la muestra.
	 * 
	 */
	public Muestra(TipoVinchuca tipoVinchuca, String fotoVinchuca, Ubicacion ubicacion,Participante recolector){
		super();
		this.fotoVinchuca= fotoVinchuca;
		this.ubicacion= ubicacion;
		this.participanteRecolector=recolector;
		this.nivelVerificacion= new NivelVerificacionBajo(this); 
		this.verificaciones= new ArrayList<VerificacionMuestra>();
		this.fechaEnvio=LocalDate.now(); //fecha actual
		this.censista = ConsensoDeVotos.getConsensoDeVotos();
		this.verificar(new VerificacionMuestra(this,recolector,tipoVinchuca));
	}
	
	
	/**
	 * Retorna la distancia en km de una muestra a otra.
	 * 
	 * @param muestraB La muestra a la que se debe calcular la distancia.
	 * 
	 * @return La distancia en km de una muestra a otra.
	 */
	public Double distanciaAMuestra(Muestra muestraB){
		return this.ubicacion.calcularDistancia(muestraB.getUbicacion());
	}

	/**
	 * Retorna la lista de verificaciones que recibio la muestra.
	 * 
	 * @return La lista de todas las verificaciones de la muestra.
	 */
	public List<VerificacionMuestra> getVerificaciones() {
		return verificaciones;
	}
	
	/**
	 * Retorna la ubicacion donde fue recolectada la muestra.
	 * 
	 * @return La ubicacion de la muestra.
	 */
	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}
	
	
	
	/**
	 * Retorna el tipo de vinchuca detectado en la muestra.
	 * 
	 * @return El tipo de vinchuca detectado en la muestra.
	 */
	public TipoVinchuca getTipoVinchuca() {
		return this.censista.getTipoVinchuca(this);

	}
	

	/**
	 * Retorna la fecha de creacion de la muestra.
	 * 
	 * @return La fecha de creacion de la muestra.
	 */
	public LocalDate getFechaEnvio() {
		return this.fechaEnvio;
	}
	
	/**
	 * Retorna el nivel de verificacion que tiene dicha muestra.
	 * 
	 * @return El nivel de verificacion que tiene la muestra.
	 */
	public INivelVerificacion getNivelVerificacion() {
		return this.nivelVerificacion;
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

	/**
	 * Actualiza el nivel de verificacion de una muestra. 
	 * 
	 * @param nivelVerificacion
	 * El nuevo nivel de verificacion.
	 * 
	 */
	public void setNivelVerificacion(INivelVerificacion nivelVerificacion) {
		this.nivelVerificacion=nivelVerificacion;
		this.notificarCambio(FactoryMensajes.crearMensaje(EMensajesObservables.Modificacion, this));
	}

	/**
	 * Retorna la lista de participante que verificaron la muestra.
	 * 
	 * @return La lista de participantes que hayan verificado la muestra.
	 * 
	 */
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

	
	/**
	 * Retorna verdadero solamente cuando la fecha de creacion de la muestra 
	 * es menor al periodo de dias dados.
	 * 
	 * @param dias
	 * El numero de dias a comparar con fecha.
	 * 
	 * @return Verdadero si la fecha de creacion de la muestra es menor a la 
	 * cantidad de dias dados.
	 * 
	 */
	public boolean esMenorAXDias(Integer dias){
		
		Period period =Period.between(this.getFechaEnvio(), LocalDate.now());
		return period.getDays()<dias;
	}
	
	/**
	 * Agrega la verificacion dada a la lista de verificaciones de la muestra
	 * y notifica a los interesados(en caso que existieran), el cambio de sus 
	 * verificaciones.
	 * 
	 * @param verificacion
	 * La verificacion a agregar a la lista de verificaciones de la muestra.
	 */
	public void verificar(VerificacionMuestra verificacion) {
		this.verificaciones.add(verificacion);
		this.notificarCambio(FactoryMensajes.crearMensaje(EMensajesObservables.Modificacion, this));
		
	}

	/**
	 * Informa a sus observadores notificando que dicha muestra se cargo al sistema.
	 */
	public void informarCarga() {
		this.notificarCambio(FactoryMensajes.crearMensaje(EMensajesObservables.Alta, this));
	}	
	
	/**
	 * En caso de que existiesen interesados en el cambio de muestra, se les notifica
	 * a los mismos sobre el cambio. 
	 * 
	 * @param mensajeObserver
	 * El mensaje a enviar a sus interesados.
	 */
	private void notificarCambio(MensajeObserver mensajeObserver) {
		this.setChanged();
		this.notifyObservers(mensajeObserver);
	}

}










