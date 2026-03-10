package mx.uam.cua.tysi.integracion.alumnos.repository;

import mx.uam.cua.tysi.integracion.alumnos.entity.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    List<Entrega> findByAlumnoReportaId(Long alumnoId);
    List<Entrega> findByAlumnoRecibeId(Long alumnoId);
}