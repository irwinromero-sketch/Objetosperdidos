package mx.uam.cua.tysi.integracion.alumnos.mapper;

import javax.annotation.processing.Generated;
import mx.uam.cua.tysi.integracion.alumnos.dto.AlumnoDTO;
import mx.uam.cua.tysi.integracion.alumnos.entity.Alumno;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-24T13:30:48-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class AlumnoMapperImpl implements AlumnoMapper {

    @Override
    public AlumnoDTO alumnoToAlumnoDTO(Alumno alumno) {
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

    @Override
    public Alumno alumnoDTOToAlumno(AlumnoDTO alumnoDTO) {
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
}
