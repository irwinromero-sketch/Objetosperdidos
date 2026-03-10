package mx.uam.cua.tysi.integracion.alumnos.service;

import mx.uam.cua.tysi.integracion.alumnos.dto.PrestamoDTO;
import java.util.List;

public interface PrestamoService {

    PrestamoDTO create(PrestamoDTO prestamoDTO);

    List<PrestamoDTO> findAll();

    List<PrestamoDTO> obtenerPrestamosPorAlumno(Long alumnoId);
}