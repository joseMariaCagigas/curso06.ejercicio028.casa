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

import es.cic.curso.curso06.ejercicio028.backend.dominio.Categoria;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Genero;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Programa;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso06/ejercicio028/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class ServicioGestorProgramaTest {
	
    @PersistenceContext
	protected EntityManager entityManager;
	
	@Autowired
	private ServicioGestorPrograma serviciosGestorPrograma;
	
	private Categoria categoria1;
	private Categoria categoria2;
	private Categoria categoria3;
	
	private Genero genero1;
	private Genero genero2;
	private Genero genero3;
	
	private Programa programa1;
	private Programa programa2;
	private Programa programa3;
	
	
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
		Categoria categoriaABorrar = new Categoria("categoría_1","nueva categoría");
		serviciosGestorPrograma.aniadirCategoria(categoriaABorrar);
		serviciosGestorPrograma.borrarCategoria(categoriaABorrar.getId());
		List<Categoria> listaCategoria = serviciosGestorPrograma.listarCategoria();
		assertEquals(listaCategoria.size(), 3);
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


}
