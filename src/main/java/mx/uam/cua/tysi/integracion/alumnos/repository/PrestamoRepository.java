package mx.uam.cua.tysi.integracion.alumnos.repository;

import mx.uam.cua.tysi.integracion.alumnos.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    List<Prestamo> findByAlumnoId(Long alumnoId);
}