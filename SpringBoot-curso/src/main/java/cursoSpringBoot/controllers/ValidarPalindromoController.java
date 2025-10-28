package cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para verificar palíndromos.
 * */

@RestController
public class ValidarPalindromoController {

    /**
     *
     * @param palabra La palabra a verificar
     * @return Un mensaje indicando si la palabra es un palindromo o no
     */
    @GetMapping("/validar-palindromo/{palabra}")
    public String validadPalindromo(@PathVariable String palabra){ 
        StringBuilder sb = new StringBuilder(palabra);
        String palabraInvertida = sb.reverse().toString();
        if (palabra.equals(palabraInvertida)){
            return "La palabra " + palabra + " ES palíndromo";
        } else {
            return "La palabra " + palabra + " NO es palíndromo";
        }
    }
}
