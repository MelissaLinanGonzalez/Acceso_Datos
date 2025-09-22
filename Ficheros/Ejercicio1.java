package Ficheros;

import java.io.*;
import java.util.Scanner;

/*
* Una función que escribe 3 líneas en un fichero y posteriormente lee y muestrapor pantalla las 3 líneas*/
public class Ejercicio1 {
    public static void main(String[] args) {
        File archivo = new File("Ficheros/Ejercicio1.txt");
        Scanner entrada = new Scanner(System.in);
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, true));
            System.out.print("Escribe una línea: ");
            String linea1 = entrada.nextLine();
            escritor.write(linea1);

            System.out.print("Escribre la segunda línea: ");
            String linea2 = entrada.nextLine();
            escritor.write("\n" + linea2);

            System.out.print("Escribe la tercera línea: ");
            String linea3 = entrada.nextLine();
            escritor.write("\n" + linea3);

            System.out.println("Archivo creado y escrito correctamente");
            escritor.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = lector.readLine()) != null){
                System.out.println(linea);
            }
            
            lector.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
