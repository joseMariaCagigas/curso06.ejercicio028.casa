package es.cic.curso.curso06.ejercicio028.backend.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.cic.curso.curso06.ejercicio028.backend.repository.Identificable;

@Entity
@Table(name = "CATEGORIA")
public class Categoria implements Identificable<Long> {
	private static final long serialVersionUID = -8800715225024553533L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@OneToMany(mappedBy = "categoria")
	private List<Programa> categorias = new ArrayList<>();
	
	public Categoria() {
		super();
		
	}

	public Categoria(String nombre, String descripcion, List<Programa> categorias) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categorias = categorias;
	}

	public Categoria(Long id, String nombre, String descripcion, List<Programa> categorias) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categorias = categorias;
	}

	public Categoria( String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Programa> getGeneros() {
		return categorias;
	}

	public void setGeneros(List<Programa> categorias) {
		this.categorias = categorias;
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
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Genero [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", categorias=" + categorias
				+ "]";
	}

}
