import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        boolean seguir = false;
        int opcion;

        while (!seguir) {
            System.out.print("""
                    *** MENÚ ***
                    \t1- Añadir alumnos
                    \t2- Devolver el ID de un alumno (por nombre y apellido)
                    \t3- Insertar notas
                    \t4- Calcular la media de notas de un alumno
                    \t0- Salir
                    Introduzca la opción deseada:\s"""
            );
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    entrada.nextLine();
                    introducirAlumno();
                    System.out.print("Introduzca 1 si desea seguir añadiendo alumnos, 0 para salir: ");
                    int continuar = entrada.nextInt();
                    while (continuar == 1) {
                        introducirAlumno();
                        System.out.print("Introduzca 1 si desea seguir añadiendo alumnos, 0 para salir: ");
                        continuar = entrada.nextInt();
                    }
                    break;
                case 2:
                    entrada.nextLine();
                    String nombreB = pedirNombre();
                    String apellidoB = pedirApellido();
                    int Id = devolverID(nombreB, apellidoB);
                    if (Id == 0){
                        System.out.println("El alumno con nombre <" + nombreB + "> y apellido <" + apellidoB + "> no se encuentra en el archivo");
                        break;
                    }
                    System.out.println("Id: " + Id);
                    break;
                case 3:
                    entrada.nextLine();
                    insertarNotas();
                    break;
                case 4:
                    entrada.nextLine();
                    calcularMedia();
                    break;
                case 0:
                    entrada.nextLine();
                    System.out.println("Saliendo del sistema...");
                    seguir = true;
                    break;
            }
        }
    }

    public static String pedirNombre(){
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduzca el nombre del alumno: ");
        return entrada.nextLine();
    }

    public static String pedirApellido(){
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduzca el apellido del alumno: ");
        return entrada.nextLine();
    }

    // Introducimos los datos del alumno a través de la consola
    public static void introducirAlumno(){
        File archivo = new File("Repaso_Examen/src/Alumnos.txt");
        Scanner entrada = new Scanner(System.in);
        int id = 1;
        // Comprobamos si el archivo existe para identificar cuál sería el ID siguiente
        if (archivo.exists()){
            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = lector.readLine()) != null){
                    id++;
                }

            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Si, por el contrario, el archivo no existiese, el ID sería 0

        try {

            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo,true));

            // Pedimos los datos del alumno
            System.out.print("Introduzca el nombre del alumno: ");
            String nombre = entrada.nextLine();
            System.out.print("Introduzca el apellido del alumno: ");
            String apellido = entrada.nextLine();
            System.out.print("Introduzca la fecha de nacimiento del alumno (dd-MM-aaaa): ");
            String fecha = entrada.nextLine();
            System.out.print("Introduzca la clase del alumno: ");
            String clase = entrada.nextLine();
            escritor.write(id + "|" + nombre + "|" + apellido + "|" + fecha + "|" + clase);
            escritor.newLine();
            System.out.println("Alumno añadido correctamente");


            escritor.close();
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Devolver el ID del alumno (nombre y apellido)
    public static int devolverID(String nombre, String apellido){
        File archivo = new File("Repaso_Examen/src/Alumnos.txt");
        if (archivo.exists()){
            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));

                String linea;
                while ((linea = lector.readLine()) != null){
                    String[] datos = linea.split("\\|");
                    if (datos[1].equals(nombre) && datos[2].equals(apellido)){
                        return Integer.parseInt(datos[0]);
                    }
                }

            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        return 0;
    }

    // Añadir notas a un alumno en el archivo notas.txt.
    // Primero pide el nombre y apellidos, obteniendo el ID, usando la funcion delvolverID, pidiendo notas separadas por (;).
    public static void insertarNotas(){
        File archivo = new File("Repaso_Examen/src/notas.txt");
        String nombre = pedirNombre();
        String apellido = pedirApellido();
        int ID = devolverID(nombre, apellido);

        // Como anteriormente tenemos que los IDs comienzan en 1, si devuelve 0 es porque el alumno no existe, por lo que comprobamos que sea distinto a eso, para pedir las notas
        if (ID != 0){
            Scanner entrada = new Scanner(System.in);
            System.out.print("Introduzca las notas separadas por (;) : ");
            String notas = entrada.nextLine();

            try {
                BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, true));
                escritor.write(ID + "|" + notas);
                escritor.newLine();
                System.out.println("Notas añadidas correctamente");
                escritor.close();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("NO SE HA ENCONTRADO A NINGÚN ALUMNO CON ESAS CREDENCIALES");
        }

    }

    // Se pide el nombre y apellido para obtener el ID, se calcula todas las medias asociadas
    public static void calcularMedia(){
        File archivo = new File("Repaso_Examen/src/notas.txt");
        String nombre = pedirNombre();
        String apellido = pedirApellido();
        int ID = devolverID(nombre, apellido);
        // Volvemos a comprobar si el ID nos da 0, es por que el alumno no existe
        if (ID == 0){
            System.out.println("Alumno no encontrado");
            return;
        }

        double media = 0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;
            // Leemos el archivo y en primera instancia separamos el ID y las notas
            while ((linea = lector.readLine()) != null){
                String[] datos = linea.split("\\|");
                if (Integer.parseInt(datos[0]) == ID){
                    double suma = 0;
                    String[] notas = datos[1].split(";"); // Separamos las notas
                    for (String nota : notas){
                        suma += Double.parseDouble(nota);
                    }
                    media = suma / notas.length;

                }
            }
            System.out.println("La nota media del alumno con id " + ID + " es: " + String.format("%.2f", media));
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}