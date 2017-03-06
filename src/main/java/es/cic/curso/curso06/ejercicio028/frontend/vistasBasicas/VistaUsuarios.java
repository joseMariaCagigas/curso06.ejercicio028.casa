package es.cic.curso.curso06.ejercicio028.frontend.vistasBasicas;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
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

import es.cic.curso.curso06.ejercicio028.backend.dominio.Categoria;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Usuario;
import es.cic.curso.curso06.ejercicio028.backend.service.ServicioGestorPrograma;

public class VistaUsuarios extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5366004381410718812L;

	private TextField nombre, apellidos;
	private Label label;
	private Grid gridUsuarios;
	private Button crear, borrar, actualizar, aceptar, cancelar;
	private Usuario nuevoUsuario, usuarioSeleccionado;
	private ServicioGestorPrograma servicioGestorPrograma;
	private Collection<Usuario> listaUsuarios;

	@SuppressWarnings("serial")
	public VistaUsuarios() {

		servicioGestorPrograma = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorPrograma.class);
		nuevoUsuario = new Usuario();

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
		crear.addClickListener(e -> {
			crearUsuario();
		});
		borrar = new Button("Borrar");
		borrar.setVisible(true);
		borrar.setEnabled(false);
		borrar.setIcon(FontAwesome.ERASER);
		borrar.addClickListener(e -> this.getUI().getUI()
				.addWindow(creaVentanaConfirmacionBorradoUsuarios(usuarioSeleccionado.getNombre())));
				

		actualizar = new Button("Actualizar");
		actualizar.setVisible(true);
		actualizar.setEnabled(false);
		actualizar.setIcon(FontAwesome.REFRESH);
		actualizar.addClickListener(e -> {
			nombre.setValue(usuarioSeleccionado.getNombre());
			apellidos.setValue(usuarioSeleccionado.getApellidos());
			nombre.setEnabled(true);
			apellidos.setEnabled(true);
			aceptar.setEnabled(true);
			cancelar.setEnabled(true);
			crear.setEnabled(false);
			borrar.setEnabled(false);
			actualizar.setEnabled(false);
		});
		
		layoutTres.addComponents(crear, borrar, actualizar);
		return layoutTres;
	}

	private HorizontalLayout layoutDos() {
		HorizontalLayout layoutDos = new HorizontalLayout();
		layoutDos.setMargin(true);
		layoutDos.setSpacing(true);
		VerticalLayout grid = new VerticalLayout();
		grid.setSpacing(true);

		gridUsuarios = new Grid();
		gridUsuarios.setVisible(true);
		gridUsuarios.setColumns("nombre", "apellidos");
		gridUsuarios.setSizeFull();
		gridUsuarios.setSelectionMode(SelectionMode.SINGLE);
		gridUsuarios.addSelectionListener(e -> {
			usuarioSeleccionado = null;
			if (!e.getSelected().isEmpty()) {
				usuarioSeleccionado = (Usuario) e.getSelected().iterator().next();
				System.out.println(usuarioSeleccionado.getId());
				borrar.setEnabled(true);
				actualizar.setEnabled(true);
			} else {
				ocultarElementos();

			}
		});

		grid.addComponent(gridUsuarios);
		VerticalLayout menu = new VerticalLayout();
		menu.setMargin(true);
		menu.setSpacing(true);
		nombre = new TextField("Nombre");
		nombre.setInputPrompt("Nombre");
		nombre.setVisible(true);
		nombre.setEnabled(false);
		nombre.setWidth(250.0F, Unit.PIXELS);
		apellidos = new TextField("Apellidos");
		apellidos.setInputPrompt("Apellidos");
		apellidos.setVisible(true);
		apellidos.setEnabled(false);
		apellidos.setWidth(250.0F, Unit.PIXELS);

		HorizontalLayout ok = new HorizontalLayout();
		ok.setSpacing(true);
		aceptar = new Button("Aceptar");
		aceptar.setVisible(true);
		aceptar.setEnabled(false);
		aceptar.setIcon(FontAwesome.CHECK);
		aceptar.addClickListener(e -> {
			if ("".equals(nombre.getValue()) || "".equals(apellidos.getValue())) {
				Notification.show("Debes indicar un nombre y una descripción para crear un Género nuevo.");
			} else {
				Usuario nuevoUsuario = new Usuario(nombre.getValue(), apellidos.getValue());
				if (usuarioSeleccionado.getId() > 0) {
					usuarioSeleccionado.setNombre(nombre.getValue());
					usuarioSeleccionado.setApellidos(apellidos.getValue());
					servicioGestorPrograma.modificarUsuario(usuarioSeleccionado);
					Notification.show("Usuario \"" + nuevoUsuario.getNombre() + "\" editado con éxito.");
					
				} else {
					servicioGestorPrograma.aniadirUsuario(nuevoUsuario);
					Notification.show("Usuario \"" + nuevoUsuario.getNombre() + "\" añadido con éxito.");
				}
				cargaGrid();
				ocultarElementos();
				apellidos.clear();
				nombre.clear();
				
			}
		});
		cancelar = new Button("Cancelar");
		cancelar.setIcon(FontAwesome.CLOSE);
		cancelar.addClickListener(e -> {
			apellidos.clear();
			nombre.clear();
			cargaGrid();
			
			

		});

		ok.addComponents(aceptar, cancelar);
		menu.addComponents(nombre, apellidos, ok);

		layoutDos.addComponents(grid, menu);
		return layoutDos;
	}

	public void ocultarElementos() {
		cancelar.setEnabled(false);
		aceptar.setEnabled(false);
		nombre.setEnabled(false);
		apellidos.setEnabled(false);
		crear.setEnabled(true);

	}

	public void crearUsuario() {

		nombre.setEnabled(true);
		apellidos.setEnabled(true);
		aceptar.setEnabled(true);
		cancelar.setEnabled(true);
		crear.setEnabled(false);
		borrar.setEnabled(false);
		actualizar.setEnabled(false);
		usuarioSeleccionado = new Usuario();
		usuarioSeleccionado.setId((long) 0);

	}

	private HorizontalLayout label_buscador() {
		HorizontalLayout label_buscador = new HorizontalLayout();
		label_buscador.setMargin(true);
		label_buscador.setSpacing(true);
		label = new Label("Lista de Usuarios");
		label.setVisible(true);
		label_buscador.addComponents(label);
		return label_buscador;
	}

	public void enter(ViewChangeEvent event) {
		cargaGrid();
	}

	public void cargaGrid() {
		
		Collection<Usuario> listaUsuarios = servicioGestorPrograma.listarUsuario();
		gridUsuarios.setContainerDataSource(new BeanItemContainer<>(Usuario.class, listaUsuarios));
		ocultarElementos();
	}
	
	private Window creaVentanaConfirmacionBorradoUsuarios(String nombre) {
		Window resultado = new Window();
		resultado.setWidth(350.0F, Unit.PIXELS);
		resultado.setModal(true);
		resultado.setClosable(false);
		resultado.setResizable(false);
		resultado.setDraggable(false);

		Label label = new Label("¿Está seguro de que desea borrar este Género: <strong>\"" + nombre + "\"</strong>?");
		label.setContentMode(ContentMode.HTML);

		Button botonAceptar = new Button("Aceptar");
		botonAceptar.addClickListener(e -> {
			servicioGestorPrograma.borrarUsuario(usuarioSeleccionado.getId());
			cargaGrid();
			resultado.close();
			borrar.setEnabled(false);
			actualizar.setEnabled(false);
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

	
	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
	
	private void cargaDatos() {
		if (servicioGestorPrograma.listarUsuario().isEmpty()) {
			Usuario usuario1 = new Usuario("Héctor", "Cifuentes Pérez");
			servicioGestorPrograma.aniadirUsuario(usuario1);
			Usuario usuario2 = new Usuario("Manuel", "Hacha Bendita");
			servicioGestorPrograma.aniadirUsuario(usuario2);
			Usuario usuario3 = new Usuario("Ana", "Caida del Árbol");
			servicioGestorPrograma.aniadirUsuario(usuario3);
			Usuario usuario4 = new Usuario("Gustavo", "Torre Mocho");
			servicioGestorPrograma.aniadirUsuario(usuario4);
	}
	}
}