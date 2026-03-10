package mx.uam.cua.tysi.integracion.alumnos.service;

import mx.uam.cua.tysi.integracion.alumnos.dto.ObjetoDTO;
import java.util.List;

public interface ObjetoService {

    ObjetoDTO crear(ObjetoDTO objetoDTO);

    List<ObjetoDTO> obtenerTodos();

    List<ObjetoDTO> obtenerPorAlumno(Long alumnoId);

    ObjetoDTO actualizarEstado(Long id, String estado);

    void eliminar(Long id);
}