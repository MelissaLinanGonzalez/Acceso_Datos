package EjA2XML;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        File xml = new File("EjerciciosXML/src/EjA2XML/catalogo.xml");
        List<Book> books;
        books = Reader.read(xml);
        System.out.println("Libros:");
    }
}
