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
public class ProgramaRepositoryImplTest {

	public static final int NUMERO = 100;

	@Autowired
	private ProgramaRepository programaRepository;

	@PersistenceContext
	protected EntityManager em;

	private Programa generaPrograma() {
		Categoria categoria = new Categoria();
		categoria.setNombre("CATEGORIA");
		categoria.setDescripcion("DESCRIPCION");
		em.persist(categoria);
		
		Genero genero = new Genero();
		genero.setNombre("GENERO");
		genero.setDescripcion("DESCRIPCION");
		em.persist(genero);
		
		em.flush();
		
		Programa elemento = new Programa();
		elemento.setNombre("Inicial");
		elemento.setDuracion(100);
		elemento.setCategoria(categoria);
		elemento.setGenero(genero);
		
		programaRepository.add(elemento);
		return elemento;

	}

	@Test
	public void testCreate() {
		Programa elemento  = new Programa();
		elemento.setNombre("medicamento");
		programaRepository.add(elemento);
		assertNotNull(elemento.getId());
	}

	@Test
	public void testRead() {
		Programa elemento1 = generaPrograma();
		Programa elemento2 = programaRepository.read(elemento1.getId());

		assertTrue(elemento1.getId().equals(elemento2.getId()));

		try {
			@SuppressWarnings("unused")
			Programa elemento3 = programaRepository.read(Long.MIN_VALUE);
			fail("No deberían existir elementos con el ID pasado");
		} catch (PersistenceException pe) {

		}
	}

	@Test
	public void testUpdate() {
		Programa original = generaPrograma();
		Programa clon = new Programa();
		clon.setId(original.getId());
		clon.setNombre(original.getNombre());
		clon.setDuracion(original.getDuracion());
		clon.setCategoria(original.getCategoria());
		clon.setGenero(original.getGenero());

		original.setNombre("No Principal");
		original.setDuracion(65);
		programaRepository.update(original);

		Programa modificado = programaRepository.read(original.getId());
		assertEquals(original.getNombre(), modificado.getNombre());
		assertNotEquals(clon.getDuracion(), modificado.getDuracion());
	}

	@Test
	public void testDelete() {
		Programa elemento = generaPrograma();
		programaRepository.delete(elemento.getId());

		Programa resultado = programaRepository.read(elemento.getId());
		assertNull(resultado);
	}

	@Test
	public void testList() {
		for (int i = 0; i < NUMERO; i++) {
			generaPrograma();
		}

		List<Programa> lista = programaRepository.list();
		assertEquals(NUMERO, lista.size());
	}

}

