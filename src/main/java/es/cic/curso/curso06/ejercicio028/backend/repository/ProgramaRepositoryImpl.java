package es.cic.curso.curso06.ejercicio028.backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Programa;

@Repository
@Transactional
public class ProgramaRepositoryImpl extends AbstractRepositoryImpl<Long, Programa> implements ProgramaRepository {

	@Override
	public Class<Programa> getClassDeT() {
		return Programa.class;
	}

	@Override
	public String getNombreTabla() {
		/*
		 * Escribelo tal cual lo tienes en el changelog
		 */
		return "PROGRAMA";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Programa> listyByGenre(Long idGenero) {
		List<Programa> resultado;
		try {
			resultado = entityManager.createNativeQuery("SELECT * FROM PROGRAMA WHERE id_genero = ?", getClassDeT())
					.setParameter(1, idGenero).getResultList();
		} catch (Exception e) {
			return null;
		}
		return resultado;
	}

}
