package es.cic.curso.curso06.ejercicio028.backend.dto;

public class CategoriaDTO{

	private Long id;

	private String nombre;

	private String descripcion;

	
	public CategoriaDTO() {
		super();
		
	}

	public CategoriaDTO(String nombre, String descripcion) {
		super();
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



	/**
	 * @return the id
	 */

	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */

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
		CategoriaDTO other = (CategoriaDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategoriaDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}



}
