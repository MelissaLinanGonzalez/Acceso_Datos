import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        File archivo = new File("Ejercicios/src/salida.txt");
        Scanner entrada = new Scanner(System.in);
        String nombre;

        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, true));
            do{
                System.out.print("Introduce el nombre: ");
                nombre = entrada.nextLine();

                if (!nombre.equals("fin")){
                    escritor.write(nombre + "\n");
                }
            } while (!nombre.equals("fin"));

            escritor.close();
            System.out.println("Nombres escritos correctamente en el archivo");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
