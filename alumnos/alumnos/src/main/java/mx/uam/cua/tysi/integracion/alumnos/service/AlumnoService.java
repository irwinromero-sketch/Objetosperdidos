package mx.uam.cua.tysi.integracion.alumnos.service;

import mx.uam.cua.tysi.integracion.alumnos.dto.AlumnoDTO;
import java.util.List;

public interface AlumnoService {

    AlumnoDTO crearAlumno(AlumnoDTO alumnoDTO);

    List<AlumnoDTO> obtenerTodos();

    AlumnoDTO obtenerPorId(Long id);

    AlumnoDTO actualizarAlumno(Long id, AlumnoDTO alumnoDTO);

    void eliminarAlumno(Long id);
}
