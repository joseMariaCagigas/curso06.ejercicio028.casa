package es.cic.curso.curso06.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Categoria;

public interface CategoriaService {

	Long aniadirCategoria(String nombre, String descripcion);

	Categoria actualizarCategoria(Categoria modificada);

	void borrarCategoria(Long id);

	Categoria obtenerCategoria(Long id);

	List<Categoria> obtenerCategoria();

}