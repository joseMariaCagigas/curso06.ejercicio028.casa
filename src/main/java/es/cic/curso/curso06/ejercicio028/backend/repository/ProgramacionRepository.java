package es.cic.curso.curso06.ejercicio028.backend.repository;

import java.util.List;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Programacion;


public interface ProgramacionRepository extends IRepository<Long, Programacion> {

	List<Programacion> deleteByDisease(Long idDisease);

	List<Programacion> listByDisease(Long idDisease);
}
