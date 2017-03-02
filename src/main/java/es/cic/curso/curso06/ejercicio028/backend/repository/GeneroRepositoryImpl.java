package es.cic.curso.curso06.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Genero;

@Repository
@Transactional
public class GeneroRepositoryImpl extends AbstractRepositoryImpl<Long, Genero> implements GeneroRepository {

    @Override
    public Class<Genero> getClassDeT() {
        return Genero.class;
    }

    @Override
    public String getNombreTabla() {
        /**
         * Escribelo tal cual lo tienes en el changelog
         */
        return "GENERO";
    }
}
