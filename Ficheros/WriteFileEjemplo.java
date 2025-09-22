package Ficheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriteFileEjemplo {
    public static void main(String[] args) {
        File archivo = new File("Ficheros/Prueba.txt");
        Scanner entrada = new Scanner(System.in);

        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, true));
            System.out.print("Escribe lo que desees añadir al archivo: ");
            String aniadido = entrada.nextLine();

            escritor.write("\n" + aniadido);
            System.out.println("Mensaje añadido correctamente");
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
