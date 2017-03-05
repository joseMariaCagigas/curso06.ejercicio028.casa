package es.cic.curso.curso06.ejercicio028.backend.dto;

import java.util.List;

public interface Traductor<A, B> {

	B traduceADTO(A entidad);

	A traduceAEntidad(B dto);
	
	List<B> traduceAListaDTOs(List<A> entidades);
	
	List<A> traduceAListaEntidades(List<B> dtos);
	
}
