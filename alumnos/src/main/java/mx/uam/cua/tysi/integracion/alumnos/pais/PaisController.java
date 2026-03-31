package mx.uam.cua.tysi.integracion.alumnos.pais;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pais")
public class PaisController {

    private final PaisService paisService;

    public PaisController(PaisService paisService) {
        this.paisService = paisService;
    }

    @GetMapping("/{nombre}")
    public PaisDTO obtenerInfoPais(@PathVariable String nombre) {
        return paisService.obtenerInfoPais(nombre);
    }
}