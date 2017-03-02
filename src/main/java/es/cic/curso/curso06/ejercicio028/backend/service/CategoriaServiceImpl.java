/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.curso.curso06.ejercicio028.backend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Categoria;
import es.cic.curso.curso06.ejercicio028.backend.repository.CategoriaRepository;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;


		/* (non-Javadoc)
		 * @see es.cic.curso.curso06.ejercicio028.backend.service.CategoriaService#aniadirCategoria(java.lang.String, java.lang.String)
		 */
		@Override
		public Long aniadirCategoria(String nombre, String descripcion) {
            
		Categoria categoriaNuevo = new Categoria();

                
		categoriaNuevo.setNombre(nombre);
		categoriaNuevo.setDescripcion(descripcion);

		
		Categoria nuevo = aniadirCategoria( categoriaNuevo );
		
		return nuevo.getId();
	}

    private Categoria aniadirCategoria(Categoria categoriaNuevo) {
			return categoriaRepository.add(categoriaNuevo);
		}


	/* (non-Javadoc)
	 * @see es.cic.curso.curso06.ejercicio028.backend.service.CategoriaService#actualizarCategoria(es.cic.curso.curso06.ejercicio028.backend.dominio.Categoria)
	 */
	@Override
	public Categoria actualizarCategoria(Categoria modificada) {
        return categoriaRepository.update( modificada );
    }

	/* (non-Javadoc)
	 * @see es.cic.curso.curso06.ejercicio028.backend.service.CategoriaService#borrarCategoria(java.lang.Long)
	 */
	@Override
	public void borrarCategoria(Long id) {
        Categoria objetoEliminable = obtenerCategoria(id);
        categoriaRepository.delete( objetoEliminable );
    }

	/* (non-Javadoc)
	 * @see es.cic.curso.curso06.ejercicio028.backend.service.CategoriaService#obtenerCategoria(java.lang.Long)
	 */
	@Override
	public Categoria obtenerCategoria(Long id) {
        return categoriaRepository.read(id);
    }

	/* (non-Javadoc)
	 * @see es.cic.curso.curso06.ejercicio028.backend.service.CategoriaService#obtenerCategoria()
	 */
	@Override
	public List<Categoria> obtenerCategoria() {
        return categoriaRepository.list();
    }

}