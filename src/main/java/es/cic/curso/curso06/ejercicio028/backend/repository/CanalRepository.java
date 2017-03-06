package es.cic.curso.curso06.ejercicio028.backend.repository;

import java.util.List;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Canal;
;

public interface CanalRepository extends IRepository<Long, Canal> {

	List<Canal> listyByGenreUsuario(Long idUsuario);


}
