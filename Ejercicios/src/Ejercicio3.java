import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        File archivo = new File("Ejercicios/src/salida.txt");
        Scanner entrada = new Scanner(System.in);

        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, true));
            while (true){
                System.out.print("Introduce el nombre: ");
                String nombre = entrada.nextLine();

                if (nombre.equalsIgnoreCase("fin")){
                    break;
                }

                escritor.write(nombre + "\n");
            }

            escritor.close();
            System.out.println("Nombres escritos correctamente en el archivo");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
