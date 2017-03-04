package es.cic.curso.curso06.ejercicio028.backend.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.cic.curso.curso06.ejercicio028.backend.repository.Identificable;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Identificable<Long> {
	private static final long serialVersionUID = -8800715225024553533L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellidos")
	private String apellidos;

	@OneToMany(mappedBy = "usuario")
	private List<Canal> usuarios = new ArrayList<>();
	
	
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Usuario(String nombre, String apellidos, List<Canal> usuarios) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.usuarios = usuarios;
	}



	public Usuario(Long id, String nombre, String apellidos, List<Canal> usuarios) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.usuarios = usuarios;
	}



	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellidos() {
		return apellidos;
	}



	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}



	public List<Canal> getUsuarios() {
		return usuarios;
	}



	public void setUsuarios(List<Canal> usuarios) {
		this.usuarios = usuarios;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", usuarios=" + usuarios
				+ "]";
	}
	}