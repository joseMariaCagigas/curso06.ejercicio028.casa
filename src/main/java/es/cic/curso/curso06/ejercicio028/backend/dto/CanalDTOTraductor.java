package es.cic.curso.curso06.ejercicio028.backend.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Canal;

@Service
public class CanalDTOTraductor implements Traductor<Canal, CanalDTO> {

	@Override
	public CanalDTO traduceADTO(Canal entidad) {
		CanalDTO resultado = new CanalDTO();
		resultado.setId(entidad.getId());
		resultado.setNombre(entidad.getNombre());
		resultado.setTiempo_maximo(entidad.getTiempo_maximo());
		return resultado;
	}

	@Override
	public Canal traduceAEntidad(CanalDTO dto) {
		Canal resultado = new Canal();
		resultado.setId(dto.getId());
		resultado.setNombre(dto.getNombre());
		resultado.setTiempo_maximo(dto.getTiempo_maximo());
		return resultado;
	}

	@Override
	public List<CanalDTO> traduceAListaDTOs(List<Canal> entidades) {
		List<CanalDTO> resultado = new ArrayList<>();
		for (Canal enfermedad : entidades) {
			resultado.add(traduceADTO(enfermedad));
		}
		return resultado;
	}

	@Override
	public List<Canal> traduceAListaEntidades(List<CanalDTO> dtos) {
		List<Canal> resultado = new ArrayList<>();
		for (CanalDTO dto : dtos) {
			resultado.add(traduceAEntidad(dto));
		}
		return resultado;
	}

}
