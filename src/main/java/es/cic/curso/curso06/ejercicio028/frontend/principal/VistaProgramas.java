package es.cic.curso.curso06.ejercicio028.frontend.principal;

import org.springframework.web.context.ContextLoader;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Programa;
import es.cic.curso.curso06.ejercicio028.backend.service.ServicioGestorPrograma;

public class VistaProgramas extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7445902879676064023L;

	private TextField buscador, nombre, duracion, anio;
	private Label label;
	private Grid gridProgramas;
	private Button crear, borrar, actualizar, aceptar, cancelar;
	private ComboBox genero, tipo;
	private Programa programa;
	private ServicioGestorPrograma servicioGestorPrograma;
	
	@SuppressWarnings("serial")
	public VistaProgramas(){
		
		servicioGestorPrograma = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorPrograma.class);
		programa = new Programa();
		
		//Layout Pantalla
		HorizontalLayout buscador = new HorizontalLayout();
		HorizontalLayout grid = new HorizontalLayout();
		HorizontalLayout crud = new HorizontalLayout();
		VerticalLayout menu = new VerticalLayout();
		final HorizontalLayout base = new HorizontalLayout();

	}
	
}
