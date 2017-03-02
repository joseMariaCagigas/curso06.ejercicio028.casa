package es.cic.curso.curso06.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Genero;

public interface GeneroService {

	Long aniadirGenero(String nombre, String descripcion);

	Genero actualizarGenero(Genero modificada);

	void borrarGenero(Long id);

	Genero obtenerGenero(Long id);

	List<Genero> obtenerGenero();

}