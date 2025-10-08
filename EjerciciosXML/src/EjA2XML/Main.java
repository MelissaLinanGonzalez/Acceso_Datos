package EjA2XML;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        
        File xml = new File("EjerciciosXML/src/EjA2XML/catalogo.xml");

        boolean salir = false;
        List<Book> books = new ArrayList<>();
        Scanner entrada = new Scanner(System.in);

        while (!salir){
            System.out.println(" ");
            System.out.printf("""
                    *** Menú ***
                    \tdom
                    \tsax
                    \t3. Títulos publicados después del 2010 (ingrese solo el número 3)
                    \t4. Libros con más de un autor (ingrese solo el número 4)
                    \t5. Precio medio de los libros con EUR (ingrese solo el número 5)
                    \tsalir
                    Introduzca la opción deseada:\s""");
            String mode = entrada.nextLine();
            System.out.println(" ");
            switch (mode) {
                case "dom":
                    books = DomReader.read(xml);
                    System.out.println("[DOM] Libros:");
                    books.forEach(System.out::println);
                    break;
                case "sax":
                    books = SaxReader.read(xml);
                    System.out.println("[SAX] Libros:");
                    books.forEach(System.out::println);
                    break;
                case "salir":
                    System.out.println("Saliendo del programa...");
                    salir = true;
                    break;
                case "3":
                    System.out.println("*** Títulos publicados después del 2010 ***");
                    books = DomReader.read(xml);
                    titulosPosteriores2010(books);
                    break;
                case "4":
                    System.out.println("*** Libros con más de un autor ***");
                    books = SaxReader.read(xml);
                    librosConAutores(books);
                    break;
                case "5":
                    System.out.println("*** Precio medio de los libros con EUR ***");
                    books = DomReader.read(xml);
                    precioMedio(books);
                    break;
                default:
                    System.out.println("Opción no válida: " + mode);
                    return;
            }
        }
    }

    public static void titulosPosteriores2010(List<Book> books){
        for (Book libro : books){
            if (libro.getYear() >= 2010){
                System.out.println(libro);
            }
        }
    }

    public static void librosConAutores(List<Book> books){
        for (Book libro : books){
            List<String> autores = libro.authors;
            if (autores.size() >= 2){
                System.out.println(libro);
            }
        }
    }

    public static void precioMedio(List<Book> books){
        double total = 0;
        List<Double> precios = new ArrayList<>();
        for (Book libro : books){
            if (libro.currency.equals("EUR")){
                total += libro.getPrice();
                precios.add(libro.price);
            }
        }

        double media = total / precios.size();
        System.out.println("La media de los libros con currency EUR es: " + media);
    }
}
