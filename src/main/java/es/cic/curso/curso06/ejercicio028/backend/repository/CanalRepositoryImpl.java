package es.cic.curso.curso06.ejercicio028.backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Canal;

@Repository
@Transactional
public class CanalRepositoryImpl extends AbstractRepositoryImpl<Long, Canal> implements CanalRepository {

    @Override
    public Class<Canal> getClassDeT() {
        return Canal.class;
    }

    @Override
    public String getNombreTabla() {
        /**
         * Escribelo tal cual lo tienes en el changelog
         */
        return "CANAL";
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Canal> listyByGenreUsuario(Long idUsuario) {
		List<Canal> resultado;
		try {
			resultado = entityManager.createNativeQuery("SELECT * FROM CANAL WHERE id_Usuario = ?", getClassDeT())
					.setParameter(1, idUsuario).getResultList();
		} catch (Exception e) {
			return null;
		}
		return resultado;
	}
}
