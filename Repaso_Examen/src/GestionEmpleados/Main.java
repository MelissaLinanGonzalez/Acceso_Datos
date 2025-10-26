package GestionEmpleados;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File archivo = new File("Repaso_Examen/src/GestionEmpleados/empleados.txt");
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;

        while (!salir){
            System.out.println(" ");
            System.out.print("""
                    *** GESTIÓN DE EMPLEADOS ***
                    \t1. Añadir empleado
                    \t2. Buscar empleado por DNI
                    \t3. Modificar salario de un empleado
                    \t4. Mostrar empleados con salario superior a una cantidad
                    \t5. Calcular el salario medio
                    \t6. Salir
                    Seleccione la opción deseada:\s""");

            int opcion = entrada.nextInt();
            switch (opcion){
                case 1:
                    entrada.nextLine();
                    System.out.println("*** Añadir empleado ***");
                    agregarEmpleado(archivo);
                    break;
                case 2:
                    entrada.nextLine();
                    System.out.println("*** Buscar empleado por DNI ***");
                    buscarEmpleado(archivo);
                    break;
                case 3:
                    System.out.println("*** Modificar el salario del empleado ***");
                    entrada.nextLine();
                    modificarSalario(archivo);
                    break;
                case 4:
                    System.out.println("*** Mostrar empleados con salario superior a una cantidad ***");
                    entrada.nextLine();
                    mostrarEmpleados(archivo);
                    break;
                case 5:
                    entrada.nextLine();
                    System.out.println("*** Calcular el salario medio ***");
                    calcularSalarioMedio(archivo);
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    salir = true;
                    break;
                default:
                    System.out.println("Entrada no válida, introduzca una opción entre 1 y 6: ");
                    break;
            }
        }
    }

    public static void agregarEmpleado(File archivo){
        Scanner entrada = new Scanner(System.in);

        int id = 1;
        if (archivo.exists()){
            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = lector.readLine()) != null){
                    id++;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.print("Nombre: ");
        String nombre = entrada.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = entrada.nextLine();
        System.out.print("DNI: ");
        String dni = entrada.nextLine();
        System.out.print("Puesto: ");
        String puesto = entrada.nextLine();
        System.out.print("Salario: ");
        String salario = entrada.nextLine();

        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo,true));
            escritor.write(id + "|" + nombre + "|" + apellidos + "|" + dni + "|" + puesto + "|" + salario);
            escritor.newLine();
            System.out.println("Empleado añadido correctamente");
            escritor.close();

        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void buscarEmpleado(File archivo){
        boolean existe = false;

        if (archivo.exists()){
            Scanner entrada = new Scanner(System.in);
            System.out.print("Introduce el DNI: ");
            String dni = entrada.nextLine();

            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = lector.readLine()) != null){
                    String[] datos = linea.split("\\|");
                    if (datos[3].equals(dni)){
                        existe = true;
                        for (String dato : datos){
                            System.out.print(dato + "|");
                        }
                    }

                    System.out.println(" ");
                }
                if (!existe){
                    throw new Exception("El empleado con DNI <" + dni + "> no se encuentra en el archivo");
                }
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }

        }
    }

    public static void modificarSalario(File archivo){
        if (archivo.exists()){
            Scanner entrada = new Scanner(System.in);
            System.out.print("Introduzca el DNI: ");
            String dni = entrada.nextLine();
            System.out.print("Introduzca el nuevo salario: ");
            String salario = entrada.nextLine();

            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                StringBuilder sb = new StringBuilder();
                String linea;
                while ((linea = lector.readLine()) != null){
                    String[] datos = linea.split("\\|");
                    if (datos[3].equals(dni)){
                        datos[5] = salario;
                    }
                    sb.append(String.join("|", datos)).append("\n");
                }

                lector.close();
                BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo,false));
                escritor.write(sb.toString());
                System.out.println("Salario modificado correctamente");
                escritor.close();
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void mostrarEmpleados(File archivo){
        if (archivo.exists()){
            Scanner entrada = new Scanner(System.in);
            System.out.print("Introduzca el salario para mostrar los empleados con salario igual o superior: ");
            double salario = entrada.nextDouble();


            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = lector.readLine()) != null){
                    String[] datos = linea.split("\\|");
                    if (Double.parseDouble(datos[5]) >= salario){
                        for (String dato : datos){
                            System.out.print(dato + "|");
                        }
                        System.out.println(" ");
                    }
                }
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void calcularSalarioMedio(File archivo){
        double suma = 0;
        double media = 0;
        int contador = 0;

        if (archivo.exists()){
            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = lector.readLine()) != null){
                    String[] datos = linea.split("\\|");
                    suma += Double.parseDouble(datos[5]);
                    contador++;
                }

                media = suma / contador;
                System.out.println("La media del salario de los empleados es: " + media + "€");
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
