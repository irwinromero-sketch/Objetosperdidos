package mx.uam.cua.tysi.integracion.alumnos.repository;

import mx.uam.cua.tysi.integracion.alumnos.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}