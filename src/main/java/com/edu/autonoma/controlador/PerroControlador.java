package com.edu.autonoma.controlador;

import com.edu.autonoma.modelo.Perro;
import com.edu.autonoma.modelo.Usuario;
import com.edu.autonoma.servicio.PerroServicio;
import com.edu.autonoma.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/perros")
public class PerroControlador {
    @Autowired
    private UsuarioServicio servicio;


    @Autowired
    private PerroServicio perroServicio;



    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("perro", new Perro());
        return "nuevoPerro";
    }

    @PostMapping("/guardar")
    public String guardarPerro(@ModelAttribute Perro perro, @RequestParam("imagen") MultipartFile imagen) {
        System.out.println("la foto es :" + imagen);
        Usuario usuario = servicio.obtenerUsuarioAutenticado();

        // Asignar el usuario al perro
        perro.setUsuario(usuario);

        if (!imagen.isEmpty()){
            Path directorioImagenes  = Paths.get("src//main//resources//static//css/fotos");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                //guardo el nombre en la bd
                perro.setFoto(imagen.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        perroServicio.guardar(perro);
        return "redirect:/?perroPerdidoGuardado=true";
    }

    @GetMapping("/listar")
    public String listarPerros(Model model) {
        List<Perro> perros = perroServicio.listarPerros();
        model.addAttribute("perros", perros);
        model.addAttribute("rutaImagenes", "/css/fotos");
        return "lista-perros";
    }

    @GetMapping("/listarPorUsuario")
    public String listarPerrosPorUsuario(Model model) {
        // Obtener el ID del usuario autenticado
        Usuario usuario = servicio.obtenerUsuarioAutenticado();
        // Obtener la lista de perros para el usuario autenticado
        List<Perro> perros = perroServicio.listarPerrosPorUsuarioId(usuario.getId());
        model.addAttribute("perros", perros);
        model.addAttribute("rutaImagenes", "/css/fotos");
        return "lista-perros-usuario";
    }


    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        // Obtiene el perro por su ID
        Optional<Perro> perro = perroServicio.obtenerPorId(id);
        model.addAttribute("rutaImagenes", "/css/fotos");
        // Verifica si el perro existe
        if (perro.isPresent()) {
            // Agrega el perro al modelo y muestra el formulario de edición
            model.addAttribute("perro", perro.get());

            return "editarPerro";
        } else {
            // Manejo del caso en que el perro no existe
            return "redirect:/listar?perroNoEncontrado=true";
        }


    }

    @PostMapping("/actualizar/{id}")
    public String actualizarPerro(@PathVariable Long id, @ModelAttribute Perro perro,
                                  @RequestParam("nuevaImagen") MultipartFile nuevaImagen) {
        // Lógica para actualizar el perro, similar a tu código existente
        perroServicio.actualizar(id, perro, nuevaImagen);


        return "redirect:/perros/listar?perroActualizado=true";
    }


    // Otros métodos, si los tienes
}
