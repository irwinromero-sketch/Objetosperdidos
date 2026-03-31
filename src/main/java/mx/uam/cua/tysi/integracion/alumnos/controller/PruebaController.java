package mx.uam.cua.tysi.integracion.alumnos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {


    @GetMapping ("/prueba")
    public String prueba(){
        return "Hola desde Spring";
    }

    @GetMapping ("/hola")
    public String hola(){
        return "Esta es la ruta /hola";
    }
}