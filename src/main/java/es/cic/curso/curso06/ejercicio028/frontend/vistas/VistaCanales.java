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
import com.vaadin.server.Sizeable.Unit;
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




public class VistaCanales extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7445902879676064023L;

	private TextField buscador;
	private TextField nombre, tiempo;
	private Label label;
	private Grid gridCanales;
	private Button crear, borrar, actualizar, aceptar, cancelar;
	private ComboBox usuario;
	List <Usuario> listaUsuarios = new ArrayList<>();
	private List<String> lisUsuarios = new ArrayList<>();
	private Canal canal, canalSeleccionado;
	private ServicioGestorPrograma servicioGestorPrograma;
	private Collection<Canal> listaCanales;
	private Usuario usuarioElegido;
	
	
	@SuppressWarnings("serial")
	public VistaCanales(){
		
		canal = new Canal();
		
		servicioGestorPrograma = ContextLoader.getCurrentWebApplicationContext().getBean(ServicioGestorPrograma.class);
		
		HorizontalLayout layoutEncabezado = inicializaLayoutEncabezado();
		HorizontalLayout layoutUno = label_buscador();
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
		crear.setEnabled(true);
		crear.setIcon(FontAwesome.PLUS);
		crear.addClickListener(c-> {
			crearCanal();
			
		});
		
		borrar = new Button("Borrar");
		borrar.setVisible(true);
		borrar.setEnabled(false);
		borrar.setIcon(FontAwesome.ERASER);
		borrar.addClickListener(b -> this.getUI().getUI()
				.addWindow(creaVentanaConfirmacionBorradoCanales(canalSeleccionado.getNombre())));
		
		actualizar = new Button("Actualizar");
		actualizar.setVisible(true);
		actualizar.setEnabled(false);
		actualizar.setIcon(FontAwesome.REFRESH);
		actualizar.addClickListener(a -> {
			activarMenu();
			nombre.setValue(canalSeleccionado.getNombre());
			tiempo.setValue(String.valueOf(canalSeleccionado.getTiempo_maximo()));
			usuario.setValue(canalSeleccionado.getUsuario());
		});
		
		layoutTres.addComponents(crear, borrar, actualizar);
		return layoutTres;
	}

	private HorizontalLayout layoutDos() {
		HorizontalLayout layoutDos = new HorizontalLayout();
		layoutDos.setMargin(true);
		layoutDos.setSpacing(true);
		VerticalLayout grid = new VerticalLayout();
		gridCanales = new Grid();
		gridCanales.setVisible(true);
		gridCanales.setColumns("nombre", "usuario", "tiempo_maximo");
		gridCanales.setSizeFull();
		gridCanales.setSelectionMode(SelectionMode.SINGLE);	
		gridCanales.addSelectionListener(e -> {
			canalSeleccionado = null;
			if (!e.getSelected().isEmpty()) {
				canalSeleccionado = (Canal) e.getSelected().iterator().next();
				crear.setEnabled(false);
				borrar.setEnabled(true);
				actualizar.setEnabled(true);
				} else {
					crear.setEnabled(true);
					borrar.setEnabled(false);
					actualizar.setEnabled(false);
				}
		});
		grid.addComponent(gridCanales);
		

		
		VerticalLayout menu = new VerticalLayout();
		menu.setMargin(true);
		menu.setSpacing(true);
		
			nombre = new TextField("Nombre");
			nombre.setInputPrompt("Nombre");
			nombre.setVisible(true);
			nombre.setEnabled(false);
			nombre.setWidth(250.0F, Unit.PIXELS);
			tiempo = new TextField("Tiempo");
			tiempo.setInputPrompt("Tiempo");
			tiempo.setVisible(true);
			tiempo.setEnabled(false);
			tiempo.setWidth(250.0F, Unit.PIXELS);
			
			

		HorizontalLayout ok = new HorizontalLayout();
		ok.setSpacing(true);
		
		aceptar = new Button("Aceptar");
		aceptar.setVisible(true);
		aceptar.setEnabled(false);
		aceptar.setIcon(FontAwesome.CHECK);
		aceptar.addClickListener(e -> {
			if ("".equals(nombre.getValue()) || "".equals(tiempo.getValue()) || "".equals(usuario.getValue())) {
				Notification.show("Debes rellenar todos los campos, si no existe tu usuario debes crearlo primero.");
			} else {
				try{	usuarioElegido = servicioGestorPrograma.obtenerUsuario((Long)usuario.getValue());
				

				Canal nuevoCanal = new Canal(nombre.getValue(), Integer.parseInt(tiempo.getValue()), usuarioElegido);
				if (canalSeleccionado.getId() > 0) {
					canalSeleccionado.setNombre(nombre.getValue());
					canalSeleccionado.setTiempo_maximo(Integer.parseInt(tiempo.getValue()));
					usuarioElegido = servicioGestorPrograma.obtenerUsuario(Long.parseLong(usuario.getValue().toString()));
					canalSeleccionado.setUsuario(usuarioElegido);
					servicioGestorPrograma.modificarCanal(canalSeleccionado);
					limpiarMenu();
					Notification.show("Canal \"" + nuevoCanal.getNombre() + "\" editado con éxito.");
				} else {
					servicioGestorPrograma.aniadirCanal(nuevoCanal);
					Notification.show("Canal \"" + nuevoCanal.getNombre() + "\" añadido con éxito.");
					limpiarMenu();
				}
				cargaGrid();
				menu.setEnabled(false);
				crear.setEnabled(true);
				
			}catch(NumberFormatException ex){
				Notification.show("En numeros pon numeros");
			}	
		}
	});
		
		cancelar = new Button("Cancelar");
		cancelar.setIcon(FontAwesome.CLOSE);
		cancelar.setEnabled(false);
		cancelar.addClickListener(e-> {
			desactivarMenu();
			limpiarMenu();
			cargaGrid();
			
		});
		usuario = new ComboBox();
		usuario.setWidth(250.0F, Unit.PIXELS);
		Label label_usuario = new Label("Usuario");
		actualizarUsuario();
		ok.addComponents(aceptar, cancelar);
		menu.addComponents(nombre, tiempo,label_usuario,  usuario, ok);

		layoutDos.addComponents(grid, menu);
		return layoutDos;
	}
	private void limpiarMenu() {
		
		tiempo.clear();
		nombre.clear();
		usuario.clear();
	}

	private void activarMenu(){
		
		tiempo.setEnabled(true);
		nombre.setEnabled(true);
		usuario.setEnabled(true);
		aceptar.setEnabled(true);
		cancelar.setEnabled(true);
		
	}

	private void desactivarMenu(){
		
		nombre.setEnabled(false);
		tiempo.setEnabled(false);;
		usuario.setEnabled(false);
		aceptar.setEnabled(false);
		cancelar.setEnabled(false);
	}

	private void actualizarUsuario() {
		
		List <Usuario> listaUsuarios = new ArrayList<>();
		
		listaUsuarios = servicioGestorPrograma.listarUsuario();

		usuario.setInputPrompt("Usuario");
		usuario.setNullSelectionAllowed(false);
		for(int i = 0; i < listaUsuarios.size(); i++){
			usuario.addItem(listaUsuarios.get(i).getId());
			usuario.setItemCaption(listaUsuarios.get(i).getId(), listaUsuarios.get(i).getNombre());
		}
		usuario.select(1);
		usuario.setImmediate(true);
		usuario.setVisible(true);

	}

	public void crearCanal() {

		nombre.setEnabled(true);
		tiempo.setEnabled(true);
		usuario.setEnabled(true);
		aceptar.setEnabled(true);
		cancelar.setEnabled(true);
		crear.setEnabled(false);
		borrar.setEnabled(false);
		actualizar.setEnabled(false);
		actualizarUsuario();
		canalSeleccionado = new Canal();
		canalSeleccionado.setId((long) 0);

	}
	
	public void enter(ViewChangeEvent event) {
		cargaGrid();
	}

	public void cargaGrid() {
		
		Collection<Canal> listaCanales = servicioGestorPrograma.listarCanal();
		gridCanales.setContainerDataSource(new BeanItemContainer<>(Canal.class, listaCanales));
		crear.setEnabled(true);

	}
	
	private HorizontalLayout label_buscador() {
		HorizontalLayout label_buscador = new HorizontalLayout();
		label_buscador.setMargin(true);
		label = new Label("Lista de Canales");
		label.setVisible(true);
		buscador = new TextField();
		buscador.setWidth(250.0F, Unit.PIXELS);
		buscador.setInputPrompt("Buscador");
		label_buscador.addComponents(label, buscador);
		label_buscador.setWidth(100.0F, Unit.PERCENTAGE);
		label_buscador.setComponentAlignment(buscador, Alignment.TOP_RIGHT);
		return label_buscador;
	}

	private void recorrerUsuarios() {
		for(Usuario usu :listaUsuarios){
			if(usuario.getValue() == usu.getNombre()){
				usuarioElegido.setNombre(usu.getNombre());
				
			}			
		}
	}
	
	public void setCanal(Canal canal) {
		this.setVisible(canal != null);
		this.canal = canal;

		if (canal != null) {
			BeanFieldGroup.bindFieldsUnbuffered(canal, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Canal(), this);
		}
	}

	private Window creaVentanaConfirmacionBorradoCanales(String nombre) {
		Window resultado = new Window();
		resultado.setWidth(350.0F, Unit.PIXELS);
		resultado.setModal(true);
		resultado.setClosable(false);
		resultado.setResizable(false);
		resultado.setDraggable(false);

		Label label = new Label("¿Está seguro de que desea borrar este Canal: <strong>\"" + nombre + "\"</strong>?");
		label.setContentMode(ContentMode.HTML);

		Button botonAceptar = new Button("Aceptar");
		botonAceptar.addClickListener(e -> {
			servicioGestorPrograma.borrarCanal(canalSeleccionado.getId());
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

	public Canal getCanalSeleccionado() {
		return canalSeleccionado;
	}


	public void CanalSeleccionado(Canal canalSeleccionado) {
		this.canalSeleccionado = canalSeleccionado;
	}
	
}
