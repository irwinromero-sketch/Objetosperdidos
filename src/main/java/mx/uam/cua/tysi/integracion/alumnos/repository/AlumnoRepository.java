package mx.uam.cua.tysi.integracion.alumnos.repository;

import mx.uam.cua.tysi.integracion.alumnos.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}
