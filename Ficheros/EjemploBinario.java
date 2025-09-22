package Ficheros;

import java.io.*;

public class EjemploBinario {
    public static void main(String[] args) {
        File archivo = new File("Ficheros/datos.bin");

        // Escritura de datos en archivos binarios
        // Al escribir el archivo binario es necesario poner el tipo de dato
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            DataOutputStream dos = new DataOutputStream(fos);

            dos.writeInt(25);  // entero
            dos.writeDouble(6.5);  //double
            dos.writeUTF("Hola mundo");  //cadena de texto

            dos.close();
            fos.close();
            System.out.println("Datos escritos correctamente");

        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        try {
            //Lectura de datos en archivo binario
            FileInputStream fis = new FileInputStream(archivo);
            DataInputStream dis = new DataInputStream(fis);

            int edad = dis.readInt();
            double pi = dis.readDouble();
            String saludo = dis.readUTF();

            dis.close();
            fis.close();
            System.out.println("Edad: " + edad);
            System.out.println("Número double: " + pi);
            System.out.println("Saludo: " + saludo);
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
