package mx.uam.cua.tysi.integracion.alumnos.controller;

import mx.uam.cua.tysi.integracion.alumnos.entity.Libro;
import mx.uam.cua.tysi.integracion.alumnos.repository.LibroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroRepository libroRepository;

    public LibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    // 🔹 Crear libro
    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    // 🔹 Obtener todos los libros
    @GetMapping
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    // 🔹 Obtener libro por id
    @GetMapping("/{id}")
    public Libro obtenerPorId(@PathVariable Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }
}