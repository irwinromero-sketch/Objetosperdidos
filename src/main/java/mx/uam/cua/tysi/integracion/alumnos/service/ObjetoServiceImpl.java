package mx.uam.cua.tysi.integracion.alumnos.service;

import mx.uam.cua.tysi.integracion.alumnos.dto.ObjetoDTO;
import mx.uam.cua.tysi.integracion.alumnos.entity.Alumno;
import mx.uam.cua.tysi.integracion.alumnos.entity.Objeto;
import mx.uam.cua.tysi.integracion.alumnos.mapper.AlumnoMapper;
import mx.uam.cua.tysi.integracion.alumnos.repository.AlumnoRepository;
import mx.uam.cua.tysi.integracion.alumnos.repository.ObjetoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObjetoServiceImpl implements ObjetoService {

    private final ObjetoRepository objetoRepository;
    private final AlumnoRepository alumnoRepository;
    private final AlumnoMapper alumnoMapper;

    public ObjetoServiceImpl(ObjetoRepository objetoRepository,
                             AlumnoRepository alumnoRepository,
                             AlumnoMapper alumnoMapper) {
        this.objetoRepository = objetoRepository;
        this.alumnoRepository = alumnoRepository;
        this.alumnoMapper = alumnoMapper;
    }

    @Override
    public ObjetoDTO crear(ObjetoDTO objetoDTO) {
        Alumno alumno = alumnoRepository.findById(objetoDTO.getAlumno().getId())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        Objeto objeto = new Objeto();
        objeto.setDescripcion(objetoDTO.getDescripcion());
        objeto.setFecha(LocalDate.now());
        objeto.setEstado("PERDIDO");
        objeto.setAlumno(alumno);

        objeto = objetoRepository.save(objeto);

        return convertirADTO(objeto);
    }

    @Override
    public ObjetoDTO obtenerPorId(Long id) {
        Objeto objeto = objetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado"));
        return convertirADTO(objeto);
    }

    @Override
    public List<ObjetoDTO> obtenerTodos() {
        List<Objeto> objetos = objetoRepository.findAll();
        List<ObjetoDTO> dtos = new ArrayList<>();
        for (Objeto objeto : objetos) {
            dtos.add(convertirADTO(objeto));
        }
        return dtos;
    }

    @Override
    public List<ObjetoDTO> obtenerPorAlumno(Long alumnoId) {
        List<Objeto> objetos = objetoRepository.findByAlumnoId(alumnoId);
        List<ObjetoDTO> dtos = new ArrayList<>();
        for (Objeto objeto : objetos) {
            dtos.add(convertirADTO(objeto));
        }
        return dtos;
    }

    @Override
    public ObjetoDTO actualizarEstado(Long id, String estado) {
        Objeto objeto = objetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado"));

        objeto.setEstado(estado);
        objeto = objetoRepository.save(objeto);

        return convertirADTO(objeto);
    }

    @Override
    public ObjetoDTO actualizarDescripcion(Long id, String descripcion) {
        Objeto objeto = objetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado"));

        objeto.setDescripcion(descripcion);
        objeto = objetoRepository.save(objeto);

        return convertirADTO(objeto);
    }

    @Override
    public ObjetoDTO actualizarImagen(Long id, String nombreImagen) {
        Objeto objeto = objetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado"));

        objeto.setImagen(nombreImagen);
        objeto = objetoRepository.save(objeto);

        return convertirADTO(objeto);
    }

    @Override
    public void eliminar(Long id) {
        Objeto objeto = objetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado"));
        objetoRepository.delete(objeto);
    }

    private ObjetoDTO convertirADTO(Objeto objeto) {
        ObjetoDTO dto = new ObjetoDTO();
        dto.setId(objeto.getId());
        dto.setDescripcion(objeto.getDescripcion());
        dto.setFecha(objeto.getFecha());
        dto.setEstado(objeto.getEstado());
        if (objeto.getImagen() != null) {
            dto.setImagenUrl("http://localhost:8080/imagenes/" + objeto.getImagen());
        }
        dto.setAlumno(alumnoMapper.alumnoToAlumnoDTO(objeto.getAlumno()));
        return dto;
    }
}