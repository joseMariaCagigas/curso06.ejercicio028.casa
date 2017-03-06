package es.cic.curso.curso06.ejercicio028.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Genero;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso06/ejercicio028/applicationContext.xml" })

public class GeneroRepositoryImplTest extends AbstractRepositoryImplTest<Long, Genero>{


	 @Autowired
	    private GeneroRepository sut;

	    @Before
	    @Override
	    public void setUp() throws Exception {
	        super.setUp();
	    }

	    @Override
	    public Genero getInstanceDeTParaNuevo() {
	    	
	    	Genero op = new Genero();
	        
	        op.setDescripcion("categoria");
	      
	        
	        return op;
	    }

	    @Override
	    public Genero getInstanceDeTParaLectura() {
	    	
	    	Genero op = new Genero();
	        
	        op.setDescripcion("categoria");
	      

	        return op;
	    }

	    @Override
	    public Long getClavePrimariaNoExistente() {
	        return Long.MAX_VALUE;
	    }

	    @Override
	    public Genero getInstanceDeTParaModificar(Long clave) {
	    	Genero op = getInstanceDeTParaLectura();
	        op.setId(clave);
	        op.setDescripcion("operacion");
	       
	        return op;
	    }

	    @Override
	    public IRepository<Long, Genero> getRepository() {
	        return sut;
	    }

	    @Override
	    public boolean sonDatosIguales(Genero t1, Genero t2) {
	        if (t1 == null || t2 == null) {
	            throw new UnsupportedOperationException("No puedo comparar nulos");
	        }
	        
			if (!t1.getDescripcion().equals(t2.getDescripcion())) {
				return false;
			}
			
		 
	        
	        return true;
	    }
	}