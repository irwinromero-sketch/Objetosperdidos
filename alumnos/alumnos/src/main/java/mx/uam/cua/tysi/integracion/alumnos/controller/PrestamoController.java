package mx.uam.cua.tysi.integracion.alumnos.controller;

import mx.uam.cua.tysi.integracion.alumnos.dto.PrestamoDTO;
import mx.uam.cua.tysi.integracion.alumnos.service.PrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public PrestamoDTO crear(@RequestBody PrestamoDTO dto) {
        return prestamoService.create(dto);
    }

    @GetMapping
    public List<PrestamoDTO> obtenerTodos() {
        return prestamoService.findAll();
    }

    @GetMapping("/{alumnoId}")
    public List<PrestamoDTO> obtenerPorAlumno(@PathVariable Long alumnoId) {
        return prestamoService.obtenerPrestamosPorAlumno(alumnoId);
    }
}