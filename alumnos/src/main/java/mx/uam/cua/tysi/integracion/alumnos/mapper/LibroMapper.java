package mx.uam.cua.tysi.integracion.alumnos.mapper;

import mx.uam.cua.tysi.integracion.alumnos.dto.LibroDTO;
import mx.uam.cua.tysi.integracion.alumnos.entity.Libro;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LibroMapper {

    LibroDTO libroToDto(Libro libro);

    Libro libroDtoToLibro(LibroDTO libroDTO);
}