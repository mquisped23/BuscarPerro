package com.edu.autonoma.controlador;

import com.edu.autonoma.modelo.Encontrado;
import com.edu.autonoma.modelo.Perro;
import com.edu.autonoma.modelo.Usuario;
import com.edu.autonoma.servicio.EncontradoServicio;
import com.edu.autonoma.servicio.PerroServicio;
import com.edu.autonoma.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/encontrado")

public class EncontradoControlador {

    @Autowired
    private UsuarioServicio servicio;


    @Autowired
    private EncontradoServicio encontradoServicio;



    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("perro", new Encontrado());
        return "nuevoEncontrado";
    }

    @PostMapping("/guardar")
    public String guardarPerro(@ModelAttribute Encontrado encontrado, @RequestParam("imagen") MultipartFile imagen) {
        System.out.println("la foto es :" + imagen);
        Usuario usuario = servicio.obtenerUsuarioAutenticado();

        // Asignar el usuario al perro
        encontrado.setUsuario(usuario);

        if (!imagen.isEmpty()){
            Path directorioImagenes  = Paths.get("src//main//resources//static//css/fotosEncontrados");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                //guardo el nombre en la bd
                encontrado.setFoto(imagen.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        encontradoServicio.guardar(encontrado);
        return "redirect:/?perroEncontradoGuardado=true";
    }

    @GetMapping("/listar")
    public String listarPerrosEncontrado(Model model) {
        List<Encontrado> encontrados = encontradoServicio.listarEncontrados();
        model.addAttribute("perros", encontrados);
        model.addAttribute("rutaImagenes", "/css/fotosEncontrados");
        return "lista-perrosEncontrados";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPerroEncontrado(@PathVariable Long id) {
        encontradoServicio.eliminarPorId(id);
        return "redirect:/encontrado/listarPorUsuario";
    }


    @GetMapping("/listarPorUsuario")
    public String listarPerrosEncontradosPorUsuario(Model model) {
        // Obtener el ID del usuario autenticado
        Usuario usuario = servicio.obtenerUsuarioAutenticado();
        // Obtener la lista de perros para el usuario autenticado
        List<Encontrado> perros = encontradoServicio.listarEncontradosPorUsuarioId(usuario.getId());
        model.addAttribute("perros", perros);
        model.addAttribute("rutaImagenes", "/css/fotosEncontrados");
        return "lista-perros-encontrados-usuario";
    }


    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicionEncontrados(@PathVariable Long id, Model model) {
        // Obtiene el perro por su ID
        Optional<Encontrado> perro = encontradoServicio.obtenerPorId(id);
        model.addAttribute("rutaImagenes", "/css/fotosEncontrados");
        // Verifica si el perro existe
        if (perro.isPresent()) {
            // Agrega el perro al modelo y muestra el formulario de edición
            model.addAttribute("perro", perro.get());

            return "editarPerroEncontrado";
        } else {
            // Manejo del caso en que el perro no existe
            return "redirect:/listar?perroNoEncontrado=true";
        }


    }

    @PostMapping("/actualizar/{id}")
    public String actualizarPerroEncontrado(@PathVariable Long id, @ModelAttribute Encontrado perro,
                                  @RequestParam("nuevaImagen") MultipartFile nuevaImagen) {
        // Lógica para actualizar el perro, similar a tu código existente
        encontradoServicio.actualizar(id, perro, nuevaImagen);


        return "redirect:/encontrado/listar?perroActualizado=true";
    }
}
