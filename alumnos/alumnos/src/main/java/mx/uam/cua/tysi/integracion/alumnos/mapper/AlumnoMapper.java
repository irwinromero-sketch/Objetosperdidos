package mx.uam.cua.tysi.integracion.alumnos.mapper;

import mx.uam.cua.tysi.integracion.alumnos.dto.AlumnoDTO;
import mx.uam.cua.tysi.integracion.alumnos.entity.Alumno;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AlumnoMapper {

    AlumnoDTO alumnoToAlumnoDTO(Alumno alumno);

    Alumno alumnoDTOToAlumno(AlumnoDTO alumnoDTO);
}