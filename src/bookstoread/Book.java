package bookstoread;

import java.time.LocalDate;

/**
 * Représente un livre avec son titre, auteur et date de publication.
 * Implémente Comparable pour permettre le tri alphabétique par titre.
 */
public class Book implements Comparable<Book> {

    private final String title;
    private final String author;
    private final LocalDate publishedOn;

    public Book(String title, String author, LocalDate publishedOn) {
        this.title = title;
        this.author = author;
        this.publishedOn = publishedOn;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public LocalDate getPublishedOn() { return publishedOn; }

    /**
     * Tri naturel par titre de livre (ordre alphabétique).
     */
    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author +
                "', publishedOn=" + publishedOn + "}";
    }
}