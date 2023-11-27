package com.edu.autonoma.servicio;


import com.edu.autonoma.controlador.dto.UsuarioRegistroDTO;
import com.edu.autonoma.modelo.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;




public interface UsuarioServicio extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);

	public List<Usuario> listarUsuarios();
	public Usuario obtenerUsuarioAutenticado();

}
