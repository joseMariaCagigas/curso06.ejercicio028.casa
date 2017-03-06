package es.cic.curso.curso06.ejercicio028.backend.repository;

import java.util.List;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Programa;

public interface ProgramaRepository extends IRepository<Long, Programa> {


	
	List<Programa> listyByGenreCategoria(Long idCategoria);

	List<Programa> listyByGenreGenero(Long idGenero);
}
