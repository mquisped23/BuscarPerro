package com.edu.autonoma.servicio;

import com.edu.autonoma.modelo.Encontrado;
import com.edu.autonoma.modelo.Perro;
import com.edu.autonoma.repositorio.EncontradoRepositorio;
import com.edu.autonoma.repositorio.PerroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class EncontradoServicioImpl  implements EncontradoServicio{

    private final EncontradoRepositorio encontradoRepositorio;

    @Autowired
    public EncontradoServicioImpl(EncontradoRepositorio encontradoRepositorio) {
        this.encontradoRepositorio = encontradoRepositorio;
    }



    @Override
    public Encontrado guardar(Encontrado encontrado) {
        return encontradoRepositorio.save(encontrado);
    }

    @Override
    public void eliminarPorId(Long id) {
        encontradoRepositorio.deleteById(id);
    }

    @Override
    public List<Encontrado> listarEncontrados() {
        return encontradoRepositorio.findAll();
    }

    @Override
    public List<Encontrado> listarEncontradosPorUsuarioId(Long usuarioId) {
        return encontradoRepositorio.findByUsuarioId(usuarioId);
    }
    @Override
    public Optional<Encontrado> obtenerPorId(Long id) {
        return encontradoRepositorio.findById(id);
    }

    @Override
    public void actualizar(Long id, Encontrado encontrado, MultipartFile nuevaImagen) {
        Optional<Encontrado> perroExistente = obtenerPorId(id);

        if (perroExistente.isPresent()) {
            Encontrado encontradoActualizado = perroExistente.get();

            // Actualiza los campos del perro con los nuevos valores
            encontradoActualizado.setNombre(encontrado.getNombre());
            encontradoActualizado.setDiaEncontrado(encontrado.getDiaEncontrado());
            encontradoActualizado.setUbicacion(encontrado.getUbicacion());
            encontradoActualizado.setSexo(encontrado.getSexo());
            encontradoActualizado.setContacto(encontrado.getContacto());


            // Si se proporciona una nueva imagen, actualiza la foto
            if (nuevaImagen != null && !nuevaImagen.isEmpty()) {
                try {
                    byte[] bytesImg = nuevaImagen.getBytes();
                    String nombreImagen = nuevaImagen.getOriginalFilename();
                    // Guarda la imagen y actualiza el nombre en la entidad
                    guardarImagen(nombreImagen, bytesImg);
                    encontradoActualizado.setFoto(nombreImagen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Guarda el perro actualizado en el repositorio
            encontradoRepositorio.save(encontradoActualizado);
        }
    }

    // Método para guardar la imagen en el directorio de imágenes
    private void guardarImagen(String nombreImagen, byte[] bytes) {
        Path directorioImagenes = Paths.get("src//main//resources//static//css/fotosEncontrados");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

        try {
            Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nombreImagen);
            Files.write(rutaCompleta, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
