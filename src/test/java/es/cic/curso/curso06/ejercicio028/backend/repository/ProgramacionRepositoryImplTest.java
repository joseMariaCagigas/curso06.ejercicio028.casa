package es.cic.curso.curso06.ejercicio028.backend.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.junit.Ignore;
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
public class ProgramacionRepositoryImplTest {

	public static final int NUMERO_ELEMENTOS = 100;

	@Autowired
	private ProgramacionRepository programacionRepository;

	@PersistenceContext
	protected EntityManager em;

	private Usuario generaUsuarioPrueba() {
		Usuario elemento = new Usuario();
		elemento.setNombre("enfermedad");
		elemento.setApellidos("Hacha");

		em.persist(elemento);
		em.flush();
		return elemento;
	}
	
	private Categoria generaCategoriaPrueba() {
		Categoria elemento = new Categoria();
		elemento.setNombre("enfermedad");
		elemento.setDescripcion("Hacha");

		em.persist(elemento);
		em.flush();
		return elemento;
	}
	
	private Genero generaGeneroPrueba() {
		Genero elemento = new Genero();
		elemento.setNombre("enfermedad");
		elemento.setDescripcion("Hacha");

		em.persist(elemento);
		em.flush();
		return elemento;
	}
	private Canal generaCanalPrueba() {
		Canal elemento = new Canal();
		elemento.setNombre("tipo de medicamento");
		elemento.setTiempoMaximo(100);
		elemento.setUsuario(generaUsuarioPrueba());
		em.persist(elemento);
		em.flush();
		return elemento;
	}
	private Programa generaProgramaPrueba() {
		Programa elemento = new Programa();
		elemento.setNombre("tipo de medicamento");
		elemento.setDuracion(100);
		elemento.setCategoria(generaCategoriaPrueba());
		elemento.setGenero(generaGeneroPrueba());
		em.persist(elemento);
		em.flush();
		return elemento;
	}

	private Programacion generaElementoPrueba() {
		Programacion elemento = new Programacion();
		elemento.setCanal(generaCanalPrueba());
		elemento.setPrograma(generaProgramaPrueba());
		em.persist(elemento);
		em.flush();
		return elemento;
	}

	@Test
	public void testAdd() {		
		Programacion elemento = new Programacion();
		elemento.setCanal(generaCanalPrueba());
		elemento.setPrograma(generaProgramaPrueba());
		programacionRepository.add(elemento);
		assertNotNull(elemento.getId());
	}

	@Test
	public void testRead() {
		Programacion elemento1 = generaElementoPrueba();
		Programacion elemento2 = programacionRepository.read(elemento1.getId());

		assertTrue(elemento1.getId().equals(elemento2.getId()));

		try {
			@SuppressWarnings("unused")
			Programacion elemento3 = programacionRepository.read(Long.MIN_VALUE);
			fail("No deberían existir elementos con el ID pasado");
		} catch (PersistenceException pe) {

		}
	}

	@Test
	public void testDelete() {
		
		Programacion elemento = generaElementoPrueba();
		System.out.println(elemento);
		programacionRepository.delete(elemento.getId());

		Programacion resultado = programacionRepository.read(elemento.getId());
		assertNull(resultado);
	}

	@Test
	public void testList() {
		for (int i = 0; i < NUMERO_ELEMENTOS; i++) {
			generaElementoPrueba();
		}

		List<Programacion> lista = programacionRepository.list();
		assertEquals(NUMERO_ELEMENTOS, lista.size());
	}
	
	

}
