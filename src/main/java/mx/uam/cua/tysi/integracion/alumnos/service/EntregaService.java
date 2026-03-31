package mx.uam.cua.tysi.integracion.alumnos.service;

import mx.uam.cua.tysi.integracion.alumnos.dto.EntregaDTO;
import java.util.List;

public interface EntregaService {

    EntregaDTO crear(EntregaDTO entregaDTO);

    EntregaDTO obtenerPorId(Long id);

    List<EntregaDTO> obtenerTodos();

    List<EntregaDTO> obtenerPorAlumnoReporta(Long alumnoId);

    List<EntregaDTO> obtenerPorAlumnoRecibe(Long alumnoId);

    void eliminar(Long id);
}