package app;

import java.util.List;
import java.util.stream.Collectors;

import muestra.Muestra;

public class BusquedaDeMuestras {

	public List<Muestra> buscarMuestras(Filtro filtro,List<Muestra> muestras){
		return muestras.stream().filter(muestra -> filtro.aplicar(muestra)).collect(Collectors.toList());
	}
}
