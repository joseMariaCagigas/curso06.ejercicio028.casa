package es.cic.curso.curso06.ejercicio028.backend.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Programa;

@Service
public class ProgramaDTOTraductor implements Traductor<Programa, ProgramaDTO> {

	@Override
	public ProgramaDTO traduceADTO(Programa entidad) {
		ProgramaDTO resultado = new ProgramaDTO();
		resultado.setId(entidad.getId());
		resultado.setNombre(entidad.getNombre());
		resultado.setDuracion(entidad.getDuracion());
		return resultado;
	}

	@Override
	public Programa traduceAEntidad(ProgramaDTO dto) {
		Programa resultado = new Programa();
		resultado.setId(dto.getId());
		resultado.setNombre(dto.getNombre());
		resultado.setDuracion(dto.getDuracion());
		return resultado;
	}

	@Override
	public List<ProgramaDTO> traduceAListaDTOs(List<Programa> entidades) {
		List<ProgramaDTO> resultado = new ArrayList<>();
		for (Programa enfermedad : entidades) {
			resultado.add(traduceADTO(enfermedad));
		}
		return resultado;
	}

	@Override
	public List<Programa> traduceAListaEntidades(List<ProgramaDTO> dtos) {
		List<Programa> resultado = new ArrayList<>();
		for (ProgramaDTO dto : dtos) {
			resultado.add(traduceAEntidad(dto));
		}
		return resultado;
	}

}
