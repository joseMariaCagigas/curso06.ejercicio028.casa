package es.cic.curso.curso06.ejercicio028.backend.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Genero;

@Service
public class GeneroDTOTraductor implements Traductor<Genero, GeneroDTO> {

	@Override
	public GeneroDTO traduceADTO(Genero entidad) {
		GeneroDTO resultado = new GeneroDTO();
		resultado.setId(entidad.getId());
		resultado.setNombre(entidad.getNombre());
		resultado.setDescripcion(entidad.getDescripcion());
		return resultado;
	}

	@Override
	public Genero traduceAEntidad(GeneroDTO dto) {
		Genero resultado = new Genero();
		resultado.setId(dto.getId());
		resultado.setNombre(dto.getNombre());
		resultado.setDescripcion(dto.getDescripcion());
		return resultado;
	}

	@Override
	public List<GeneroDTO> traduceAListaDTOs(List<Genero> entidades) {
		List<GeneroDTO> resultado = new ArrayList<>();
		for (Genero enfermedad : entidades) {
			resultado.add(traduceADTO(enfermedad));
		}
		return resultado;
	}

	@Override
	public List<Genero> traduceAListaEntidades(List<GeneroDTO> dtos) {
		List<Genero> resultado = new ArrayList<>();
		for (GeneroDTO dto : dtos) {
			resultado.add(traduceAEntidad(dto));
		}
		return resultado;
	}

}
