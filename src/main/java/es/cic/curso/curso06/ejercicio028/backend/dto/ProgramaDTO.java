package es.cic.curso.curso06.ejercicio028.backend.dto;

public class ProgramaDTO{

	private Long id;

	private String nombre;

	private int duracion;

	private int anio;

	public ProgramaDTO() {
		super();

	}

	public ProgramaDTO(String nombre, int duracion, int anio) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
		this.anio = anio;
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
		ProgramaDTO other = (ProgramaDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProgramaDTO [id=" + id + ", nombre=" + nombre + ", duracion=" + duracion + ", anio=" + anio + "]";
	}

}
