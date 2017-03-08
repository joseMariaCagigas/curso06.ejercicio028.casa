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

	public static final int NUMERO = 100;

	@Autowired
	private CanalRepository canalRepository;

	@PersistenceContext
	protected EntityManager em;

	private Canal generaCanal() {
		Usuario usuario = new Usuario();
		usuario.setNombre("MANNUEL");
		usuario.setApellidos("GAFFOTAS");
		em.persist(usuario);
		em.flush();
		
		Canal elemento = new Canal();
		elemento.setNombre("Inicial");
		elemento.setTiempoMaximo(100);
		elemento.setUsuario(usuario);
		
		canalRepository. add(elemento);
		return elemento;

	}

	@Test
	public void testCreate() {
		Canal elemento  = new Canal();
		elemento.setNombre("medicamento");
		canalRepository.add(elemento);
		assertNotNull(elemento.getId());
	}

	@Test
	public void testRead() {
		Canal elemento1 = generaCanal();
		Canal elemento2 = canalRepository.read(elemento1.getId());

		assertTrue(elemento1.getId().equals(elemento2.getId()));

		try {
			@SuppressWarnings("unused")
			Canal elemento3 = canalRepository.read(Long.MIN_VALUE);
			fail("No deberían existir elementos con el ID pasado");
		} catch (PersistenceException pe) {

		}
	}

	@Test
	public void testUpdate() {
		Canal original = generaCanal();
		Canal clon = new Canal();
		clon.setId(original.getId());
		clon.setNombre(original.getNombre());
		clon.setTiempoMaximo(original.getTiempoMaximo());
		clon.setUsuario(original.getUsuario());

		original.setNombre("No Principal");
		original.setTiempoMaximo(65);
		canalRepository.update(original);

		Canal modificado = canalRepository.read(original.getId());
		assertEquals(original.getNombre(), modificado.getNombre());
		assertNotEquals(clon.getTiempoMaximo(), modificado.getTiempoMaximo());
	}

	@Test
	public void testDelete() {
		Canal elemento = generaCanal();
		canalRepository.delete(elemento.getId());

		Canal resultado = canalRepository.read(elemento.getId());
		assertNull(resultado);
	}

	@Test
	public void testList() {
		for (int i = 0; i < NUMERO; i++) {
			generaCanal();
		}

		List<Canal> lista = canalRepository.list();
		assertEquals(NUMERO, lista.size());
	}

}
