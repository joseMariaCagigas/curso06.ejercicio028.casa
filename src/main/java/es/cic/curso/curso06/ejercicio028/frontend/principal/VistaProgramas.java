package es.cic.curso.curso06.ejercicio028.frontend.principal;

import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Programa;
import es.cic.curso.curso06.ejercicio028.backend.service.ServicioGestorPrograma;


public class VistaProgramas extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7445902879676064023L;

	private TextField buscador;
	private TextField nombre, duracion, anio;
	private Label label;
	private Grid gridProgramas;
	private Button crear, borrar, actualizar, aceptar, cancelar;
	private ComboBox genero, categoria;
	private Programa programa;
	private ServicioGestorPrograma servicioGestorPrograma;
	private List<Programa> listaProgramas;
	
	
	@SuppressWarnings("serial")
	public VistaProgramas(){
		
		programa = new Programa();
		//Layout Pantalla
//		
		
		HorizontalLayout layoutUno = label_buscador();
		
		HorizontalLayout layoutDos = layoutDos();
		
		HorizontalLayout layoutTres = layoutTres();

		addComponents(layoutUno, layoutDos, layoutTres);
			}


	private HorizontalLayout layoutTres() {
		HorizontalLayout layoutTres = new HorizontalLayout();
		layoutTres.setMargin(true);
		layoutTres.setSpacing(true);
		crear = new Button("Crear");
		crear.setVisible(true);
		crear.setEnabled(false);
		crear.setIcon(FontAwesome.PLUS);
		borrar = new Button("Borrar");
		borrar.setVisible(true);
		borrar.setEnabled(false);
		borrar.setIcon(FontAwesome.ERASER);
		actualizar = new Button("Actualizar");
		actualizar.setVisible(true);
		actualizar.setEnabled(false);
		actualizar.setIcon(FontAwesome.REFRESH);
		layoutTres.addComponents(crear, borrar, actualizar);
		return layoutTres;
	}


	private HorizontalLayout layoutDos() {
		HorizontalLayout layoutDos = new HorizontalLayout();
		layoutDos.setMargin(true);
		layoutDos.setSpacing(true);
		VerticalLayout grid = new VerticalLayout();
		grid.setSpacing(true);
		gridProgramas = new Grid();
		gridProgramas.setVisible(true);
		gridProgramas.setColumns("Nombre", "Duración", "Año", "Categoría", "Género");
		gridProgramas.setSizeFull();
		gridProgramas.setSelectionMode(SelectionMode.SINGLE);
		grid.addComponent(gridProgramas);
		VerticalLayout menu = new VerticalLayout();
		menu.setMargin(true);
		menu.setSpacing(true);
		nombre = new TextField("Nombre");
		nombre.setInputPrompt("Nombre");
		nombre.setVisible(true);
		nombre.setEnabled(false);
		duracion = new TextField("Duración");
		duracion.setInputPrompt("Duración");
		duracion.setVisible(true);
		duracion.setEnabled(false);
		anio = new TextField("Año");
		anio.setInputPrompt("Año");
		anio.setVisible(true);
		anio.setEnabled(false);
		categoria = new ComboBox("Categoría");
		categoria.setVisible(true);
		categoria.setEnabled(false);
		genero = new ComboBox("Género");
		genero.setVisible(true);
		genero.setEnabled(false);
		


		HorizontalLayout ok = new HorizontalLayout();
		ok.setSpacing(true);
		aceptar = new Button("Aceptar");
		aceptar.setVisible(true);
		aceptar.setEnabled(false);
		aceptar.setIcon(FontAwesome.CHECK);
		cancelar = new Button("Cancelar");
		cancelar.setVisible(true);
		cancelar.setEnabled(false);
		cancelar.setIcon(FontAwesome.CLOSE);
		ok.addComponents(aceptar, cancelar);
		menu.addComponents(nombre, duracion, anio, genero, categoria, ok);

		layoutDos.addComponents(grid, menu);
		return layoutDos;
	}


	private HorizontalLayout label_buscador() {
		HorizontalLayout label_buscador = new HorizontalLayout();
		label_buscador.setMargin(true);
		label_buscador.setSpacing(true);
		label = new Label("Lista de Programas");
		label.setVisible(true);
		buscador = new TextField();
		buscador.setInputPrompt("Buscador");
		label_buscador.addComponents(label, buscador);
		label_buscador.setWidth(100.0F, Unit.PERCENTAGE);
		label_buscador.setComponentAlignment(buscador, Alignment.TOP_RIGHT);
		return label_buscador;
	}
	
}
