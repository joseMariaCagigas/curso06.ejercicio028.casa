package es.cic.curso.curso06.ejercicio028.backend.repository;

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
}
