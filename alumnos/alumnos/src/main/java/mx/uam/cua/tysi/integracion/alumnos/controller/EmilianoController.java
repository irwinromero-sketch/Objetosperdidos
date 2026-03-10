package mx.uam.cua.tysi.integracion.alumnos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Emiliano")
public class EmilianoController {

    @GetMapping("/saludo")
    public String saludo() {
        return "Soy Emiliano";
    }

    @GetMapping("/status")
    public String status() {
        return "Controller de Emiliano funcionando correctamente";
    }
}