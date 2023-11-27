package com.edu.autonoma.servicio;

import com.edu.autonoma.modelo.Perro;
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
public class PerroServicioImpl implements PerroServicio {

    private final PerroRepositorio perroRepositorio;

    @Autowired
    public PerroServicioImpl(PerroRepositorio perroRepositorio) {
        this.perroRepositorio = perroRepositorio;
    }

    @Override
    public Perro guardar(Perro perro) {
        return perroRepositorio.save(perro);
    }

    @Override
    public List<Perro> listarPerros() {
        return perroRepositorio.findAll();
    }

    @Override
    public List<Perro> listarPerrosPorUsuarioId(Long usuarioId) {
        return perroRepositorio.findByUsuarioId(usuarioId);
    }
    @Override
    public Optional<Perro> obtenerPorId(Long id) {
        return perroRepositorio.findById(id);
    }

    @Override
    public void actualizar(Long id, Perro perro, MultipartFile nuevaImagen) {
        Optional<Perro> perroExistente = obtenerPorId(id);

        if (perroExistente.isPresent()) {
            Perro perroActualizado = perroExistente.get();

            // Actualiza los campos del perro con los nuevos valores
            perroActualizado.setNombre(perro.getNombre());
            perroActualizado.setDiaPerdido(perro.getDiaPerdido());
            perroActualizado.setUbicacion(perro.getUbicacion());
            perroActualizado.setSexo(perro.getSexo());
            perroActualizado.setEdad(perro.getEdad());
            perroActualizado.setTamano(perro.getTamano());
            perroActualizado.setRaza(perro.getRaza());

            // Si se proporciona una nueva imagen, actualiza la foto
            if (nuevaImagen != null && !nuevaImagen.isEmpty()) {
                try {
                    byte[] bytesImg = nuevaImagen.getBytes();
                    String nombreImagen = nuevaImagen.getOriginalFilename();
                    // Guarda la imagen y actualiza el nombre en la entidad
                    guardarImagen(nombreImagen, bytesImg);
                    perroActualizado.setFoto(nombreImagen);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Guarda el perro actualizado en el repositorio
            perroRepositorio.save(perroActualizado);
        }
    }

    // Método para guardar la imagen en el directorio de imágenes
    private void guardarImagen(String nombreImagen, byte[] bytes) {
        Path directorioImagenes = Paths.get("src//main//resources//static//css/fotos");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

        try {
            Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nombreImagen);
            Files.write(rutaCompleta, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}