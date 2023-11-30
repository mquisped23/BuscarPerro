package com.edu.autonoma.repositorio;

import com.edu.autonoma.modelo.Encontrado;
import com.edu.autonoma.modelo.Perro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncontradoRepositorio extends JpaRepository<Encontrado, Long> {
    List<Encontrado> findByUsuarioId(Long usuarioId);
}
