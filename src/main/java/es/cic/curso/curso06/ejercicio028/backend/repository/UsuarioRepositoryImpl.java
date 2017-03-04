package es.cic.curso.curso06.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Categoria;
import es.cic.curso.curso06.ejercicio028.backend.dominio.Usuario;

@Repository
@Transactional
public class UsuarioRepositoryImpl extends AbstractRepositoryImpl<Long, Usuario> implements UsuarioRepository {

    @Override
    public Class<Usuario> getClassDeT() {
        return Usuario.class;
    }

    @Override
    public String getNombreTabla() {
        /**
         * Escribelo tal cual lo tienes en el changelog
         */
        return "USUARIO";
    }
}
