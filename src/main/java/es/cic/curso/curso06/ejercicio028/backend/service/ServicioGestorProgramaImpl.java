package es.cic.curso.curso06.ejercicio028.backend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Canal;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Categoria;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Genero;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Programa;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Programacion;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Usuario;
import es.cic.curso.curso06.ejercicio028.backend.repository.CanalRepository;
import es.cic.curso.curso06.ejercicio028.backend.repository.CategoriaRepository;
import es.cic.curso.curso06.ejercicio028.backend.repository.GeneroRepository;
import es.cic.curso.curso06.ejercicio028.backend.repository.ProgramaRepository;
import es.cic.curso.curso06.ejercicio028.backend.repository.ProgramacionRepository;
import es.cic.curso.curso06.ejercicio028.backend.repository.UsuarioRepository;


@Service
@Transactional
public class ServicioGestorProgramaImpl implements ServicioGestorPrograma {
	
	@Autowired
	private ProgramaRepository programaRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private GeneroRepository generoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CanalRepository canalRepository;
	
	@Autowired
	private ProgramacionRepository programacionRepository;

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
	public Usuario aniadirUsuario(Usuario usuario) {

		return usuarioRepository.add(usuario);
	}

	@Override
	public List<Usuario> listarUsuario() {
		
		return usuarioRepository.list();
	}

	@Override
	public Usuario obtenerUsuario(Long id) {
		return usuarioRepository.read(id);
	}

	@Override
	public void borrarUsuario(Long id) {
		Usuario usuarioABorrar = obtenerUsuario(id);
		usuarioRepository.delete( usuarioABorrar);
		
	}

	@Override
	public Usuario modificarUsuario(Usuario usuario) {
		
		return usuarioRepository.update(usuario);
	}


	@Override
	public Canal aniadirCanal(Canal canal) {

		return canalRepository.add(canal);
	}

	@Override
	public List<Canal> listarCanal() {
		
		return canalRepository.list();
	}

	@Override
	public Canal obtenerCanal(Long id) {
		return canalRepository.read(id);
	}

	@Override
	public void borrarCanal(Long id) {
		Canal canalABorrar = obtenerCanal(id);
		canalRepository.delete( canalABorrar);
		
	}

	@Override
	public Canal modificarCanal(Canal canal) {
		
		return canalRepository.update(canal);
	}

	@Override
	public Programacion aniadirProgramacion(Programacion programacion) {
		
		return programacionRepository.add(programacion);
	}

	@Override
	public List<Programacion> listarProgramacion() {
		
		return programacionRepository.list();
	}

	@Override
	public Programacion obtenerProgramacion(Long id) {
		
		return programacionRepository.read(id);
	}

	@Override
	public void borrarProgramacion(Long id) {
		Programacion programacionABorrar = obtenerProgramacion(id);
		programacionRepository.delete( programacionABorrar);

	}

	@Override
	public Programacion modificarProgramacion(Programacion programacion) {
		
		return programacionRepository.update(programacion);
	}





}
