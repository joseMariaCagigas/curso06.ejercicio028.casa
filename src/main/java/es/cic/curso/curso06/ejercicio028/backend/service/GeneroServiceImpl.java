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

import es.cic.curso.curso06.ejercicio028.backend.dominio.Genero;
import es.cic.curso.curso06.ejercicio028.backend.repository.GeneroRepository;

@Service
@Transactional
public class GeneroServiceImpl implements GeneroService {

	@Autowired
	private GeneroRepository generoRepository;

        /* (non-Javadoc)
		 * @see es.cic.curso.curso06.ejercicio028.backend.service.GeneroService#aniadirGenero(java.lang.String, java.lang.String)
		 */
        @Override
		public Long aniadirGenero(String nombre, String descripcion) {
            
		Genero generoNuevo = new Genero();

                
		generoNuevo.setNombre(nombre);
		generoNuevo.setDescripcion(descripcion);

		
		Genero nuevo = aniadirGenero( generoNuevo );
		
		return nuevo.getId();
	}

    private Genero aniadirGenero(Genero generoNuevo) {
			return generoRepository.add(generoNuevo);
		}

        
    /* (non-Javadoc)
	 * @see es.cic.curso.curso06.ejercicio028.backend.service.GeneroService#actualizarGenero(es.cic.curso.curso06.ejercicio028.backend.dominio.Genero)
	 */
    @Override
	public Genero actualizarGenero(Genero modificada) {
        return generoRepository.update( modificada );
    }


    /* (non-Javadoc)
	 * @see es.cic.curso.curso06.ejercicio028.backend.service.GeneroService#borrarGenero(java.lang.Long)
	 */
    @Override
	public void borrarGenero(Long id) {
        Genero objetoEliminable = obtenerGenero(id);
        generoRepository.delete( objetoEliminable );
    }

    /* (non-Javadoc)
	 * @see es.cic.curso.curso06.ejercicio028.backend.service.GeneroService#obtenerGenero(java.lang.Long)
	 */
    @Override
	public Genero obtenerGenero(Long id) {
        return generoRepository.read(id);
    }

    /* (non-Javadoc)
	 * @see es.cic.curso.curso06.ejercicio028.backend.service.GeneroService#obtenerGenero()
	 */
    @Override
	public List<Genero> obtenerGenero() {
        return generoRepository.list();
    }

}