package es.cic.curso.curso06.ejercicio028.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Canal;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Categoria;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Genero;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Programa;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Programacion;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Usuario;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso06/ejercicio028/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class ServicioGestorProgramaTest {
	
    @PersistenceContext
	protected EntityManager entityManager;
	
	@Autowired
	private ServicioGestorPrograma serviciosGestorPrograma;
	
	private Categoria categoria1, categoria2, categoria3;

	private Genero genero1, genero2, genero3;

	private Programa programa1, programa2, programa3;

	private Canal canal1, canal2, canal3;

	private Usuario usuario1, usuario2, usuario3;
	
	private Programacion programacion1, programacion2, programacion3;

	@Before
	public void setUp() throws Exception {
		inicializaBaseDeDatos();
	}

	private void inicializaBaseDeDatos() {
		
   	 
		programa1 = new Programa("administrador", 120, 2016, categoria1, genero1);
		programa2 = new Programa("invitado", 68, 2016, categoria1, genero1);
		programa3 = new Programa("invitado", 99, 1999, categoria1, genero1);
		
		entityManager.persist(programa1);
		entityManager.persist(programa2);
		entityManager.persist(programa3);
		
		
		categoria1 = new Categoria("pelicula", "dramón de almodóvar");
		categoria2 = new Categoria("documental", "león con tripada después de comerse gacela");
		categoria3 = new Categoria("serie", "serie de 13 capítulos");

		entityManager.persist(categoria1);
		entityManager.persist(categoria2);
		entityManager.persist(categoria3);
		
		genero1 = new Genero("Acción","prueba del 1");
		genero2 = new Genero("Deportes","prueba del 2");
		genero3 = new Genero("Drama","prueba del 3");

		entityManager.persist(genero1);
		entityManager.persist(genero2);
		entityManager.persist(genero3);
		
		canal1 = new Canal("Despertador", 100, usuario1);
		canal2 = new Canal("Despertador", 80, usuario2);
		canal3 = new Canal("Despertador", 100, usuario3);
		
		entityManager.persist(canal1);
		entityManager.persist(canal2);
		entityManager.persist(canal3);
		
		usuario1 = new Usuario("manuel", "torrija");
		usuario2 = new Usuario("andres", "torrija");
		usuario3 = new Usuario("manuel", "torquemada");
		
		entityManager.persist(usuario1);
		entityManager.persist(usuario2);
		entityManager.persist(usuario3);
		
		programacion1 = new Programacion(canal1, programa1);
		programacion2 = new Programacion(canal3, programa2);
		programacion3 = new Programacion(canal3, programa3);
		
		entityManager.persist(programacion1);
		entityManager.persist(programacion2);
		entityManager.persist(programacion3);
	}

	@Test
	public void testAniadirCategoria() {
		Categoria categoriaCreada = serviciosGestorPrograma.aniadirCategoria(categoria2);
		assertNotNull(categoriaCreada.getId());
	}

	@Test
	public void testListarCategoria() {
		Categoria categoriaCreada = serviciosGestorPrograma.aniadirCategoria(categoria2);
		List<Categoria> listaCategoria = serviciosGestorPrograma.listarCategoria();
		for (Categoria u : listaCategoria) {
			assertNotNull(u.getId());
		}
	}

	@Test
	public void testObtenerCategoria() {
		Categoria categoriaCreada = serviciosGestorPrograma.aniadirCategoria(categoria2);
		assertNotNull(categoriaCreada.getId());
	}

	@Test
	public void testBorrarCategoria() {
		Categoria categoriaABorrar = new Categoria("categoría_1","nueva categoría");
		serviciosGestorPrograma.aniadirCategoria(categoriaABorrar);
		serviciosGestorPrograma.borrarCategoria(categoriaABorrar.getId());
		List<Categoria> listaCategoria = serviciosGestorPrograma.listarCategoria();
		assertEquals(listaCategoria.size(), 3);
	}

	@Test
	public void testModificarCategoria() {
		categoria2.setNombre("Categoría_2");
		serviciosGestorPrograma.modificarCategoria(categoria2);
		assertEquals(categoria2.getNombre(), "Categoría_2");
	}

	@Test
	public void testAniadirGenero() {
		Genero generoCreada = serviciosGestorPrograma.aniadirGenero(genero2);
		assertNotNull(generoCreada.getId());
	}

	@Test
	public void testListarGenero() {
		Genero generoCreada = serviciosGestorPrograma.aniadirGenero(genero2);
		List<Genero> listaGenero = serviciosGestorPrograma.listarGenero();
		for (Genero u : listaGenero) {
			assertNotNull(u.getId());
		}

	}
	
	@Test
	public void testObtenerGenero() {
		Genero generoCreada = serviciosGestorPrograma.aniadirGenero(genero2);
		assertNotNull(generoCreada.getId());
	}

	@Test
	public void testBorrarGenero() {
		Genero generoABorrar = new Genero("genero_1","nueva genero");
		serviciosGestorPrograma.aniadirGenero(generoABorrar);
		serviciosGestorPrograma.borrarGenero(generoABorrar.getId());
		List<Genero> listaGenero = serviciosGestorPrograma.listarGenero();
		assertEquals(listaGenero.size(), 3);
	}

	@Test
	public void testModificarGenero() {
		genero2.setNombre("Porno Vainilla");
		serviciosGestorPrograma.modificarGenero(genero2);
		assertEquals(genero2.getNombre(), "Porno Vainilla");
	}


	@Test
	public void testAniadirPrograma() {
		Programa programaCreada = serviciosGestorPrograma.aniadirPrograma(programa2);
		assertNotNull(programaCreada.getId());
	}

	@Test
	public void testListarPrograma() {
		Programa programaCreada = serviciosGestorPrograma.aniadirPrograma(programa2);
		List<Programa> listaProgramas = serviciosGestorPrograma.listarPrograma();
		for (Programa u : listaProgramas) {
			assertNotNull(u.getId());
		}
	}

	@Test
	public void testObtenerPrograma() {
		Programa programaCreada = serviciosGestorPrograma.aniadirPrograma(programa2);
		assertNotNull(programaCreada.getId());
	}

	@Test
	public void testBorrarPrograma() {
		Programa programaABorrar = new Programa("invitado", 68, 2016, categoria1, genero1);
		serviciosGestorPrograma.aniadirPrograma(programaABorrar);
		serviciosGestorPrograma.borrarPrograma(programaABorrar.getId());
		List<Categoria> listaCategoria = serviciosGestorPrograma.listarCategoria();
		assertEquals(listaCategoria.size(), 3);
	}

	@Test
	public void testModificarPrograma() {
		programa2.setNombre("Los 40 Tv");
		serviciosGestorPrograma.modificarPrograma(programa2);
		assertEquals(programa2.getNombre(), "Los 40 Tv");
	}

	@Test
	public void testAniadirUsuario() {
		Usuario usuarioCreada = serviciosGestorPrograma.aniadirUsuario(usuario2);
		assertNotNull(usuario2.getId());
	}

	@Test
	public void testListarUsuario() {
		Usuario usuarioCreada = serviciosGestorPrograma.aniadirUsuario(usuario2);
		List<Usuario> listaUsuario = serviciosGestorPrograma.listarUsuario();
		for (Usuario u : listaUsuario) {
			assertNotNull(u.getId());
		}

	}
	
	@Test
	public void testObtenerUsuario() {
		Usuario usuarioCreada = serviciosGestorPrograma.aniadirUsuario(usuario2);
		assertNotNull(usuarioCreada.getId());
	}

	@Test
	public void testBorrarUsuario() {
		Usuario usuarioABorrar = new Usuario("categoría_1","nueva categoría");
		serviciosGestorPrograma.aniadirUsuario(usuarioABorrar);
		serviciosGestorPrograma.borrarUsuario(usuarioABorrar.getId());
		List<Categoria> listaUsuario = serviciosGestorPrograma.listarCategoria();
		assertEquals(listaUsuario.size(), 3);
	}

	@Test
	public void testModificarUsuario() {
		usuario2.setNombre("Porno Vainilla");
		serviciosGestorPrograma.modificarUsuario(usuario2);
		assertEquals(usuario2.getNombre(), "Porno Vainilla");
	}
	

	@Test
	public void testAniadirCanal() {
		Canal canalCreada = serviciosGestorPrograma.aniadirCanal(canal2);
		assertNotNull(canalCreada.getId());
	}

	@Test
	public void testListarCanal() {
		Canal canalCreada = serviciosGestorPrograma.aniadirCanal(canal2);
		List<Canal> listaCanales = serviciosGestorPrograma.listarCanal();
		for (Canal u : listaCanales) {
			assertNotNull(u.getId());
		}
	}

	@Test
	public void testObtenerCanal() {
		Canal canalCreada = serviciosGestorPrograma.aniadirCanal(canal2);
		assertNotNull(canalCreada.getId());
	}

	@Test
	public void testBorrarCanal() {

		List<Canal> listaCanal = serviciosGestorPrograma.listarCanal();
		System.out.println(listaCanal.size());
		assertEquals(listaCanal.size(),3);
		
		serviciosGestorPrograma.borrarCanal(canal2.getId());

		List<Canal> listaCanal2 = serviciosGestorPrograma.listarCanal();
		System.out.println(listaCanal2.size());
		assertEquals(listaCanal2.size(),2);

	}

	@Test
	public void testModificarCanal() {
		programa2.setNombre("Los 40 Tv");
		serviciosGestorPrograma.modificarCanal(canal2);
		assertEquals(programa2.getNombre(), "Los 40 Tv");
	}

	@Test
	public void testAniadirProgramacion() {
		Programacion canalCreada = serviciosGestorPrograma.aniadirProgramacion(programacion3);
		assertNotNull(canalCreada.getId());
	}

	@Test
	public void testListarProgramacion() {
		Programacion programacionCreada = serviciosGestorPrograma.aniadirProgramacion(programacion3);
		List<Programacion> listaProgramacion = serviciosGestorPrograma.listarProgramacion();
		for (Programacion p : listaProgramacion) {
			assertNotNull(p.getId());
		}
	}

	@Test
	public void testObtenerProgramacion() {
		Programacion programacionCreada = serviciosGestorPrograma.aniadirProgramacion(programacion3);
		assertNotNull(programacionCreada.getId());
	}

	@Test
	public void testBorrarProgramacion() {
	List<Programacion> listaProgramacion = serviciosGestorPrograma.listarProgramacion();
	assertEquals(3, listaProgramacion.size());

	for (Programacion programacion : listaProgramacion) {
	serviciosGestorPrograma.borrarProgramacion(programacion.getId());
	}

	List<Programacion> listaProgramacion2 = serviciosGestorPrograma.listarProgramacion();
	assertEquals(0, listaProgramacion2.size());
	}

	@Test
	public void testModificarProgramacion() {
		programacion2.setCanal(canal1);
		serviciosGestorPrograma.modificarCanal(canal1);
		assertEquals(programacion2.getCanal(), canal1);
	}
}
