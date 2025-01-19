package maru.challenge3.liter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import maru.challenge3.liter.model.Autor;
import maru.challenge3.liter.model.Datos;
import maru.challenge3.liter.model.Libro;


public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT d FROM Datos d WHERE LOWER(d.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    Optional<Datos> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT d FROM Datos d WHERE LOWER(d.idioma) LIKE LOWER(CONCAT('%', :idioma, '%'))")
    List<Datos> findByIdiomaContainingIgnoreCase(String idioma);

    @Query("SELECT a FROM Autor a WHERE a.anoNacimiento <= :anoNacimiento AND (a.anoFallecimiento >= :anoFallecimiento OR a.anoFallecimiento IS NULL)")
    List<Autor> findByAnoNacimientoAndAnoFallecimiento(int anoNacimiento, int anoFallecimiento);
}