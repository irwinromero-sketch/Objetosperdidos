package mx.uam.cua.tysi.integracion.alumnos.mapper;

import javax.annotation.processing.Generated;
import mx.uam.cua.tysi.integracion.alumnos.dto.ObjetoDTO;
import mx.uam.cua.tysi.integracion.alumnos.entity.Objeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-31T01:54:11-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class ObjetoMapperImpl implements ObjetoMapper {

    private final AlumnoMapper alumnoMapper;

    @Autowired
    public ObjetoMapperImpl(AlumnoMapper alumnoMapper) {

        this.alumnoMapper = alumnoMapper;
    }

    @Override
    public ObjetoDTO objetoToDto(Objeto objeto) {
        if ( objeto == null ) {
            return null;
        }

        ObjetoDTO objetoDTO = new ObjetoDTO();

        objetoDTO.setId( objeto.getId() );
        objetoDTO.setDescripcion( objeto.getDescripcion() );
        objetoDTO.setFecha( objeto.getFecha() );
        objetoDTO.setEstado( objeto.getEstado() );
        objetoDTO.setAlumno( alumnoMapper.alumnoToAlumnoDTO( objeto.getAlumno() ) );

        return objetoDTO;
    }

    @Override
    public Objeto dtoToObjeto(ObjetoDTO objetoDTO) {
        if ( objetoDTO == null ) {
            return null;
        }

        Objeto objeto = new Objeto();

        objeto.setId( objetoDTO.getId() );
        objeto.setDescripcion( objetoDTO.getDescripcion() );
        objeto.setFecha( objetoDTO.getFecha() );
        objeto.setEstado( objetoDTO.getEstado() );
        objeto.setAlumno( alumnoMapper.alumnoDTOToAlumno( objetoDTO.getAlumno() ) );

        return objeto;
    }
}
