package GestiónInventario;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        boolean seguir = false;
        File archivo = new File("src/GestiónInventario/productos.txt");

        while (!seguir){
            System.out.print("""
                *** GESTIÓN DE INVENTARIO ***
                \t1. Añadir producto.
                \t2. Buscar producto por nombre.
                \t3. Modificar el precio de un producto.
                \t4. Calcular el valor total del inventario.
                \t0. Salir
                Introduzca una opción:\s""");

            int opcion = entrada.nextInt();

            switch (opcion){
                case 1:
                    entrada.nextLine();
                    System.out.println("AÑADIR PRODUCTOS");
                    agregarProducto(archivo);
                    break;
                case 2:
                    entrada.nextLine();
                    System.out.println("BUSCAR PRODUCTO POR NOMBRE");
                    buscarProducto(archivo);
                    break;
                case 3:
                    System.out.println("MODIFICAR EL PRECIO DE UN PRODUCTO");
                    modificarPrecio(archivo);
                    break;
                case 4:
                    System.out.println("CALCULAR EL TOTAL DEL INVENTARIO");
                    calcularTotal(archivo);
                    break;
                case 0:
                    System.out.println("Saliendo del programa....");
                    seguir = true;
                    break;
            }
        }
    }

    public static void agregarProducto(File archivo) {
        Scanner entrada = new Scanner(System.in);

        int ID = 1;
        if (archivo.exists()){
            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = lector.readLine()) != null){
                    ID++;
                }
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.print("Introduce el nombre del producto: ");
        String nombre = entrada.nextLine();
        System.out.print("Introduce la categoría del producto: ");
        String categoria = entrada.nextLine();
        System.out.print("Introduce el precio del producto: ");
        String precio = entrada.nextLine();
        System.out.print("Introduce la cantidad del producto: ");
        String cantidad = entrada.nextLine();

        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo,true));
            escritor.write(ID + "|" + nombre + "|" + categoria + "|" + precio + "|" + cantidad);
            escritor.newLine();
            System.out.println("Producto añadido correctamente");
            System.out.println(" ");
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void buscarProducto(File archivo){
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduzca el nombre del producto que desea buscar: ");
        String nombre = entrada.nextLine();
        boolean encontrado = false;

        if (archivo.exists()){
            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = lector.readLine()) != null){
                    String[] productos = linea.split("\\|");
                    if (productos[1].equals(nombre)){
                        System.out.println(" ");
                        System.out.println("ID|nombre|categoría|precio|cantidad");
                        for (String dato : productos){
                            System.out.print(dato + "|");

                        }
                        System.out.println(" ");
                        System.out.println(" ");
                        encontrado = true;
                    }
                }
                if (!encontrado){
                    throw new Exception("El producto <" + nombre + "> no se encuentra en el archivo");
                }
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void modificarPrecio(File archivo){
        if (archivo.exists()){
            Scanner entrada = new Scanner(System.in);
            System.out.print("Introduce el nombre del producto del que desea modificar su precio: ");
            String nombre = entrada.nextLine();
            System.out.print("Introduce el nuevo precio que desea establecer: ");
            String precio = entrada.nextLine();
            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));

                StringBuilder nuevoContenido = new StringBuilder();
                String linea;
                while ((linea = lector.readLine()) != null){
                    String[] datos = linea.split("\\|");
                    if (datos[1].equals(nombre)){
                        datos[3] = precio;
                    }
                    nuevoContenido.append(String.join("|", datos)).append("\n");
                }
                lector.close();
                BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo));
                escritor.write(nuevoContenido.toString());
                escritor.close();

            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void calcularTotal(File archivo){
        if (archivo.exists()){
            double suma = 0;
            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = lector.readLine()) != null){
                    String[] datos = linea.split("\\|");
                    double precio = Double.parseDouble(datos[3]);
                    int cantidad = Integer.parseInt(datos[4]);
                    suma += precio * cantidad;
                }

                System.out.println("La cuantía total del inventario es: " + suma);

            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }


}
