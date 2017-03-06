package es.cic.curso.curso06.ejercicio028.frontend.vistas;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Canal;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Categoria;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Genero;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Programa;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Usuario;
import es.cic.curso.curso06.ejercicio028.backend.service.ServicioGestorPrograma;




public class VistaProgramas extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7445902879676064023L;

	private TextField nombre, duracion, anio;
	private Label label;
	private Grid gridProgramas;
	private Button crear, borrar, actualizar, aceptar, cancelar;
	private ComboBox genero, categoria;
	private List<String> lisCategorias = new ArrayList<>();
	List <Genero> listaGeneros = new ArrayList<>();
	List <Categoria> listaCategorias = new ArrayList<>();
	private List<String> lisGeneros = new ArrayList<>();
	private Programa programa, programaSeleccionado;
	private ServicioGestorPrograma servicioGestorPrograma;
	private Genero generoElegido;
	private Categoria categoriaElegida;

	private Label label_genero;
	private Label label_categoria;
	
	
	@SuppressWarnings("serial")
	public VistaProgramas(){
		
		programa = new Programa();
		
		servicioGestorPrograma = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorPrograma.class);
		HorizontalLayout layoutEncabezado = inicializaLayoutEncabezado();
		HorizontalLayout layoutUno = label_buscador();
		HorizontalLayout layoutDos = layoutDos();
		HorizontalLayout layoutTres = layoutTres();
		addComponents(layoutEncabezado, layoutUno, layoutDos, layoutTres);	
		cargaDatos();
		cargaGrid();	
	}

	private HorizontalLayout inicializaLayoutEncabezado() {
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/cic_logo.png"));
		Image imagen = new Image(null, resource);
		imagen.setHeight(60.0F, Unit.PIXELS);
		
		Label titulo = new Label("<span style=\"font-size: 175%;\">Programación Televisiva</span>");
		titulo.setContentMode(ContentMode.HTML);

		HorizontalLayout layoutEncabezado = new HorizontalLayout();
		layoutEncabezado.setMargin(new MarginInfo(true, true, false, true));
		layoutEncabezado.setSpacing(false);
		layoutEncabezado.addComponents(imagen, titulo);
		layoutEncabezado.setComponentAlignment(titulo, Alignment.MIDDLE_LEFT);
		return layoutEncabezado;
	}

	private HorizontalLayout layoutTres() {
		HorizontalLayout layoutTres = new HorizontalLayout();
		layoutTres.setMargin(true);
		layoutTres.setSpacing(true);
		crear = new Button("Crear");
		crear.setVisible(true);
		crear.setEnabled(true);
		crear.setIcon(FontAwesome.PLUS);
		crear.addClickListener(c-> {
			crearPrograma();
		});
		
		borrar = new Button("Borrar");
		borrar.setVisible(true);
		borrar.setEnabled(true);
		borrar.setIcon(FontAwesome.ERASER);
		borrar.addClickListener(b -> this.getUI().getUI()
				.addWindow(creaVentanaConfirmacionBorradoProgramas(programaSeleccionado.getNombre())));
		
		actualizar = new Button("Actualizar");
		actualizar.setVisible(true);
		actualizar.setEnabled(true);
		actualizar.setIcon(FontAwesome.REFRESH);
		actualizar.addClickListener(a -> {
				borrar_actualizar();
				nombre.setValue(String.valueOf(programaSeleccionado.getNombre()));
				duracion.setValue(String.valueOf(programaSeleccionado.getDuracion()));
				anio.setValue(String.valueOf(programaSeleccionado.getAnio()));
				genero.setValue(programaSeleccionado.getGenero().getId());
				categoria.setValue(programaSeleccionado.getCategoria().getId());
	
		});
		
		layoutTres.addComponents(crear, borrar, actualizar);
		return layoutTres;
	}

	private HorizontalLayout layoutDos() {
		HorizontalLayout layoutDos = new HorizontalLayout();
		layoutDos.setMargin(true);
		layoutDos.setSpacing(true);
		VerticalLayout grid = new VerticalLayout();
		gridProgramas = new Grid();
		gridProgramas.setVisible(true);
		gridProgramas.setColumns("nombre", "duracion", "anio", "categoria", "genero" );
		gridProgramas.setSizeFull();
		gridProgramas.setSelectionMode(SelectionMode.SINGLE);	
		gridProgramas.addSelectionListener(e -> {
			programaSeleccionado = null;
			if (!e.getSelected().isEmpty()) {
				programaSeleccionado = (Programa) e.getSelected().iterator().next();
				crear.setEnabled(true);
				borrar.setEnabled(true);
				actualizar.setEnabled(true);
				} else {
					crear.setEnabled(true);
					borrar.setEnabled(true);
					actualizar.setEnabled(true);
				}
		});
		grid.addComponent(gridProgramas);
		
		VerticalLayout menu = new VerticalLayout();
		menu.setMargin(true);
		menu.setSpacing(true);
		
			nombre = new TextField("Nombre");
			nombre.setInputPrompt("Nombre");
			nombre.setWidth(250.0F, Unit.PIXELS);
			nombre.setVisible(true);
			nombre.setEnabled(true);
			duracion = new TextField("Duración");
			duracion.setInputPrompt("Duración");
			duracion.setWidth(250.0F, Unit.PIXELS);
			duracion.setVisible(true);
			duracion.setEnabled(true);
			anio = new TextField("Año");
			anio.setInputPrompt("Año");
			anio.setWidth(250.0F, Unit.PIXELS);
			anio.setVisible(true);
			anio.setEnabled(true);
			
		HorizontalLayout ok = new HorizontalLayout();
		ok.setSpacing(true);
		
		aceptar = new Button("Aceptar");
		aceptar.setVisible(true);
		aceptar.setEnabled(true);
		aceptar.setIcon(FontAwesome.CHECK);
		aceptar.addClickListener(e -> {
			if ("".equals(nombre.getValue()) || "".equals(duracion.getValue()) || "".equals(anio.getValue())|| "".equals(categoria.getValue()) || "".equals(genero.getValue())) {
				Notification.show("Debes rellenar todos los campos.");
			} else {
				try{
					categoriaElegida = servicioGestorPrograma.obtenerCategoria((Long)categoria.getValue());
					generoElegido = servicioGestorPrograma.obtenerGenero((Long)genero.getValue());

					Programa nuevoPrograma = new Programa(nombre.getValue(), Integer.parseInt(duracion.getValue()), 
						Integer.parseInt(anio.getValue()), categoriaElegida, generoElegido);
					if (programaSeleccionado.getId() > 0) {
						programaSeleccionado.setNombre(nombre.getValue());
						programaSeleccionado.setDuracion(Integer.parseInt(duracion.getValue()));
						programaSeleccionado.setAnio(Integer.parseInt(anio.getValue()));
						categoriaElegida = servicioGestorPrograma.obtenerCategoria(Long.parseLong(categoria.getValue().toString()));
						programaSeleccionado.setCategoria(categoriaElegida);
						generoElegido = servicioGestorPrograma.obtenerGenero(Long.parseLong(genero.getValue().toString()));
						programaSeleccionado.setGenero(generoElegido);
						servicioGestorPrograma.modificarPrograma(programaSeleccionado);
						limpiarMenu();
						Notification.show("Programa \"" + nuevoPrograma.getNombre() + "\" editado con éxito.");
					} else {
						servicioGestorPrograma.aniadirPrograma(nuevoPrograma);
						Notification.show("Programa \"" + nuevoPrograma.getNombre() + "\" añadido con éxito.");
						limpiarMenu();
					}
					cargaGrid();
					menu.setEnabled(true);
					crear.setEnabled(true);
					
				}catch(NumberFormatException ex){
					Notification.show("En numeros pon numeros");
				}	
			}
		});
		cancelar = new Button("Cancelar");
		cancelar.setIcon(FontAwesome.CLOSE);
		cancelar.setEnabled(true);
		cancelar.addClickListener(e-> {
			limpiarMenu();
			cargaGrid();	
		});
		
		categoria = new ComboBox();
		categoria.setWidth(250.0F, Unit.PIXELS);
		Label label_categoria = new Label("Categoría");;
		actualizarCategoria();
		genero = new ComboBox();
		genero.setWidth(250.0F, Unit.PIXELS);
		Label label_genero = new Label("Género");
		actualizarGenero();

		ok.addComponents(aceptar, cancelar);
		menu.addComponents(nombre, duracion, anio,label_categoria, categoria,label_genero, genero, ok);

		layoutDos.addComponents(grid, menu);
		return layoutDos;
	}
	private void limpiarMenu() {
		duracion.clear();
		nombre.clear();
		anio.clear();
		genero.clear();
		categoria.clear();
		
	}
	
	private void actualizarCategoria() {
		
		List <Categoria> listaCategorias = new ArrayList<>();
		
		listaCategorias = servicioGestorPrograma.listarCategoria();

		categoria.setInputPrompt("Categoría");
		categoria.setNullSelectionAllowed(false);
		for(int i = 0; i < listaCategorias.size(); i++){
			genero.addItem(listaCategorias.get(i).getId());
			genero.setItemCaption(listaCategorias.get(i).getId(), listaCategorias.get(i).getNombre());
		}
		categoria.select(1);
		categoria.setImmediate(true);
		categoria.setVisible(true);

	}
	private void actualizarGenero() {
		
		List <Genero> listaGeneros = new ArrayList<>();
		
		listaGeneros = servicioGestorPrograma.listarGenero();

		genero.setInputPrompt("Género");
		genero.setNullSelectionAllowed(false);
		for(int i = 0; i < listaGeneros.size(); i++){
			genero.addItem(listaGeneros.get(i).getId());
			genero.setItemCaption(listaGeneros.get(i).getId(), listaGeneros.get(i).getNombre());
		}
		genero.select(1);
		genero.setImmediate(true);
		genero.setVisible(true);

	}

	public void borrar_actualizar(){
		nombre.setEnabled(true);
		duracion.setEnabled(true);
		anio.setEnabled(true);
		aceptar.setEnabled(true);
		cancelar.setEnabled(true);
		crear.setEnabled(false);
		borrar.setEnabled(false);
		actualizar.setEnabled(false);
		actualizarGenero();
		actualizarCategoria();
	}
	public void crearPrograma() {
		nombre.setEnabled(true);
		duracion.setEnabled(true);
		anio.setEnabled(true);
		genero.setEnabled(true);
		categoria.setEnabled(true);
		aceptar.setEnabled(true);
		cancelar.setEnabled(true);
		crear.setEnabled(true);
		borrar.setEnabled(true);
		actualizar.setEnabled(true);
		actualizarGenero();
		actualizarCategoria();
		programaSeleccionado = new Programa();
		programaSeleccionado.setId((long) 0);
	}
	public void enter(ViewChangeEvent event) {
		cargaGrid();
	}
	public void cargaGrid() {
		
		Collection<Programa> listaProgramas = servicioGestorPrograma.listarPrograma();
		gridProgramas.setContainerDataSource(new BeanItemContainer<>(Programa.class, listaProgramas));
		crear.setEnabled(true);

	}
	private HorizontalLayout label_buscador() {
		HorizontalLayout label_buscador = new HorizontalLayout();
		label_buscador.setMargin(true);
		label = new Label("Lista de Programas");
		label.setVisible(true);
		label_buscador.addComponents(label);
		return label_buscador;
	}
	public void setPrograma(Programa programa) {
		this.setVisible(programa != null);
		this.programa = programa;

		if (programa != null) {
			BeanFieldGroup.bindFieldsUnbuffered(programa, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Programa(), this);
		}
	}
	private Window creaVentanaConfirmacionBorradoProgramas(String nombre) {
		Window resultado = new Window();
		resultado.setWidth(350.0F, Unit.PIXELS);
		resultado.setModal(true);
		resultado.setClosable(false);
		resultado.setResizable(false);
		resultado.setDraggable(false);

		Label label = new Label("¿Está seguro de que desea borrar este Programa, perderá todo lo guardado en él: <strong>\"" + nombre + "\"</strong>?");
		label.setContentMode(ContentMode.HTML);

		Button botonAceptar = new Button("Aceptar");
		botonAceptar.addClickListener(e -> {
			servicioGestorPrograma.borrarPrograma(programaSeleccionado.getId());
			cargaGrid();
			resultado.close();
		});

		Button botonCancelar = new Button("Cancelar");
		botonCancelar.addClickListener(e -> resultado.close());

		HorizontalLayout layoutBotones = new HorizontalLayout();
		layoutBotones.setMargin(true);
		layoutBotones.setSpacing(true);
		layoutBotones.setWidth(100.0F, Unit.PERCENTAGE);
		layoutBotones.addComponents(botonAceptar, botonCancelar);

		final FormLayout content = new FormLayout();
		content.setMargin(true);
		content.addComponents(label, layoutBotones);
		resultado.setContent(content);
		resultado.center();
		return resultado;
	
	}
	public Programa getProgramaSeleccionado() {
		return programaSeleccionado;
	}

	public void setProgramaSeleccionado(Programa programaSeleccionado) {
		this.programaSeleccionado = programaSeleccionado;
	}
	
	private void cargaDatos() {
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
			Programa programa3 = new Programa("Programa_2", 60, 2015, categoria2, genero1);
			servicioGestorPrograma.aniadirPrograma(programa3);

	}
}
