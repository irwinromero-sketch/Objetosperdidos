package mx.uam.cua.tysi.integracion.alumnos.mapper;

import javax.annotation.processing.Generated;
import mx.uam.cua.tysi.integracion.alumnos.dto.AlumnoDTO;
import mx.uam.cua.tysi.integracion.alumnos.dto.LibroDTO;
import mx.uam.cua.tysi.integracion.alumnos.dto.PrestamoDTO;
import mx.uam.cua.tysi.integracion.alumnos.entity.Alumno;
import mx.uam.cua.tysi.integracion.alumnos.entity.Libro;
import mx.uam.cua.tysi.integracion.alumnos.entity.Prestamo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-17T12:10:16-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class PrestamoMapperImpl implements PrestamoMapper {

    @Override
    public PrestamoDTO toDto(Prestamo prestamo) {
        if ( prestamo == null ) {
            return null;
        }

        PrestamoDTO prestamoDTO = new PrestamoDTO();

        prestamoDTO.setId( prestamo.getId() );
        prestamoDTO.setFechaPrestamo( prestamo.getFechaPrestamo() );
        prestamoDTO.setFechaDevolucion( prestamo.getFechaDevolucion() );
        prestamoDTO.setAlumno( alumnoToAlumnoDTO( prestamo.getAlumno() ) );
        prestamoDTO.setLibro( libroToLibroDTO( prestamo.getLibro() ) );

        return prestamoDTO;
    }

    @Override
    public Prestamo toEntity(PrestamoDTO prestamoDTO) {
        if ( prestamoDTO == null ) {
            return null;
        }

        Prestamo prestamo = new Prestamo();

        prestamo.setId( prestamoDTO.getId() );
        prestamo.setFechaPrestamo( prestamoDTO.getFechaPrestamo() );
        prestamo.setFechaDevolucion( prestamoDTO.getFechaDevolucion() );
        prestamo.setAlumno( alumnoDTOToAlumno( prestamoDTO.getAlumno() ) );
        prestamo.setLibro( libroDTOToLibro( prestamoDTO.getLibro() ) );

        calcularFecha( prestamoDTO, prestamo );

        return prestamo;
    }

    protected AlumnoDTO alumnoToAlumnoDTO(Alumno alumno) {
        if ( alumno == null ) {
            return null;
        }

        AlumnoDTO alumnoDTO = new AlumnoDTO();

        alumnoDTO.setId( alumno.getId() );
        alumnoDTO.setNombre( alumno.getNombre() );
        alumnoDTO.setApellido( alumno.getApellido() );
        alumnoDTO.setCarrera( alumno.getCarrera() );
        alumnoDTO.setPromedio( alumno.getPromedio() );
        alumnoDTO.setEmail( alumno.getEmail() );

        return alumnoDTO;
    }

    protected LibroDTO libroToLibroDTO(Libro libro) {
        if ( libro == null ) {
            return null;
        }

        LibroDTO libroDTO = new LibroDTO();

        libroDTO.setId( libro.getId() );
        libroDTO.setTitulo( libro.getTitulo() );

        return libroDTO;
    }

    protected Alumno alumnoDTOToAlumno(AlumnoDTO alumnoDTO) {
        if ( alumnoDTO == null ) {
            return null;
        }

        Alumno alumno = new Alumno();

        alumno.setId( alumnoDTO.getId() );
        alumno.setNombre( alumnoDTO.getNombre() );
        alumno.setApellido( alumnoDTO.getApellido() );
        alumno.setCarrera( alumnoDTO.getCarrera() );
        alumno.setPromedio( alumnoDTO.getPromedio() );
        alumno.setEmail( alumnoDTO.getEmail() );

        return alumno;
    }

    protected Libro libroDTOToLibro(LibroDTO libroDTO) {
        if ( libroDTO == null ) {
            return null;
        }

        Libro libro = new Libro();

        libro.setId( libroDTO.getId() );
        libro.setTitulo( libroDTO.getTitulo() );

        return libro;
    }
}
