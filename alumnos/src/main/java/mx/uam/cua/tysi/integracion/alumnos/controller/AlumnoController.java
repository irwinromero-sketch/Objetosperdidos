package mx.uam.cua.tysi.integracion.alumnos.controller;

import mx.uam.cua.tysi.integracion.alumnos.dto.AlumnoDTO;
import mx.uam.cua.tysi.integracion.alumnos.service.AlumnoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping
    public AlumnoDTO crearAlumno(@RequestBody AlumnoDTO alumnoDTO) {
        return alumnoService.crearAlumno(alumnoDTO);
    }

    @GetMapping
    public List<AlumnoDTO> obtenerTodos() {
        return alumnoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public AlumnoDTO obtenerPorId(@PathVariable Long id) {
        return alumnoService.obtenerPorId(id);
    }

    @PatchMapping("/{id}")
    public AlumnoDTO actualizarAlumno(@PathVariable Long id,
                                      @RequestBody AlumnoDTO alumnoDTO) {
        return alumnoService.actualizarAlumno(id, alumnoDTO);
    }

    @DeleteMapping("/{id}")
    public String eliminarAlumno(@PathVariable Long id) {
        alumnoService.eliminarAlumno(id);
        return "Alumno eliminado correctamente";
    }
}
