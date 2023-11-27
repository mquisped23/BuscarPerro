package com.edu.autonoma.servicio;



import com.edu.autonoma.modelo.Perro;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PerroServicio {

    Perro guardar(Perro perro);

    List<Perro> listarPerros();
    List<Perro> listarPerrosPorUsuarioId(Long usuarioId);
    Optional<Perro> obtenerPorId(Long id);

    void actualizar(Long id, Perro perro, MultipartFile nuevaImagen);
}
