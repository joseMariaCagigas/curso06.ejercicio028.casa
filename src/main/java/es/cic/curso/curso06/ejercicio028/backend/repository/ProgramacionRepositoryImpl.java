package es.cic.curso.curso06.ejercicio028.backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Programacion;

@Repository
@Transactional
public class ProgramacionRepositoryImpl extends AbstractRepositoryImpl<Long, Programacion> implements ProgramacionRepository {

    @Override
    public Class<Programacion> getClassDeT() {
        return Programacion.class;
    }

    @Override
    public String getNombreTabla() {
        /**
         * Escribelo tal cual lo tienes en el changelog
         */
        return "PROGRAMACION";
    }

	@Override
	public List<Programacion> deleteByDisease(Long idDisease) {

		List<Programacion> entradas = listByDisease(idDisease);
		entityManager.createNativeQuery("DELETE FROM PROGRAMACION WHERE id_canal = ?").setParameter(1,  idDisease).executeUpdate();
		return entradas;
	}

	@Override
	public List<Programacion> listByDisease(Long idDisease) {

		List<Programacion> resultado;
		try {
			resultado = entityManager
					.createNativeQuery("SELECT * FROM PROGRAMACION WHERE id_programas = ?", getClassDeT())
					.setParameter(1, idDisease).getResultList();
		} catch (Exception e) {
			return null;
		}
		return resultado;
	}
}
