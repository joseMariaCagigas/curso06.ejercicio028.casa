package es.cic.curso.curso06.ejercicio028.frontend.principal;

import java.util.List;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Categoria;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Genero;
import es.cic.curso.curso06.ejercicio028.backend.service.ServicioGestorPrograma;

public class VistaGeneros extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5366004381410718812L;

	private TextField buscador;
	private TextField nombre, descripcion;
	private Label label;
	private Grid gridGeneros;
	private Button crear, borrar, actualizar, aceptar, cancelar;
	private Genero nuevoGenero;
	private ServicioGestorPrograma servicioGestorPrograma;
	private List<Categoria> listaCategorias;
	
	
	@SuppressWarnings("serial")
	public VistaGeneros(){
		
		nuevoGenero = new Genero();
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
		gridGeneros = new Grid();
		gridGeneros.setVisible(true);
		gridGeneros.setColumns("Nombre", "Descripción");
		gridGeneros.setSizeFull();
		gridGeneros.setSelectionMode(SelectionMode.SINGLE);
		grid.addComponent(gridGeneros);
		VerticalLayout menu = new VerticalLayout();
		menu.setMargin(true);
		menu.setSpacing(true);
		nombre = new TextField("Nombre");
		nombre.setInputPrompt("Nombre");
		nombre.setVisible(true);
		nombre.setEnabled(false);
		descripcion = new TextField("Descripción");
		descripcion.setInputPrompt("Descripción");
		descripcion.setVisible(true);
		descripcion.setEnabled(false);

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
		menu.addComponents(nombre, descripcion, ok);

		layoutDos.addComponents(grid, menu);
		return layoutDos;
	}


	private HorizontalLayout label_buscador() {
		HorizontalLayout label_buscador = new HorizontalLayout();
		label_buscador.setMargin(true);
		label_buscador.setSpacing(true);
		label = new Label("Lista de Generos");
		label.setVisible(true);
		buscador = new TextField();
		buscador.setInputPrompt("Buscador");
		label_buscador.addComponents(label, buscador);
		label_buscador.setWidth(100.0F, Unit.PERCENTAGE);
		label_buscador.setComponentAlignment(buscador, Alignment.TOP_RIGHT);
		return label_buscador;
	}
	
}