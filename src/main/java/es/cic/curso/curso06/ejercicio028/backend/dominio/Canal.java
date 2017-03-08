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
@Table(name = "CANAL")
public class Canal implements Identificable<Long> {
	
	private static final long serialVersionUID = -8760299749061904850L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "tiempo_maximo")
	private int tiempoMaximo;
	
	@JoinColumn(name = "id_usuario")
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;
	
	
	
	public Canal() {
		super();
	}

	public Canal(String nombre, int tiempo_maximo, Usuario usuario) {
		super();
		this.nombre = nombre;
		this.tiempoMaximo = tiempo_maximo;
		this.usuario = usuario;
	}

	public Canal(Long id, String nombre, int tiempo_maximo, Usuario usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tiempoMaximo = tiempo_maximo;
		this.usuario = usuario;
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

	public int getTiempo_maximo() {
		return tiempoMaximo;
	}

	public void setTiempo_maximo(int tiempo_maximo) {
		this.tiempoMaximo = tiempo_maximo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Canal other = (Canal) obj;
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
