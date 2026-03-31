package mx.uam.cua.tysi.integracion.alumnos.controller;

import mx.uam.cua.tysi.integracion.alumnos.dto.EntregaDTO;
import mx.uam.cua.tysi.integracion.alumnos.service.EntregaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @PostMapping
    public EntregaDTO crear(@RequestBody EntregaDTO entregaDTO) {
        return entregaService.crear(entregaDTO);
    }

    @GetMapping("/{id}")
    public EntregaDTO obtenerPorId(@PathVariable Long id) {
        return entregaService.obtenerPorId(id);
    }

    @GetMapping
    public List<EntregaDTO> obtenerTodos() {
        return entregaService.obtenerTodos();
    }

    @GetMapping("/reporta/{alumnoId}")
    public List<EntregaDTO> obtenerPorAlumnoReporta(@PathVariable Long alumnoId) {
        return entregaService.obtenerPorAlumnoReporta(alumnoId);
    }

    @GetMapping("/recibe/{alumnoId}")
    public List<EntregaDTO> obtenerPorAlumnoRecibe(@PathVariable Long alumnoId) {
        return entregaService.obtenerPorAlumnoRecibe(alumnoId);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        entregaService.eliminar(id);
        return "Entrega eliminada correctamente";
    }
}