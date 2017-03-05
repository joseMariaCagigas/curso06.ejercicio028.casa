package es.cic.curso.curso06.ejercicio028.backend.dominio;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.cic.curso.curso06.ejercicio028.backend.repository.Identificable;

@Entity
@Table(name = "CANAL_PROGRAMA")
public class Programacion implements Identificable<Long> {
	private static final long serialVersionUID = -1834404426900148883L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	@JoinColumn(name = "id_canal")
	@OneToOne(fetch = FetchType.LAZY)
	private Canal canal;

	@JoinColumn(name = "id_programa")
	@OneToOne(fetch = FetchType.LAZY)
	private Programa programa;
	
	

	public Programacion() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Programacion(Canal canal, Programa programa) {
		super();
		this.canal = canal;
		this.programa = programa;
	}


	public Programacion(Long id, Canal canal, Programa programa) {
		super();
		this.id = id;
		this.canal = canal;
		this.programa = programa;
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


	public Canal getCanal() {
		return canal;
	}


	public void setCanal(Canal canal) {
		this.canal = canal;
	}


	public Programa getPrograma() {
		return programa;
	}


	public void setPrograma(Programa programa) {
		this.programa = programa;
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
		Programacion other = (Programacion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "CanalPrograma [id=" + id + ", canal=" + canal.getId() + ", programa=" + programa.getId() + "]";
	}
}