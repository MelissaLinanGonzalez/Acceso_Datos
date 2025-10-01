package EjA2XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    public static List<Book> read(File xmlFile) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);

        NodeList nodes = doc.getElementsByTagName("book");
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            Book b = new Book();
            b.id = e.getAttribute("id");
            b.title  = textOf(e, "title");
            b.isbn = e.getAttribute("isbn");

            NodeList authors = e.getElementsByTagName("author");
            if (authors.getLength()>0){
                b.authors = new ArrayList<>();

                for (int j = 0; j < authors.getLength(); j++) {
                    Element elemento = (Element) authors.item(j);
                    String name = elemento.getTextContent().trim();
                    String role = elemento.getAttribute("role");
                    b.authors.add(String.valueOf(new Author(name, role)));

                }
            }
            NodeList categories = e.getElementsByTagName("category");
            if (categories.getLength()>0){
                b.categories = new ArrayList<>();
                for (int k = 0; k < categories.getLength(); k++) {
                    Element ele = (Element) categories.item(k);
                    b.categories.add(ele.getTextContent().trim());
                }
            }

            b.currency = e.getAttribute("currency");

            b.year   = parseIntSafe(textOf(e, "year"));
            b.price  = parseDoubleSafe(textOf(e, "price"));
            books.add(b);
        }
        return books;
    }

    private static String textOf(Element parent, String tag) {
        NodeList nl = parent.getElementsByTagName(tag);
        return (nl.getLength() > 0) ? nl.item(0).getTextContent().trim() : "";
    }

    private static int parseIntSafe(String s) {
        return s.isEmpty() ? 0 : Integer.parseInt(s);
    }

    private static double parseDoubleSafe(String s) {
        return s.isEmpty() ? 0.0 : Double.parseDouble(s);
    }
}
