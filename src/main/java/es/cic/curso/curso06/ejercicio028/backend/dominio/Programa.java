package es.cic.curso.curso06.ejercicio028.backend.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.cic.curso.curso06.ejercicio028.backend.repository.Identificable;

@Entity
@Table(name = "PROGRAMA")
public class Programa implements Identificable<Long> {
	private static final long serialVersionUID = -8760299749061904850L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "duracion")
	private int duracion;
	
	@Column(name = "anio")
	private int anio;

	@JoinColumn(name = "id_categoria")
	@ManyToOne(fetch = FetchType.EAGER)
	private Categoria categoria;

	@JoinColumn(name = "id_genero")
	@ManyToOne(fetch = FetchType.EAGER)
	private Genero genero;

	public Programa() {
		super();

	}

	public Programa(String nombre, int duracion, int anio, Categoria categoria, Genero genero) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
		this.anio = anio;
		this.categoria = categoria;
		this.genero = genero;
	}

	public Programa(Long id, String nombre, int duracion, int anio, Categoria categoria, Genero genero) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.anio = anio;
		this.categoria = categoria;
		this.genero = genero;
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

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
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
		Programa other = (Programa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	
}
