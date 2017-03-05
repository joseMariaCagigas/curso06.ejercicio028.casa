package es.cic.curso.curso06.ejercicio028.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso06/ejercicio028/applicationContext.xml" })

public class UsuarioRepositoryImplTest extends AbstractRepositoryImplTest<Long, Usuario>{


	 @Autowired
	    private UsuarioRepository sut;

	    @Before
	    @Override
	    public void setUp() throws Exception {
	        super.setUp();
	    }

	    @Override
	    public Usuario getInstanceDeTParaNuevo() {
	    	
	    	Usuario op = new Usuario();
	        
	        op.setApellidos("categoria");
	      
	        
	        return op;
	    }

	    @Override
	    public Usuario getInstanceDeTParaLectura() {
	    	
	    	Usuario op = new Usuario();
	        
	        op.setApellidos("categoria");
	      

	        return op;
	    }

	    @Override
	    public Long getClavePrimariaNoExistente() {
	        return Long.MAX_VALUE;
	    }

	    @Override
	    public Usuario getInstanceDeTParaModificar(Long clave) {
	    	Usuario op = getInstanceDeTParaLectura();
	        op.setId(clave);
	        op.setApellidos("operacion");
	       
	        return op;
	    }

	    @Override
	    public IRepository<Long, Usuario> getRepository() {
	        return sut;
	    }

	    @Override
	    public boolean sonDatosIguales(Usuario t1, Usuario t2) {
	        if (t1 == null || t2 == null) {
	            throw new UnsupportedOperationException("No puedo comparar nulos");
	        }
	        
			if (!t1.getApellidos().equals(t2.getApellidos())) {
				return false;
			}
			
		 
	        
	        return true;
	    }
	}