package com.edu.autonoma.repositorio;

import com.edu.autonoma.modelo.Perro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerroRepositorio extends JpaRepository<Perro, Long> {
    // Puedes agregar métodos personalizados de consulta si es necesario
    List<Perro> findByUsuarioId(Long usuarioId);
}