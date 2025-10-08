package EjA2XML;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SaxReader {

    public static List<Book> read(File xmlFile) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser parser = spf.newSAXParser();

        List<Book> books = new ArrayList<>();

        DefaultHandler handler = new DefaultHandler() {
            Book current = new Book();
            String currentElmenent = "";
            StringBuilder content = new StringBuilder();

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                currentElmenent = qName;
                content.setLength(0);

                switch (qName){
                    case "book":
                        current = new Book();
                        current.id = attributes.getValue("id");
                        current.isbn = attributes.getValue("isbn");
                        break;
                    case "author":
                        currentElmenent = "author";
                        String role = attributes.getValue("role");
                        if (role != null){
                            content.append(role).append("|");
                        }
                        break;
                    case "price":
                        if (current != null){
                            current.currency = attributes.getValue("currency");
                        }
                        break;
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                content.append(ch, start, length);
            }

            @Override
            public void endElement(String uri, String localName, String qName) {
                String text = content.toString().trim();
                if (current != null) {
                    switch (qName) {
                        case "title":
                            current.title  = text;
                            break;
                        case "author":
                            if (text.contains("|")){
                                String[] nombres = text.split("\\|", 2);
                                String role = nombres[0];
                                String nombre = nombres.length > 1 ? nombres[1] : "";
                                current.authors.add(String.valueOf(new Author(nombre, role)));
                            } else {
                                current.authors.add(String.valueOf(new Author(text, "")));
                            } break;
                        case "category":
                            current.categories.add(text);
                            break;
                        case "year":   if (!text.isEmpty()) current.year  = Integer.parseInt(text); break;
                        case "price":  if (!text.isEmpty()) current.price = Double.parseDouble(text); break;
                        case "book":   books.add(current); current = null; break;
                    }
                }
            }
        };

        parser.parse(xmlFile, handler);
        return books;
    }
}
