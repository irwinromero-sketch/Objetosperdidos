package mx.uam.cua.tysi.integracion.alumnos.repository;

import mx.uam.cua.tysi.integracion.alumnos.entity.Objeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ObjetoRepository extends JpaRepository<Objeto, Long> {

    List<Objeto> findByAlumnoId(Long alumnoId);
}