package mx.uam.cua.tysi.integracion.alumnos.mapper;

import mx.uam.cua.tysi.integracion.alumnos.dto.PrestamoDTO;
import mx.uam.cua.tysi.integracion.alumnos.entity.Prestamo;
import org.mapstruct.*;

import java.time.LocalDate;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PrestamoMapper {

    PrestamoDTO toDto(Prestamo prestamo);

    Prestamo toEntity(PrestamoDTO prestamoDTO);

    @AfterMapping
    default void calcularFecha(PrestamoDTO dto, @MappingTarget Prestamo entity) {
        entity.setFechaPrestamo(LocalDate.now());
        entity.setFechaDevolucion(LocalDate.now().plusWeeks(1));
    }
}