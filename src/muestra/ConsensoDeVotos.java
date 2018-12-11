package muestra;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsensoDeVotos {

	
	
	/**
	 * Retorna el tipo de vinchuca mas votada de una muestra determinados por participantes 
	 * basicos solo si ningun participante experto ha realizado una verificacion, en caso 
	 * contrario retorna el tipo de vinchuca mas votada por participantes expertos 
	 * 
	 * @param muestra
	 * La muestra a utilizar 
	 * 
	 * @return El tipo de vinchuca mas votada por participantes
	 */
	
	public TipoVinchuca getTipoVinchuca(Muestra muestra) {
		
		//List<VerificacionMuestra> verificaciones = muestra.getVerificaciones();
		//verificaciones.add(new VerificacionMuestra(muestra,muestra.getRecolector(),muestra.getTipoVinchucaOriginal()));
		if(this.verificacionesPorExpertos(muestra.getVerificaciones()).isEmpty()) {
			return this.votacionTipoVinchuca(this.tiposVinchucaTotales(muestra.getVerificaciones()));
		}
		else {
			return this.votacionTipoVinchuca(this.tiposVinchucaPorExpertos(muestra.getVerificaciones()));
		}
	}
	
	
	/**
	 * Retorna la lista de todos los tipos de vinchuca de las verificaciones de expertos.
	 * 
	 * @param verificaciones
	 * La lista de verificaciones a utilizar
	 * 
	 * @return La lista de todos los tipos de vinchuca de verificaciones realizadas por expertos
	 */
	public List<TipoVinchuca> tiposVinchucaPorExpertos(List<VerificacionMuestra> verificaciones){
		return this.verificacionesPorExpertos(verificaciones).stream().map(ver -> ver.getTipoVinchuca()).collect(Collectors.toList());
	}
	
	/**
	 * Retorna la lista de verificaciones filtradas por verificaciones que hayan sido
	 * realizadas por participantes expertos en el tema
	 * 
	 * @param tiposVinchuca
	 * La lista de vinchucas a filtrar
	 * 
	 * @return La lista de verificaciones realizadas por participantes expertos 
	 */
	public List<VerificacionMuestra> verificacionesPorExpertos(List<VerificacionMuestra> verificaciones) {
		return verificaciones.stream().filter(ver -> ver.fueRealizadaPorExperto()).collect(Collectors.toList());
	}
	
	/**
	 * Retorna la lista de todos los tipos de vinchuca de una determinada lista de verificaciones.
	 * 
	 * @param verificaciones
	 * La lista de verificaciones a utilizar
	 * 
	 * @return La lista de todos los tipos de vinchuca
	 */
	public List<TipoVinchuca> tiposVinchucaTotales(List<VerificacionMuestra> verificaciones){
		return verificaciones.stream().map(ver -> ver.getTipoVinchuca()).collect(Collectors.toList());
	}
	
	
	/**
	 * Retorna el tipo de vinchuca mas votada si y solo si fue la unica mas votada, en
	 * caso contrario retorna tipo de vinchuca indifinido en una lista de vinchucas.
	 * 
	 * @param tiposVinchuca
	 * La lista de vinchucas a buscar los tipos de vinchuca con mayor ocurrencia.
	 * 
	 * @return El tipo de vinchuca con mas ocurrencias si solo existe una,caso contrario
	 * 			como no hay acuerdo y hay mas de una, es indifinido. 
	 */
	public TipoVinchuca votacionTipoVinchuca(List<TipoVinchuca> tiposVinchuca) {
		
		if(this.tiposDeVinchucaMasVotados(tiposVinchuca).size() == 1) {
			return this.tipoVinchucaMayorOcurrencia(tiposVinchuca);
		}
		else {
			return TipoVinchuca.Indefinido;
		}
	}
	
	
	/**
	 * Retorna el conjunto de tipos de vinchuca mas votadas(tipos de vinchuca con mas ocurrencia).
	 * 
	 * @param tiposVinchuca
	 * La lista de vinchucas a buscar los tipos de vinchuca con mayor ocurrencia.
	 * 
	 * @return El conjunto de los tipos de vinchuca con mas ocurrencia. 
	 */
	public Set<TipoVinchuca> tiposDeVinchucaMasVotados(List<TipoVinchuca> tiposVinchuca){
		return tiposVinchuca.stream()
				.filter(vinchuca -> this.ocurrencias(this.tipoVinchucaMayorOcurrencia(tiposVinchuca), tiposVinchuca) 
																					== this.ocurrencias(vinchuca, tiposVinchuca))
				.collect(Collectors.toSet());
	}
	
	
	/**
	 * Retorna el tipo de vinchuca con mas ocurrencia dentro de una lista de vinchuca
	 * 
	 * @param tiposVinchuca
	 * La lista de vinchucas a buscar el tipo de vinchuca con mas ocurrencia
	 * 
	 * @return El tipo de vinchuca con mas ocurrencia en una determinada lista
	 */
	public TipoVinchuca tipoVinchucaMayorOcurrencia(List<TipoVinchuca> tiposVinchuca){
	    TipoVinchuca conMayorOcurrencia = tiposVinchuca.get(0);
	    for(int i=1; i<tiposVinchuca.size(); i++){
	        if(ocurrencias(conMayorOcurrencia,tiposVinchuca)<ocurrencias(tiposVinchuca.get(i),tiposVinchuca))
	        	conMayorOcurrencia = tiposVinchuca.get(i);
	    }
	    return conMayorOcurrencia;
	}
	

	/**
	 * Retorna las ocurrencias de un tipo de vinchuca en una lista de tipos de vinchuca
	 * 
	 * @param vinchuca 
	 * La vinchuca a contar en la lista de vinchucas
	 * 
	 * @param tiposVinchuca
	 * La lista de vinchucas a contar ocurrencias
	 * 
	 * @return Las ocurrencias de vinchuca en determinada lista
	 */
	public int ocurrencias(TipoVinchuca vinchuca, List<TipoVinchuca> tiposVinchuca){
	    int cuantos = 0;//contador, neutro del +
	    for(int i=0; i<tiposVinchuca.size(); i++){
	         if(vinchuca == tiposVinchuca.get(i))   //si vinchuca es igual al elemento i
	            cuantos++;    //el contador incrementa en 1.
	    }
	    return cuantos;//devuelve al contador.
	}
}
