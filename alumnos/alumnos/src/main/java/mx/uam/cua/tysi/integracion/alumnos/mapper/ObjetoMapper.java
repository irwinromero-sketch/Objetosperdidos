package mx.uam.cua.tysi.integracion.alumnos.mapper;

import mx.uam.cua.tysi.integracion.alumnos.dto.ObjetoDTO;
import mx.uam.cua.tysi.integracion.alumnos.entity.Objeto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {AlumnoMapper.class})
public interface ObjetoMapper {

    ObjetoDTO objetoToDto(Objeto objeto);

    Objeto dtoToObjeto(ObjetoDTO objetoDTO);
}