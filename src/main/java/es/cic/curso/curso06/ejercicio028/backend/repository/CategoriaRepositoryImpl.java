package es.cic.curso.curso06.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Categoria;

@Repository
@Transactional
public class CategoriaRepositoryImpl extends AbstractRepositoryImpl<Long, Categoria> implements CategoriaRepository {

    @Override
    public Class<Categoria> getClassDeT() {
        return Categoria.class;
    }

    @Override
    public String getNombreTabla() {
        /**
         * Escribelo tal cual lo tienes en el changelog
         */
        return "CATEGORIA";
    }
}
