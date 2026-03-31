package mx.uam.cua.tysi.integracion.alumnos.service;

import mx.uam.cua.tysi.integracion.alumnos.dto.ObjetoDTO;
import java.util.List;

public interface ObjetoService {

    ObjetoDTO crear(ObjetoDTO objetoDTO);

    ObjetoDTO obtenerPorId(Long id);

    List<ObjetoDTO> obtenerTodos();

    List<ObjetoDTO> obtenerPorAlumno(Long alumnoId);

    ObjetoDTO actualizarEstado(Long id, String estado);

    ObjetoDTO actualizarDescripcion(Long id, String descripcion);

    ObjetoDTO actualizarImagen(Long id, String nombreImagen);

    void eliminar(Long id);
}