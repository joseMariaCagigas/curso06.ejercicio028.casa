package es.cic.curso.curso06.ejercicio028.backend.dto;

public class CanalDTO{

	private Long id;

	private String nombre;

	private int tiempo_maximo;
	
	
	public CanalDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CanalDTO(String nombre, int tiempo_maximo) {
		super();
		this.nombre = nombre;
		this.tiempo_maximo = tiempo_maximo;

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

	public int getTiempo_maximo() {
		return tiempo_maximo;
	}

	public void setTiempo_maximo(int tiempo_maximo) {
		this.tiempo_maximo = tiempo_maximo;
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
		CanalDTO other = (CanalDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CanalDTO [id=" + id + ", nombre=" + nombre + ", tiempo_maximo=" + tiempo_maximo + "]";
	}

}
