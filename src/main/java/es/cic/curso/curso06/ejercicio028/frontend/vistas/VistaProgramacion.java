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
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Canal;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Genero;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Programa;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Programacion;
import es.cic.curso.curso06.ejercicio028.backend.service.ServicioGestorPrograma;


public class VistaProgramacion extends VerticalLayout{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7445902879676064023L;

	private Label label;
	private Grid gridProgramacion;
	private Button crear, borrar, actualizar, validar , aceptar, cancelar, mostrar;
	private ComboBox canal, programa;
	List <Genero> listaProgramacion = new ArrayList<>();
	private Programacion programacionSeleccionado;
	private ServicioGestorPrograma servicioGestorPrograma;
	private Collection<Programacion> listaProgramaciones;
	private Programa programaElegido;
	private Canal canalElegido;
	private List<Programa> listaProgramas = new ArrayList<>();
	private List<Canal> listaCanales = new ArrayList<>();

	
	
	@SuppressWarnings("serial")
	public VistaProgramacion(){
		
		servicioGestorPrograma = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorPrograma.class);
	
		HorizontalLayout layoutEncabezado = inicializaLayoutEncabezado();
		HorizontalLayout layoutUno = labelTitulo();
		HorizontalLayout layoutDos = layoutDos();
		HorizontalLayout layoutTres = layoutTres();
		addComponents(layoutEncabezado, layoutUno, layoutDos, layoutTres);
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
		crear.setIcon(FontAwesome.PLUS);
		crear.addClickListener(c->	crearProgramacion());
		
		borrar = new Button("Borrar Programa");
		borrar.setVisible(true);
		borrar.setIcon(FontAwesome.ERASER);
		borrar.addClickListener(b -> 
		this.getUI().getUI()
				.addWindow(creaVentanaConfirmacionBorradoProgramacion(programacionSeleccionado.getCanal().getNombre())));
		
		actualizar = new Button("Actualizar Programa");
		actualizar.setVisible(true);
		actualizar.setIcon(FontAwesome.REFRESH);
		actualizar.addClickListener(a -> {
			borrar_actualizar();
			canal.setValue(programacionSeleccionado.getCanal().getId());
			programa.setValue(programacionSeleccionado.getPrograma().getId());
		});

		validar = new Button ("Validar Tiempo");
		validar.addClickListener(e -> {
			cargaGrid();
			int tiempoRestante;
			tiempoRestante = canalElegido.getTiempo_maximo() - contarTiempo(canalElegido);
			Notification.show("El tiempo restante para este Canal es "+ tiempoRestante);
		});
		mostrar = new Button ("Mostrar Programas");
		mostrar.addClickListener(listener -> this.getUI().getUI().addWindow(creaVentanaProgramacionDelCanal()));
		
		layoutTres.addComponents(crear, borrar, actualizar, validar, mostrar);
		return layoutTres;
	}

	private HorizontalLayout layoutDos() {
		HorizontalLayout layoutDos = new HorizontalLayout();
		layoutDos.setMargin(true);
		layoutDos.setSpacing(true);
		VerticalLayout grid = new VerticalLayout();
		gridProgramacion = new Grid();
		gridProgramacion.setVisible(true);
		gridProgramacion.setColumns("canal", "programa");
		gridProgramacion.setSizeFull();
		gridProgramacion.setSelectionMode(SelectionMode.SINGLE);	
		gridProgramacion.addSelectionListener(e -> {
			programacionSeleccionado = null;
			if (!e.getSelected().isEmpty()) {
				programacionSeleccionado = (Programacion) e.getSelected().iterator().next();
				clickGrid();
				limpiarMenu();
				} else {
				cargaGrid();
				limpiarMenu();
				}
		});
		grid.addComponent(gridProgramacion);
		
		VerticalLayout menu = new VerticalLayout();
		menu.setMargin(true);
		menu.setSpacing(true);
		

		HorizontalLayout ok = new HorizontalLayout();
		ok.setSpacing(true);
		
		aceptar = new Button("Aceptar");
		aceptar.setVisible(true);
		aceptar.setIcon(FontAwesome.CHECK);
		aceptar.addClickListener(e -> {
			if ((canal.getValue() == null) || (programa.getValue() == null)) {
				Notification.show("Debes rellenar todos los campos.", Type.WARNING_MESSAGE);
			} else {
				try{
					nuevoOactualizado();
					
				}catch(NumberFormatException ex){
					Notification.show("Por favor coloque en cada campo los datos correspondientes", Type.WARNING_MESSAGE);
				}	
			}
		});
		
		cancelar = new Button("Cancelar");
		cancelar.setIcon(FontAwesome.CLOSE);
		cancelar.addClickListener(e-> {
			limpiarMenu();
			cargaGrid();
			
		});

		canal = new ComboBox();
		Label escogeCanal = new Label("Escoge un Canal");
		canal.setRequired(true);
		canal.setNullSelectionAllowed(true);
		canal.setRequiredError("Debes introducir un Programa.");
		canal.setNullSelectionItemId(-1);
		canal.setWidth(250.0F, Unit.PIXELS);
		actualizarCanal();
		programa = new ComboBox();
		Label escogePrograma = new Label("Escoge un Programa");
		programa.setRequired(true);
		programa.setNullSelectionAllowed(true);
		programa.setRequiredError("Debes introducir un Programa.");
		programa.setNullSelectionAllowed(true);
		programa.setNullSelectionItemId(-1);
		programa.setWidth(250.0F, Unit.PIXELS);
		actualizarPrograma();
		ok.addComponents(aceptar, cancelar);
		menu.addComponents(escogeCanal, canal, escogePrograma, programa, ok);

		layoutDos.addComponents(grid, menu);
		return layoutDos;
	}

	public void nuevoOactualizado() {
		canalElegido = servicioGestorPrograma.obtenerCanal((Long)canal.getValue());
		programaElegido = servicioGestorPrograma.obtenerPrograma((Long)programa.getValue());
		Programacion nuevoProgramacion = new Programacion(canalElegido, programaElegido);
		if (programacionSeleccionado.getId() > 0) {
			canalElegido = servicioGestorPrograma.obtenerCanal(Long.parseLong(canal.getValue().toString()));
			programacionSeleccionado.setCanal(canalElegido);
			programaElegido = servicioGestorPrograma.obtenerPrograma((Long)programa.getValue());
			programacionSeleccionado.setPrograma(programaElegido);
			servicioGestorPrograma.modificarProgramacion(programacionSeleccionado);
			limpiarMenu();
			Notification.show("Programación \"" + nuevoProgramacion.getId() + "\" editado con éxito.");
		} else {
			if(verTiempo(canalElegido, programaElegido)){
			servicioGestorPrograma.aniadirProgramacion(nuevoProgramacion);
			Notification.show("Programación \"" + nuevoProgramacion.getId() + "\" añadido con éxito.");
			}else{
				Notification.show("No hay tiempo suficiente para añadir este Programa", Type.WARNING_MESSAGE);
			}
			limpiarMenu();
		}
		cargaGrid();
	}
	
	private boolean verTiempo(Canal canal, Programa programa){
		
		boolean siHayTiempo = false;
		int tiempoRestante;
		tiempoRestante = canal.getTiempo_maximo() - contarTiempo(canal);
		if(tiempoRestante >= programa.getDuracion()){
			siHayTiempo = true;
		}
		
		return siHayTiempo;
	}
	
	private int contarTiempo(Canal canal){
		
		int tiempo = 0;
		
		List<Programacion> programas = servicioGestorPrograma.listarProgramacion();
		for (int i = 0; i< programas.size(); i++){
			if(programas.get(i).getCanal().getId() == canal.getId()){
				tiempo = tiempo + programas.get(i).getPrograma().getDuracion();
			}
		}
		return tiempo;
	}
	
	private void limpiarMenu() {
		canal.clear();
		programa.clear();
	}
	
	public void borrar_actualizar(){
		canal.setEnabled(true);
		programa.setEnabled(true);
		aceptar.setEnabled(true);
		cancelar.setEnabled(true);
		crear.setEnabled(false);
		borrar.setEnabled(false);
		actualizar.setEnabled(false);
		validar.setEnabled(false);
		mostrar.setEnabled(false);
		actualizarPrograma();
		actualizarCanal();
	}
	public void crear() {

		canal.setEnabled(false);
		programa.setEnabled(false);
		aceptar.setEnabled(false);
		cancelar.setEnabled(false);
		crear.setEnabled(true);
		borrar.setEnabled(false);
		actualizar.setEnabled(false);
		validar.setEnabled(false);
		mostrar.setEnabled(false);
	}
	
	private void actualizarPrograma() {
		
		listaProgramas = new ArrayList<>();
		listaProgramas = servicioGestorPrograma.listarPrograma();
		programa.setInputPrompt("Programa");
		programa.setNullSelectionAllowed(false);
		for(int i = 0; i < listaProgramas.size(); i++){
			programa.addItem(listaProgramas.get(i).getId());
			programa.setItemCaption(listaProgramas.get(i).getId(), listaProgramas.get(i).getNombre());
		}
		programa.select(1);
		programa.setImmediate(true);
		programa.setVisible(true);

	}

	private void actualizarCanal() {
		
		listaCanales = new ArrayList<>();
		listaCanales = servicioGestorPrograma.listarCanal();
		canal.setInputPrompt("Canal");
		canal.setNullSelectionAllowed(false);
		for(int i = 0; i < listaCanales.size(); i++){
			canal.addItem(listaCanales.get(i).getId());
			canal.setItemCaption(listaCanales.get(i).getId(), listaCanales.get(i).getNombre());
		}
		canal.select(1);
		canal.setImmediate(true);
		canal.setVisible(true);

	}

	public void crearProgramacion() {

		canal.setEnabled(true);
		programa.setEnabled(true);
		aceptar.setEnabled(true);
		cancelar.setEnabled(true);
		crear.setEnabled(true);
		borrar.setEnabled(false);
		actualizar.setEnabled(false);
		actualizarCanal();
		actualizarPrograma();
		programacionSeleccionado = new Programacion();
		programacionSeleccionado.setId((long) 0);

	}
	
	public void clickGrid() {

		canal.setEnabled(false);
		programa.setEnabled(false);
		aceptar.setEnabled(false);
		cancelar.setEnabled(false);
		crear.setEnabled(false);
		borrar.setEnabled(true);
		actualizar.setEnabled(true);
		validar.setEnabled(true);
		mostrar.setEnabled(true);
		actualizarCanal();
		actualizarPrograma();
	}
	public void enter(ViewChangeEvent event) {
		cargaGrid();
	}

	public void cargaGrid() {	
		listaProgramaciones = servicioGestorPrograma.listarProgramacion();
		gridProgramacion.setContainerDataSource(new BeanItemContainer<>(Programacion.class, listaProgramaciones));
		crear();
	}
	
	private HorizontalLayout labelTitulo() {
		HorizontalLayout labelTitulo = new HorizontalLayout();
		labelTitulo.setMargin(true);
		label = new Label("Lista Programación");
		label.setVisible(true);
		labelTitulo.addComponents(label);
		return labelTitulo;
	}
	
	public void setProgramacion(Programacion programacion) {
		this.setVisible(programacion != null);
		if (programacion != null) {
			BeanFieldGroup.bindFieldsUnbuffered(programacion, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Programacion(), this);
		}
	}

	private Window creaVentanaConfirmacionBorradoProgramacion(String canal) {
		Window resultado = new Window();
		resultado.setWidth(350.0F, Unit.PIXELS);
		resultado.setModal(true);
		resultado.setClosable(false);
		resultado.setResizable(false);
		resultado.setDraggable(false);

		label = new Label("¿Está seguro de que desea borrar este Programa de Canal: <strong>\"" + canal + "\"</strong>?");
		label.setContentMode(ContentMode.HTML);

		Button botonAceptar = new Button("Aceptar");
		botonAceptar.addClickListener(e -> {
			
			servicioGestorPrograma.borrarProgramacion(programacionSeleccionado.getId());
			cargaGrid();
			resultado.close();
		});

		Button botonCancelar = new Button("Cancelar");
		botonCancelar.addClickListener(e -> {
			cargaGrid();
			resultado.close();
		});

		HorizontalLayout layoutBotones = new HorizontalLayout();
		layoutBotones.setMargin(true);
		layoutBotones.setSpacing(true);
		layoutBotones.setWidth(100.0F, Unit.PERCENTAGE);
		layoutBotones.addComponents(botonAceptar, botonCancelar);

		final FormLayout content = new FormLayout();
		content.setMargin(true);
		
		List<Programacion> programas = servicioGestorPrograma.listarProgramacion();
		for (int i = 0; i< programas.size(); i++){
			if(programas.get(i).getCanal().getId() == canalElegido.getId()){
				content.addComponents(new Label(programas.get(i).getPrograma().getNombre()));
			}
		}
		content.addComponents(label, layoutBotones);
		resultado.setContent(content);
		resultado.center();
		return resultado;
	}
	
	private Window creaVentanaProgramacionDelCanal() {
		Window resultado = new Window();
		String titulo = "Programas Incluidos en este Canal";
		resultado.setCaption(programacionSeleccionado == null ? titulo
				: String.format("%s: <strong>\"%s\"</strong>", titulo, programacionSeleccionado.getPrograma()));
		resultado.setCaptionAsHtml(true);
		resultado.setModal(true);
		resultado.setClosable(true);
		resultado.setResizable(true);
		resultado.setDraggable(true);
		resultado.setHeight(400.0F, Unit.PIXELS);
		resultado.setWidth(600.0F, Unit.PIXELS);

		gridProgramacion = new Grid();
		
		gridProgramacion.setColumns("programa");
		gridProgramacion.setSizeFull();
		List<Programacion> programas = servicioGestorPrograma.listarProgramacion();
		List<Programacion> p = new ArrayList<>();
		for (Programacion programacion : programas) {
			if(programacion.getCanal().getId().equals(canalElegido.getId())){
				p.add(programacion);
				
			}
			
		}
		gridProgramacion.setContainerDataSource(new BeanItemContainer<>(Programacion.class, p));


		VerticalLayout layoutGrid = new VerticalLayout();
		layoutGrid.setMargin(new MarginInfo(false, true, false, true));
		layoutGrid.setSpacing(false);
		layoutGrid.setSizeFull();
		layoutGrid.addComponent(gridProgramacion);

		Button siguiente = new Button("Siguiente");
		siguiente.addClickListener(e -> {
			cargaGrid();
			resultado.close();
		});

		HorizontalLayout layoutBotones = new HorizontalLayout();
		layoutBotones.setMargin(new MarginInfo(false, true, false, true));
		layoutBotones.setSpacing(true);
		layoutBotones.setHeight(100.0F, Unit.PERCENTAGE);
		layoutBotones.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
		layoutBotones.addComponents(siguiente);

		VerticalSplitPanel layoutPrincipal = new VerticalSplitPanel();
		layoutPrincipal.setSplitPosition(90.0F, Unit.PIXELS, true);
		layoutPrincipal.setLocked(true);
		layoutPrincipal.setFirstComponent(layoutGrid);
		layoutPrincipal.setSecondComponent(layoutBotones);

		resultado.setContent(layoutPrincipal);
		resultado.center();
		return resultado;
	}
	public Programacion getProgramacionSeleccionado() {
		return programacionSeleccionado;
	}
	public void setProgramacionSeleccionado(Programacion programacionSeleccionado) {
		this.programacionSeleccionado = programacionSeleccionado;
	}
}
