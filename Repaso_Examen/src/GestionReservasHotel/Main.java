package GestionReservasHotel;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File archivo = new File("Repaso_Examen/src/GestionReservasHotel/reservas.txt");
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;

        while (!salir){
            System.out.print("""
                    *** GESTIÓN DE RESERVAS DE HOTEL ***
                    \t1. Añadir reserva
                    \t2. Buscar reserva por nombre
                    \t3. Modificar los días de la estancia
                    \t4. Calcular el total a pagar de una reserva
                    \t5. Salir
                    Introduzca la opción deseada:\s""");

            int opcion = entrada.nextInt();
            switch (opcion){
                case 1:
                    entrada.nextLine();
                    System.out.println(" ");
                    System.out.println("Añadir reserva");
                    agregarReserva(archivo);
                    break;
                case 2:
                    entrada.nextLine();
                    System.out.println(" ");
                    System.out.println("Buscar reserva");
                    buscarReserva(archivo);
                    break;
                case 3:
                    entrada.nextLine();
                    System.out.println(" ");
                    System.out.println("Modificar los dias de estancia");
                    modificarDias(archivo);
                    break;
                case 4:
                    System.out.println(" ");
                    System.out.println("Calcular el total de la reserva");
                    calcularTotal(archivo);
                    entrada.nextLine();
                    break;
                case 5:
                    System.out.println(" ");
                    System.out.println("Saliendo del programa ...");
                    salir = true;
                    break;
            }
        }
    }

    public static void agregarReserva(File archivo){
        Scanner entrada = new Scanner(System.in);

        int ID = 1;
        if (archivo.exists()){
            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = lector.readLine()) != null){
                    ID++;
                }
                lector.close();
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.print("Nombre del cliente: ");
        String nombre = entrada.nextLine();
        System.out.print("Número de habitación: ");
        String habitacion = entrada.nextLine();
        System.out.print("Días de estancia: ");
        String dias = entrada.nextLine();
        System.out.print("Precio por noche: ");
        String precio = entrada.nextLine();

        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo,true));
            escritor.write(ID + "|" + nombre + "|" + habitacion + "|" + dias + "|" + precio);
            escritor.newLine();
            System.out.println("Reserva añadida correctamente");
            System.out.println(" ");
            escritor.close();
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void buscarReserva(File archivo){
        Scanner entrada = new Scanner(System.in);
        boolean existe = false;
        if (archivo.exists()){
            System.out.print("Introduzca el nombre: ");
            String nombre = entrada.nextLine();
            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = lector.readLine()) != null){
                    String[] datos = linea.split("\\|");
                    if (datos[1].equals(nombre)){
                        for (String dato : datos){
                            System.out.print(dato + "|");
                        }
                        existe = true;
                    }
                }
                if (!existe){
                    throw new Exception("El cliente de nombre <" + nombre + "> no tiene reserva asignada");
                }
                System.out.println(" ");
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void modificarDias(File archivo){
        Scanner entrada = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        if (archivo.exists()){
            System.out.print("Introduce el nombre del cliente que desea modificar los días de reserva: ");
            String nombre = entrada.nextLine();
            System.out.print("Introduce la nueva cantidad de días: ");
            String diasNuevos = entrada.nextLine();

            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));

                String linea;
                while ((linea = lector.readLine()) != null){
                    String[] datos = linea.split("\\|");
                    if (datos[1].equals(nombre)){
                        datos[3] = diasNuevos;
                    }
                    sb.append(String.join("|", datos)).append("\n");
                }
                lector.close();

                BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, false));
                escritor.write(sb.toString());
                escritor.close();
                System.out.println("Datos modificados correctamente");


            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void calcularTotal(File archivo){
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce el nombre del cliente: ");
        String nombre = entrada.nextLine();
        double precio = 0;
        int dias = 0;
        double total;

        if (archivo.exists()){
            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = lector.readLine()) != null){
                    String[] datos = linea.split("\\|");
                    if (datos[1].equals(nombre)){
                        precio = Double.parseDouble(datos[4]);
                        dias = Integer.parseInt(datos[3]);
                    }
                }

                total = precio * dias;
                System.out.println("El precio total de la estancia de <" + nombre + "> es de: " + total + "€");

            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
