package bookstoread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BookShelf {

    private List<String> books = new ArrayList<>();

    public void add(String... booksToAdd) {
        books.addAll(Arrays.asList(booksToAdd));
    }

    public List<String> books() {
        return Collections.unmodifiableList(books);
    }

    // Nouvelle méthode : retourne les livres triés alphabétiquement
    public List<String> arrange() {
        List<String> sortedBooks = new ArrayList<>(books);
        Collections.sort(sortedBooks);
        return sortedBooks;
    }

}