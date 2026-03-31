package mx.uam.cua.tysi.integracion.alumnos.mapper;

import javax.annotation.processing.Generated;
import mx.uam.cua.tysi.integracion.alumnos.dto.LibroDTO;
import mx.uam.cua.tysi.integracion.alumnos.entity.Libro;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-31T01:54:12-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class LibroMapperImpl implements LibroMapper {

    @Override
    public LibroDTO libroToDto(Libro libro) {
        if ( libro == null ) {
            return null;
        }

        LibroDTO libroDTO = new LibroDTO();

        libroDTO.setId( libro.getId() );
        libroDTO.setTitulo( libro.getTitulo() );

        return libroDTO;
    }

    @Override
    public Libro libroDtoToLibro(LibroDTO libroDTO) {
        if ( libroDTO == null ) {
            return null;
        }

        Libro libro = new Libro();

        libro.setId( libroDTO.getId() );
        libro.setTitulo( libroDTO.getTitulo() );

        return libro;
    }
}
