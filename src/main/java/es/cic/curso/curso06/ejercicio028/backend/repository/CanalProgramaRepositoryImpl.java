package es.cic.curso.curso06.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Canal;
import es.cic.curso.curso06.ejercicio028.backend.dominio.CanalPrograma;

@Repository
@Transactional
public class CanalProgramaRepositoryImpl extends AbstractRepositoryImpl<Long, CanalPrograma> implements CanalProgramaRepository {

    @Override
    public Class<CanalPrograma> getClassDeT() {
        return CanalPrograma.class;
    }

    @Override
    public String getNombreTabla() {
        /**
         * Escribelo tal cual lo tienes en el changelog
         */
        return "CANAL_PROGRAMA";
    }
}
