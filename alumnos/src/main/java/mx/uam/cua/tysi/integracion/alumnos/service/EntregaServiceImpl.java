package mx.uam.cua.tysi.integracion.alumnos.service;

import mx.uam.cua.tysi.integracion.alumnos.dto.EntregaDTO;
import mx.uam.cua.tysi.integracion.alumnos.entity.Alumno;
import mx.uam.cua.tysi.integracion.alumnos.entity.Entrega;
import mx.uam.cua.tysi.integracion.alumnos.entity.Objeto;
import mx.uam.cua.tysi.integracion.alumnos.mapper.AlumnoMapper;
import mx.uam.cua.tysi.integracion.alumnos.mapper.ObjetoMapper;
import mx.uam.cua.tysi.integracion.alumnos.repository.AlumnoRepository;
import mx.uam.cua.tysi.integracion.alumnos.repository.EntregaRepository;
import mx.uam.cua.tysi.integracion.alumnos.repository.ObjetoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EntregaServiceImpl implements EntregaService {

    private final EntregaRepository entregaRepository;
    private final ObjetoRepository objetoRepository;
    private final AlumnoRepository alumnoRepository;
    private final AlumnoMapper alumnoMapper;
    private final ObjetoMapper objetoMapper;

    public EntregaServiceImpl(EntregaRepository entregaRepository,
                              ObjetoRepository objetoRepository,
                              AlumnoRepository alumnoRepository,
                              AlumnoMapper alumnoMapper,
                              ObjetoMapper objetoMapper) {
        this.entregaRepository = entregaRepository;
        this.objetoRepository = objetoRepository;
        this.alumnoRepository = alumnoRepository;
        this.alumnoMapper = alumnoMapper;
        this.objetoMapper = objetoMapper;
    }

    @Override
    public EntregaDTO crear(EntregaDTO entregaDTO) {
        Objeto objeto = objetoRepository.findById(entregaDTO.getObjeto().getId())
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado"));

        Alumno alumnoReporta = alumnoRepository.findById(entregaDTO.getAlumnoReporta().getId())
                .orElseThrow(() -> new RuntimeException("Alumno que reporta no encontrado"));

        Alumno alumnoRecibe = alumnoRepository.findById(entregaDTO.getAlumnoRecibe().getId())
                .orElseThrow(() -> new RuntimeException("Alumno que recibe no encontrado"));

        objeto.setEstado("ENCONTRADO");
        objetoRepository.save(objeto);

        Entrega entrega = new Entrega();
        entrega.setFechaEntrega(LocalDate.now());
        entrega.setObjeto(objeto);
        entrega.setAlumnoReporta(alumnoReporta);
        entrega.setAlumnoRecibe(alumnoRecibe);

        entrega = entregaRepository.save(entrega);

        return convertirADTO(entrega);
    }

    @Override
    public EntregaDTO obtenerPorId(Long id) {
        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada"));
        return convertirADTO(entrega);
    }

    @Override
    public List<EntregaDTO> obtenerTodos() {
        List<Entrega> entregas = entregaRepository.findAll();
        List<EntregaDTO> dtos = new ArrayList<>();
        for (Entrega entrega : entregas) {
            dtos.add(convertirADTO(entrega));
        }
        return dtos;
    }

    @Override
    public List<EntregaDTO> obtenerPorAlumnoReporta(Long alumnoId) {
        List<Entrega> entregas = entregaRepository.findByAlumnoReportaId(alumnoId);
        List<EntregaDTO> dtos = new ArrayList<>();
        for (Entrega entrega : entregas) {
            dtos.add(convertirADTO(entrega));
        }
        return dtos;
    }

    @Override
    public List<EntregaDTO> obtenerPorAlumnoRecibe(Long alumnoId) {
        List<Entrega> entregas = entregaRepository.findByAlumnoRecibeId(alumnoId);
        List<EntregaDTO> dtos = new ArrayList<>();
        for (Entrega entrega : entregas) {
            dtos.add(convertirADTO(entrega));
        }
        return dtos;
    }

    private EntregaDTO convertirADTO(Entrega entrega) {
        EntregaDTO dto = new EntregaDTO();
        dto.setId(entrega.getId());
        dto.setFechaEntrega(entrega.getFechaEntrega());
        dto.setObjeto(objetoMapper.objetoToDto(entrega.getObjeto()));
        dto.setAlumnoReporta(alumnoMapper.alumnoToAlumnoDTO(entrega.getAlumnoReporta()));
        dto.setAlumnoRecibe(alumnoMapper.alumnoToAlumnoDTO(entrega.getAlumnoRecibe()));
        return dto;
    }
}