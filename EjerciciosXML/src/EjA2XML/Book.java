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
    public String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + title + "(" + year + ")" + '\n' +
                "\tISBN: " + isbn +
                "\tAutores: " + authors + "(" + role + ")\n"+
                "\tCategorías: " + categories + "\n" +
                "\tPrecio: " + price + currency;
    }

}
