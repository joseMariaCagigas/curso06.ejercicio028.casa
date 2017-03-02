package es.cic.curso.curso06.ejercicio028.backend.repository;

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
        /**
         * Escribelo tal cual lo tienes en el changelog
         */
        return "PROGRAMA";
    }
}
