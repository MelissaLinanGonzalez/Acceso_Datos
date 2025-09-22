package Ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileEjemplo {
    public static void main(String[] args) {
        File archivo = new File("Ficheros/Prueba.txt");
        try {
            BufferedReader bf = new BufferedReader(new FileReader(archivo));

            String linea;
            while ((linea = bf.readLine()) != null){
                System.out.println(linea);
            }

        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
