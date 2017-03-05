package es.cic.curso.curso06.ejercicio028.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Categoria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso06/ejercicio028/applicationContext.xml" })

public class CategoriaRepositoryImplTest extends AbstractRepositoryImplTest<Long, Categoria>{


	 @Autowired
	    private CategoriaRepository sut;

	    @Before
	    @Override
	    public void setUp() throws Exception {
	        super.setUp();
	    }

	    @Override
	    public Categoria getInstanceDeTParaNuevo() {
	    	
	    	Categoria op = new Categoria();
	        
	        op.setDescripcion("categoria");
	      
	        
	        return op;
	    }

	    @Override
	    public Categoria getInstanceDeTParaLectura() {
	    	
	    	Categoria op = new Categoria();
	        
	        op.setDescripcion("categoria");
	      

	        return op;
	    }

	    @Override
	    public Long getClavePrimariaNoExistente() {
	        return Long.MAX_VALUE;
	    }

	    @Override
	    public Categoria getInstanceDeTParaModificar(Long clave) {
	    	Categoria op = getInstanceDeTParaLectura();
	        op.setId(clave);
	        op.setDescripcion("operacion");
	       
	        return op;
	    }

	    @Override
	    public IRepository<Long, Categoria> getRepository() {
	        return sut;
	    }

	    @Override
	    public boolean sonDatosIguales(Categoria t1, Categoria t2) {
	        if (t1 == null || t2 == null) {
	            throw new UnsupportedOperationException("No puedo comparar nulos");
	        }
	        
			if (!t1.getDescripcion().equals(t2.getDescripcion())) {
				return false;
			}
			
		 
	        
	        return true;
	    }
	}