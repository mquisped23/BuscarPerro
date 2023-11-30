package com.edu.autonoma.controlador;


import com.edu.autonoma.modelo.Usuario;
import com.edu.autonoma.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RegistroControlador {

	@Autowired
	private UsuarioServicio servicio;
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
	@GetMapping("/")
	public String verPaginaDeInicio( Model model, @RequestParam(name = "perroPerdidoGuardado", defaultValue = "false") boolean perroPerdidoGuardado,@RequestParam(name = "perroEncontradoGuardado", defaultValue = "false") boolean perroEncontradoGuardado) {
		Usuario usuario = servicio.obtenerUsuarioAutenticado();

		if (usuario != null) {
			// Ahora puedes acceder al ID del usuario y pasarlo al modelo
			Long userId = usuario.getId();
			String nombre = usuario.getNombre();
			model.addAttribute("userId", userId);
			model.addAttribute("nombre", nombre);
			model.addAttribute("perroPerdidoGuardado", perroPerdidoGuardado);
			model.addAttribute("perroEncontradoGuardado", perroEncontradoGuardado);
		}
		return "index";
	}
}
