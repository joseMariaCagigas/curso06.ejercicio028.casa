package es.cic.curso.curso06.ejercicio028.backend.repository;

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
}
