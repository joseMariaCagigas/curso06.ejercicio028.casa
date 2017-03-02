package es.cic.curso.curso06.ejercicio028.backend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Categoria;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Genero;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Programa;
import es.cic.curso.curso06.ejercicio028.backend.repository.CategoriaRepository;
import es.cic.curso.curso06.ejercicio028.backend.repository.GeneroRepository;
import es.cic.curso.curso06.ejercicio028.backend.repository.ProgramaRepository;
import es.cic.curso.grupo1.ejercicio027.dominio.Tarea;
import es.cic.curso.grupo1.ejercicio027.service.TareaServiceImpl;

@Service
@Transactional
public class ServicioGestorProgramaImpl implements ServicioGestorPrograma {
	
	@Autowired
	private ProgramaRepository programaRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private GeneroRepository generoRepository;

	@Override
	public Categoria aniadirCategoria(Categoria categoria) {
		
		return categoriaRepository.add(categoria);
	}

	@Override
	public List<Categoria> listarCategoria() {
		
		return categoriaRepository.list();
	}

	@Override
	public Categoria obtenerCategoria(Long id) {
		
		return categoriaRepository.read(id);
	}

	@Override
	public void borrarCategoria(Long id) {
		 Categoria categoriaABorrar = obtenerCategoria(id);
		 categoriaRepository.delete( categoriaABorrar);
	}

	@Override
	public Categoria modificarCategoria(Categoria categoria) {
		
		return categoriaRepository.update(categoria);
	}

	@Override
	public Genero aniadirGenero(Genero genero) {
		
		return generoRepository.add(genero);
	}

	@Override
	public List<Genero> listarGenero() {
		
		return generoRepository.list();
	}

	@Override
	public Genero obtenerGenero(Long id) {
		
		return generoRepository.read(id);
	}

	@Override
	public void borrarGenero(Long id) {
		Genero generoABorrar = obtenerGenero(id);
		generoRepository.delete( generoABorrar);

	}

	@Override
	public Genero modificarGenero(Genero genero) {
		
		return generoRepository.update(genero);
	}

	@Override
	public Programa aniadirPrograma(Programa programa) {
		
		return programaRepository.add(programa);
	}

	@Override
	public List<Programa> listarPrograma() {
		
		return programaRepository.list();
	}

	@Override
	public Programa obtenerPrograma(Long id) {
		
		return programaRepository.read(id);
	}

	@Override
	public void borrarPrograma(Long id) {
		Programa programaABorrar = obtenerPrograma(id);
		programaRepository.delete( programaABorrar);

	}

	@Override
	public Programa modificarPrograma(Programa programa) {
		
		return programaRepository.update(programa);
	}

	@Override
	public List<Programa> getProgramas() {
		return programaRepository.list();
	}

	@Override
 	public synchronized List<Programa> findAll(String filtro){
 		List<Programa> lista = new ArrayList<>();
 		for(Programa t : getProgramas()){
 			try{
 				boolean pasoFiltro = (filtro==null||filtro.isEmpty())
 						||t.getNombre().toLowerCase().contains(filtro.toLowerCase());
 				if(pasoFiltro){
 					lista.add(t.clone());
 				}
 			}catch(CloneNotSupportedException ex) {
 				Logger.getLogger(ServicioGestorPrograma.class.getName()).log(null, ex);
 			}
 		}
 		Collections.sort(lista, new Comparator<Programa>() {
 
 			@Override
 			public int compare(Programa o1, Programa o2) {
 				return (int) (o2.getId() - o1.getId());
 			}});
 		return lista;
	}	


}
