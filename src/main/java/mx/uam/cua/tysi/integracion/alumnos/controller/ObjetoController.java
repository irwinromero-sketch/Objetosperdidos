package mx.uam.cua.tysi.integracion.alumnos.controller;

import mx.uam.cua.tysi.integracion.alumnos.dto.ObjetoDTO;
import mx.uam.cua.tysi.integracion.alumnos.service.ObjetoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/objetos")
public class ObjetoController {

    private final ObjetoService objetoService;

    public ObjetoController(ObjetoService objetoService) {
        this.objetoService = objetoService;
    }

    @PostMapping
    public ObjetoDTO crear(@RequestBody ObjetoDTO objetoDTO) {
        return objetoService.crear(objetoDTO);
    }

    @GetMapping
    public List<ObjetoDTO> obtenerTodos() {
        return objetoService.obtenerTodos();
    }

    @GetMapping("/buscar/{id}")
    public ObjetoDTO obtenerPorId(@PathVariable Long id) {
        return objetoService.obtenerPorId(id);
    }

    @GetMapping("/alumno/{alumnoId}")
    public List<ObjetoDTO> obtenerPorAlumno(@PathVariable Long alumnoId) {
        return objetoService.obtenerPorAlumno(alumnoId);
    }

    @PatchMapping("/{id}/estado")
    public ObjetoDTO actualizarEstado(@PathVariable Long id, @RequestBody String estado) {
        return objetoService.actualizarEstado(id, estado);
    }

    @PatchMapping("/{id}/descripcion")
    public ObjetoDTO actualizarDescripcion(@PathVariable Long id, @RequestBody String descripcion) {
        return objetoService.actualizarDescripcion(id, descripcion);
    }

    @PatchMapping("/{id}/imagen")
    public ObjetoDTO actualizarImagen(@PathVariable Long id, @RequestBody String nombreImagen) {
        return objetoService.actualizarImagen(id, nombreImagen);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        objetoService.eliminar(id);
        return "Objeto eliminado correctamente";
    }
}
