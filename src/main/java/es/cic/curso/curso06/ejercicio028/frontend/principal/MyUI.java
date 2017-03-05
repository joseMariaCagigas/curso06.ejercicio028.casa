package es.cic.curso.curso06.ejercicio028.frontend.principal;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

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
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
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

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}