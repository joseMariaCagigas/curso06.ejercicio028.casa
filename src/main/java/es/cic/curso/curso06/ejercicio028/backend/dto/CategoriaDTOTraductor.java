package es.cic.curso.curso06.ejercicio028.backend.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Categoria;

@Service
public class CategoriaDTOTraductor implements Traductor<Categoria, CategoriaDTO> {

	@Override
	public CategoriaDTO traduceADTO(Categoria entidad) {
		CategoriaDTO resultado = new CategoriaDTO();
		resultado.setId(entidad.getId());
		resultado.setNombre(entidad.getNombre());
		resultado.setDescripcion(entidad.getDescripcion());
		return resultado;
	}

	@Override
	public Categoria traduceAEntidad(CategoriaDTO dto) {
		Categoria resultado = new Categoria();
		resultado.setId(dto.getId());
		resultado.setNombre(dto.getNombre());
		resultado.setDescripcion(dto.getDescripcion());
		return resultado;
	}

	@Override
	public List<CategoriaDTO> traduceAListaDTOs(List<Categoria> entidades) {
		List<CategoriaDTO> resultado = new ArrayList<>();
		for (Categoria enfermedad : entidades) {
			resultado.add(traduceADTO(enfermedad));
		}
		return resultado;
	}

	@Override
	public List<Categoria> traduceAListaEntidades(List<CategoriaDTO> dtos) {
		List<Categoria> resultado = new ArrayList<>();
		for (CategoriaDTO dto : dtos) {
			resultado.add(traduceAEntidad(dto));
		}
		return resultado;
	}

}
