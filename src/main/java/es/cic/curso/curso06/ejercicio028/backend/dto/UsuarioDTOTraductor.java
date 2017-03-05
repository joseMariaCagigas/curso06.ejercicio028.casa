package es.cic.curso.curso06.ejercicio028.backend.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.cic.curso.curso06.ejercicio028.backend.dominio.Usuario;

@Service
public class UsuarioDTOTraductor implements Traductor<Usuario, UsuarioDTO> {

	@Override
	public UsuarioDTO traduceADTO(Usuario entidad) {
		UsuarioDTO resultado = new UsuarioDTO();
		resultado.setId(entidad.getId());
		resultado.setNombre(entidad.getNombre());
		resultado.setApellidos(entidad.getApellidos());
		return resultado;
	}

	@Override
	public Usuario traduceAEntidad(UsuarioDTO dto) {
		Usuario resultado = new Usuario();
		resultado.setId(dto.getId());
		resultado.setNombre(dto.getNombre());
		resultado.setApellidos(dto.getApellidos());
		return resultado;
	}

	@Override
	public List<UsuarioDTO> traduceAListaDTOs(List<Usuario> entidades) {
		List<UsuarioDTO> resultado = new ArrayList<>();
		for (Usuario enfermedad : entidades) {
			resultado.add(traduceADTO(enfermedad));
		}
		return resultado;
	}

	@Override
	public List<Usuario> traduceAListaEntidades(List<UsuarioDTO> dtos) {
		List<Usuario> resultado = new ArrayList<>();
		for (UsuarioDTO dto : dtos) {
			resultado.add(traduceAEntidad(dto));
		}
		return resultado;
	}

}
