package es.cic.curso.curso06.ejercicio028.frontend.principal;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.context.ContextLoader;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Canal;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Categoria;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Genero;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Programa;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Usuario;
import es.cic.curso.curso06.ejercicio028.backend.service.ServicioGestorPrograma;
import es.cic.curso.curso06.ejercicio028.frontend.vistas.VistaCanales;
import es.cic.curso.curso06.ejercicio028.frontend.vistas.VistaProgramacion;
import es.cic.curso.curso06.ejercicio028.frontend.vistas.VistaProgramas;
import es.cic.curso.curso06.ejercicio028.frontend.vistasBasicas.VistaCategorias;
import es.cic.curso.curso06.ejercicio028.frontend.vistasBasicas.VistaGeneros;
import es.cic.curso.curso06.ejercicio028.frontend.vistasBasicas.VistaUsuarios;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5532407736552134817L;
	private TabSheet tab;
	private ServicioGestorPrograma servicioGestorPrograma;
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	servicioGestorPrograma = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorPrograma.class);
    	cargaDatosProgramas();
    	cargaDatosCanales();
    	
    	final VerticalLayout layoutUsuario = new VistaUsuarios();
    	final VerticalLayout layoutCanal = new VistaCanales();
        final VerticalLayout layoutProgramacion = new VistaProgramacion();
        final VerticalLayout layoutProgramas = new VistaProgramas();
        final VerticalLayout layoutCategorias = new VistaCategorias();
        final VerticalLayout layoutGeneros = new VistaGeneros();
        
        tab = new TabSheet();
        
        tab.setHeight(100.0f, Unit.PERCENTAGE);
        tab.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tab.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
 
        tab.addTab(layoutUsuario,"Usuarios");
        tab.addTab(layoutCanal,"Canales");
        tab.addTab(layoutProgramacion,"Programacion");
        tab.addTab(layoutProgramas,"Programas");
        tab.addTab(layoutCategorias,"Categorias");
        tab.addTab(layoutGeneros,"Géneros");
        
        setContent(tab);
    }
    
	private void cargaDatosProgramas() {
		if (servicioGestorPrograma.listarPrograma().isEmpty()) {
			
		}
			Categoria categoria1 = new Categoria("categoria1", "categoria1");
			servicioGestorPrograma.aniadirCategoria(categoria1);
			Categoria categoria2 = new Categoria("categoria2", "categoria2");
			servicioGestorPrograma.aniadirCategoria(categoria2);
			Genero genero1 = new Genero("genero1", "genero1");
			servicioGestorPrograma.aniadirGenero(genero1);
			Genero genero2 = new Genero("genero2", "genero2");
			servicioGestorPrograma.aniadirGenero(genero2);
		
			Programa programa1 = new Programa("Programa_1", 60, 2016, categoria1, genero2);
			servicioGestorPrograma.aniadirPrograma(programa1);
			Programa programa2 = new Programa("Programa_2", 60, 2015, categoria2, genero1);
			servicioGestorPrograma.aniadirPrograma(programa2);
			Programa programa3 = new Programa("Programa_3", 60, 2017, categoria2, genero1);
			servicioGestorPrograma.aniadirPrograma(programa3);

	}
	
	private void cargaDatosCanales() {
		if (servicioGestorPrograma.listarCanal().isEmpty()) {
			
		}
			Usuario usuario1 = new Usuario("Héctor", "Cifuentes Pérez");
			servicioGestorPrograma.aniadirUsuario(usuario1);
			Usuario usuario2 = new Usuario("Manuel", "Hacha Bendita");
			servicioGestorPrograma.aniadirUsuario(usuario2);
			
			Canal canal1 = new Canal("Cana1", 600, usuario1);
			servicioGestorPrograma.aniadirCanal(canal1);
			Canal canal2 = new Canal("Cana2", 500, usuario2);
			servicioGestorPrograma.aniadirCanal(canal2);
			Canal canal3 = new Canal("Cana3", 300, usuario1);
			servicioGestorPrograma.aniadirCanal(canal3);
	}

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}