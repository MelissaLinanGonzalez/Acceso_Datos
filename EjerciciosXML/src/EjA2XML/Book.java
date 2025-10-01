package EjA2XML;

import java.util.ArrayList;

public class Book {
    public String id;
    public String isbn;
    public String title;
    public ArrayList<String> authors = new ArrayList<>();
    public ArrayList<String> categories = new ArrayList<>();
    public int year;
    public double price;
    public String currency;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    @Override
    public String toString() {
        String autores;
        if(authors.isEmpty()){
            autores = "N/A";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < authors.size(); i++) {
                sb.append(authors.get(i).toString());
                if (i < authors.size() -1){
                    sb.append(", ");
                }
            }
            autores = sb.toString();
        }

        String categorias;
        if(categories.isEmpty()){
            categorias = "N/A";
        } else {
            categorias = String.join(", ", categories);
        }


        return "[" + id + "] " + title + "(" + year + ")" + '\n' +
                "\tISBN: " + isbn + "\n" +
                "\tAutores: " + autores + "\n" +
                "\tCategorías: " + categorias + "\n" +
                "\tPrecio: " + price + currency;
    }

}
