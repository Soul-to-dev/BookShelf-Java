package bookstoread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookShelf {

    // Liste qui stocke les livres
    private List<String> books = new ArrayList<>();

    // Méthode pour ajouter un livre
    public void add(String book) {
        books.add(book);
    }

    // Méthode pour retourner la liste des livres
    public List<String> books() {
        return Collections.unmodifiableList(books);
    }
}