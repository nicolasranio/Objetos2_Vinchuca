package muestra;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsensoDeVotos {

	public TipoVinchuca getTipoVinchuca(List<VerificacionMuestra> verificaciones) {
		if(this.verificacionesPorExpertos(verificaciones).isEmpty()) {
			return this.votacionTipoVinchuca(this.tiposVinchucaTotales(verificaciones));
		}
		else {
			return this.votacionTipoVinchuca(this.tiposVinchucaPorExpertos(verificaciones));
		}
	}
	
	public List<VerificacionMuestra> verificacionesPorExpertos(List<VerificacionMuestra> verificaciones) {
		return verificaciones.stream().filter(ver -> ver.fueRealizadaPorExperto()).collect(Collectors.toList());
	}
	
	public List<TipoVinchuca> tiposVinchucaPorExpertos(List<VerificacionMuestra> verificaciones){
		return this.verificacionesPorExpertos(verificaciones).stream().map(ver -> ver.getTipoVinchuca()).collect(Collectors.toList());
	}
	
	
	public List<TipoVinchuca> tiposVinchucaTotales(List<VerificacionMuestra> verificaciones){
		return verificaciones.stream().map(ver -> ver.getTipoVinchuca()).collect(Collectors.toList());
	}
	
	public Set<TipoVinchuca> tiposDeVinchucaMasVotados(List<TipoVinchuca> tiposVinchuca){
		return tiposVinchuca.stream()
				.filter(vinchuca -> this.ocurrencias(this.tipoVinchucaMayorOcurrencia(tiposVinchuca), tiposVinchuca) 
																					== this.ocurrencias(vinchuca, tiposVinchuca))
				.collect(Collectors.toSet());
	}
	
	public TipoVinchuca votacionTipoVinchuca(List<TipoVinchuca> tiposVinchuca) {
		
		if(this.tiposDeVinchucaMasVotados(tiposVinchuca).size() == 1) {
			return this.tipoVinchucaMayorOcurrencia(tiposVinchuca);
		}
		else {
			return TipoVinchuca.Indefinido;
		}
	}
	
	public TipoVinchuca tipoVinchucaMayorOcurrencia(List<TipoVinchuca> tiposVinchuca){
	    TipoVinchuca conMayorOcurrencia = tiposVinchuca.get(0);
	    for(int i=1; i<tiposVinchuca.size(); i++){
	        if(ocurrencias(conMayorOcurrencia,tiposVinchuca)<ocurrencias(tiposVinchuca.get(i),tiposVinchuca))
	        	conMayorOcurrencia = tiposVinchuca.get(i);
	    }
	    return conMayorOcurrencia;
	}
	 
	public int ocurrencias(TipoVinchuca vinchuca, List<TipoVinchuca> tiposVinchuca){
	    int cuantos = 0;//contador, neutro del +
	    for(int i=0; i<tiposVinchuca.size(); i++){
	         if(vinchuca == tiposVinchuca.get(i))   //si vinchuca es igual al elemento i
	            cuantos++;    //el contador incrementa en 1.
	    }
	    return cuantos;//devuelve al contador.
	}
}
