package mx.uam.cua.tysi.integracion.alumnos.service;

import mx.uam.cua.tysi.integracion.alumnos.dto.AlumnoDTO;
import mx.uam.cua.tysi.integracion.alumnos.entity.Alumno;
import mx.uam.cua.tysi.integracion.alumnos.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public AlumnoDTO crearAlumno(AlumnoDTO dto) {

        Alumno alumno = convertirAEntidad(dto);
        alumno.setPassword("12345");

        Alumno guardado = alumnoRepository.save(alumno);

        return convertirADTO(guardado);
    }

    @Override
    public List<AlumnoDTO> obtenerTodos() {
        return alumnoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlumnoDTO obtenerPorId(Long id) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        return convertirADTO(alumno);
    }

    @Override
    public AlumnoDTO actualizarAlumno(Long id, AlumnoDTO dto) {

        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        if (dto.getNombre() != null) alumno.setNombre(dto.getNombre());
        if (dto.getApellido() != null) alumno.setApellido(dto.getApellido());
        if (dto.getCarrera() != null) alumno.setCarrera(dto.getCarrera());
        if (dto.getPromedio() != null) alumno.setPromedio(dto.getPromedio());
        if (dto.getEmail() != null) alumno.setEmail(dto.getEmail());

        Alumno actualizado = alumnoRepository.save(alumno);

        return convertirADTO(actualizado);
    }

    @Override
    public void eliminarAlumno(Long id) {

        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        alumnoRepository.delete(alumno);
    }

    private Alumno convertirAEntidad(AlumnoDTO dto) {
        Alumno alumno = new Alumno();
        alumno.setNombre(dto.getNombre());
        alumno.setApellido(dto.getApellido());
        alumno.setCarrera(dto.getCarrera());
        alumno.setPromedio(dto.getPromedio());
        alumno.setEmail(dto.getEmail());
        return alumno;
    }

    private AlumnoDTO convertirADTO(Alumno alumno) {
        AlumnoDTO dto = new AlumnoDTO();
        dto.setId(alumno.getId());
        dto.setNombre(alumno.getNombre());
        dto.setApellido(alumno.getApellido());
        dto.setCarrera(alumno.getCarrera());
        dto.setPromedio(alumno.getPromedio());
        dto.setEmail(alumno.getEmail());
        return dto;
    }
}
