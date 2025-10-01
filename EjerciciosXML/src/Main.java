import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
//        if (args.length == 0) {
//            System.out.println("Uso: java Main [dom|sax|stax] [ruta_xml_opcional]");
//            return;
//        }
//        String mode = args[0].toLowerCase();
        File xml = new File(args.length >= 2 ? args[1] : "EjerciciosXML/src/books.xml");
        String mode = "dom";

        List<Book> books;
        switch (mode) {
            case "dom":
                books = DomReader.read(xml);
                System.out.println("[DOM] Libros:");
                break;
//            case "sax":
//                books = SaxReader.read(xml);
//                System.out.println("[SAX] Libros:");
//                break;
//            case "stax":
//                books = StaxReader.read(xml);
//                System.out.println("[StAX] Libros:");
//                break;
            default:
                System.out.println("Opción no válida: " + mode);
                return;
        }
        books.forEach(System.out::println);
    }
}