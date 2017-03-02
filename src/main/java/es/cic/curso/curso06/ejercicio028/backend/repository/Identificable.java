package es.cic.curso.curso06.ejercicio028.backend.repository;

import java.io.Serializable;

public interface Identificable<K> extends Serializable {

	K getId();

	void setId(K id);

}