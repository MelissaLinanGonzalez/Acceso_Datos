package cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

    @GetMapping({"/saludo/{name}", "/hola/{name}"})
    public String greeting(@PathVariable String name){ // con PathVariable indicamos que es una variable obtenida dinamicamente
        return "Hola " + name;
    }
}
