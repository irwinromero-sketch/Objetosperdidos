package mx.uam.cua.tysi.integracion.alumnos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/irwin")
public class IrwinController {

    @GetMapping("/saludo")
    public String saludo() {
        return "Hola, soy Irwin Romero!";
    }

    @GetMapping("/status")
    public String status() {
        return "Controller de Irwin funcionando correctamente";
    }
}
