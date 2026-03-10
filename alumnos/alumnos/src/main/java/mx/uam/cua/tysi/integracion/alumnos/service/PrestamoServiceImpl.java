package mx.uam.cua.tysi.integracion.alumnos.service;

import mx.uam.cua.tysi.integracion.alumnos.dto.PrestamoDTO;
import mx.uam.cua.tysi.integracion.alumnos.entity.Alumno;
import mx.uam.cua.tysi.integracion.alumnos.entity.Libro;
import mx.uam.cua.tysi.integracion.alumnos.entity.Prestamo;
import mx.uam.cua.tysi.integracion.alumnos.mapper.PrestamoMapper;
import mx.uam.cua.tysi.integracion.alumnos.repository.AlumnoRepository;
import mx.uam.cua.tysi.integracion.alumnos.repository.LibroRepository;
import mx.uam.cua.tysi.integracion.alumnos.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final PrestamoMapper prestamoMapper;
    private final AlumnoRepository alumnoRepository;
    private final LibroRepository libroRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository,
                               PrestamoMapper prestamoMapper,
                               AlumnoRepository alumnoRepository,
                               LibroRepository libroRepository) {
        this.prestamoRepository = prestamoRepository;
        this.prestamoMapper = prestamoMapper;
        this.alumnoRepository = alumnoRepository;
        this.libroRepository = libroRepository;
    }

    @Override
    public PrestamoDTO create(PrestamoDTO prestamoDTO) {

        // Buscar alumno real en BD
        Alumno alumno = alumnoRepository.findById(prestamoDTO.getAlumno().getId())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        // Buscar libro real en BD
        Libro libro = libroRepository.findById(prestamoDTO.getLibro().getId())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        // Crear prestamo manualmente
        Prestamo prestamo = new Prestamo();
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.now().plusWeeks(1));
        prestamo.setAlumno(alumno);
        prestamo.setLibro(libro);

        // Guardar
        prestamo = prestamoRepository.save(prestamo);

        return prestamoMapper.toDto(prestamo);
    }

    @Override
    public List<PrestamoDTO> findAll() {
        List<Prestamo> prestamos = prestamoRepository.findAll();
        List<PrestamoDTO> lista = new ArrayList<>();

        for (Prestamo p : prestamos) {
            lista.add(prestamoMapper.toDto(p));
        }

        return lista;
    }

    @Override
    public List<PrestamoDTO> obtenerPrestamosPorAlumno(Long alumnoId) {
        List<Prestamo> prestamos = prestamoRepository.findByAlumnoId(alumnoId);
        List<PrestamoDTO> lista = new ArrayList<>();

        for (Prestamo p : prestamos) {
            lista.add(prestamoMapper.toDto(p));
        }

        return lista;
    }
}