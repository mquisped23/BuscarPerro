package com.edu.autonoma.servicio;

import com.edu.autonoma.modelo.Encontrado;
import com.edu.autonoma.modelo.Perro;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface EncontradoServicio {

    Encontrado guardar(Encontrado encontrado);
    void eliminarPorId(Long id);
    List<Encontrado> listarEncontrados();
    List<Encontrado> listarEncontradosPorUsuarioId(Long usuarioId);
    Optional<Encontrado> obtenerPorId(Long id);

    void actualizar(Long id, Encontrado encontrado, MultipartFile nuevaImagen);
}
