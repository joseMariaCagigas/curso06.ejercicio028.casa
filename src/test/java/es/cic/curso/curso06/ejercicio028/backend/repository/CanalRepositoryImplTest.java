package es.cic.curso.curso06.ejercicio028.backend.repository;

import static org.junit.Assert.*;

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

import es.cic.curso.curso06.ejercicio028.backend.dominio.Canal;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso06/ejercicio028/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class CanalRepositoryImplTest {

	public static final int NUMERO_ELEMENTOS = 100;

	@Autowired
	private CanalRepository sut;

	@PersistenceContext
	protected EntityManager em;

	private Usuario generaElementoPrueba() {
		Usuario elemento = new Usuario();
		elemento.setNombre("borriquito");
		elemento.setApellidos("como tu");
		em.persist(elemento);
		em.flush();
		return elemento;
	}

	@Test
	public void testCreate() {
		Canal elemento  = new Canal();
		elemento.setNombre("medicamento");
		System.out.println(elemento);
		sut.add(elemento);
		assertNotNull(elemento.getId());
	}

//	@Test
//	public void testRead() {
//		Canal elemento1 = generaElementoPrueba();
//		Canal elemento2 = sut.read(elemento1.getId());
//
//		assertTrue(elemento1.getId().equals(elemento2.getId()));
//
//		try {
//			@SuppressWarnings("unused")
//			TipoMedicamento elemento3 = sut.read(Long.MIN_VALUE);
//			fail("No deberían existir elementos con el ID pasado");
//		} catch (PersistenceException pe) {
//
//		}
//	}
//
//	@Test
//	public void testUpdate() {
//		TipoMedicamento original = generaElementoPrueba();
//		TipoMedicamento clon = original.clone();
//
//		original.setNombre("Modificado");
//		sut.update(original);
//
//		TipoMedicamento modificado = sut.read(original.getId());
//		assertTrue(original.getNombre().equals(modificado.getNombre()));
//		assertFalse(clon.getNombre().equals(modificado.getNombre()));
//	}
//
//	@Test
//	public void testDelete() {
//		TipoMedicamento elemento = generaElementoPrueba();
//		sut.delete(elemento.getId());
//
//		TipoMedicamento resultado = sut.read(elemento.getId());
//		assertNull(resultado);
//	}

	@Test
	public void testList() {
		for (int i = 0; i < NUMERO_ELEMENTOS; i++) {
			generaElementoPrueba();
		}

		List<Canal> lista = sut.list();
		assertEquals(NUMERO_ELEMENTOS, lista.size());
	}

}
