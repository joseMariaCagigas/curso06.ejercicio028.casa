package es.cic.curso.curso06.ejercicio028.frontend.principal;

import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Programa;
import es.cic.curso.curso06.ejercicio028.backend.service.ServicioGestorPrograma;
import es.cic.curso.grupo1.ejercicio027.dominio.Tarea;

public class VistaProgramas extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7445902879676064023L;

	private TextField buscador;
	private TextField nombre, duracion, anio;
	private Label label;
	private Grid gridProgramas;
	private Button crear, borrar, actualizar, aceptar, cancelar;
	private ComboBox genero, tipo;
	private Programa programa;
	private ServicioGestorPrograma servicioGestorPrograma;
	private List<Programa> listaProgramas;
	
	
	@SuppressWarnings("serial")
	public VistaProgramas(){
		
		servicioGestorPrograma = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorPrograma.class);
		programa = new Programa();
		
		//Layout Pantalla
		HorizontalLayout label_buscador = new HorizontalLayout();
		label = new Label("Lista de Programas");
		buscador = new TextField();
		buscador.setInputPrompt("Buscador");
		buscador.addTextChangeListener(e->
		gridProgramas.setContainerDataSource(new BeanItemContainer<>(Programa.class,  
				servicioGestorPrograma.findAll(e.getText())))
 		);
 		
		Button clearFilterBtn = new Button("Reiniciar");
 		clearFilterBtn.addClickListener(e->{
 			buscador.clear();
 			listaProgramas = servicioGestorPrograma.getProgramas();
 			gridProgramas.setContainerDataSource(new BeanItemContainer<>(Programa.class, listaProgramas));			
 		});
		
		label_buscador.addComponents(label, buscador);
		HorizontalLayout datos = new HorizontalLayout();
		VerticalLayout grid = new VerticalLayout();
		VerticalLayout menu = new VerticalLayout();
		VerticalLayout change = new VerticalLayout();
		HorizontalLayout ok = new HorizontalLayout();
		HorizontalLayout crud = new HorizontalLayout();
		

		final HorizontalLayout base = new HorizontalLayout();
		base.setMargin(true);
		base.setSizeFull();

	}
	
}
