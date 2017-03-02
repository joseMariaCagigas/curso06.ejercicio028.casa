package es.cic.curso.curso06.ejercicio028.backend.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso06/ejercicio028/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class ProgramaRepositoryImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAdd() {
		assertTrue(true);
	}

	@Test
	public void testRead() {
		assertTrue(true);
	}

	@Test
	public void testList() {
		assertTrue(true);
	}

	@Test
	public void testDeleteK() {
		assertTrue(true);
	}

	@Test
	public void testDeleteT() {
		assertTrue(true);
	}

	@Test
	public void testUpdate() {
		assertTrue(true);
	}

}
