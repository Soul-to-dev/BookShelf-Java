package bookstoread;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Classe de test pour BookShelf.
 * Organise les tests en catégories avec @Nested pour une meilleure lisibilité.
 */
@DisplayName("Bibliothèque de livres")
public class BookShelfSpec {

    private BookShelf shelf;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;

    /**
     * Initialisation commune avant chaque test.
     * Crée une étagère vide et 4 livres de référence.
     */
    @BeforeEach
    void init() throws Exception {
        shelf = new BookShelf();
        effectiveJava = new Book("Effective Java", "Joshua Bloch",
                LocalDate.of(2008, Month.MAY, 8));
        codeComplete = new Book("Code Complete", "Steve McConnell",
                LocalDate.of(2004, Month.JUNE, 9));
        mythicalManMonth = new Book("The Mythical Man-Month",
                "Frederick Phillips Brooks", LocalDate.of(1975, Month.JANUARY, 1));
        cleanCode = new Book("Clean Code", "Robert C. Martin",
                LocalDate.of(2008, Month.AUGUST, 1));
    }

    /**
     * Tests pour vérifier que la bibliothèque est vide.
     */
    @Nested
    @DisplayName("Est vide")
    class IsEmpty {

        @Test
        @DisplayName("Quand aucun livre n'y est ajouté")
        void emptyBookShelfWhenNoBookAdded() throws Exception {
            List<Book> books = shelf.books();
            assertTrue(books.isEmpty(), () -> "La bibliothèque devrait être vide.");
        }

        @Test
        @DisplayName("Quand add est appelé sans livres")
        void emptyBookShelfWhenAddIsCalledWithoutBooks() {
            shelf.add();
            List<Book> books = shelf.books();
            assertTrue(books.isEmpty(), () -> "La bibliothèque devrait être vide.");
        }
    }

    /**
     * Tests pour vérifier l'ajout de livres.
     */
    @Nested
    @DisplayName("Après avoir ajouté des livres")
    class BooksAreAdded {

        @Test
        @DisplayName("Contient deux livres")
        void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
            shelf.add(effectiveJava, codeComplete);
            List<Book> books = shelf.books();
            assertEquals(2, books.size(), () -> "La bibliothèque devrait contenir deux livres.");
        }

        @Test
        @DisplayName("Renvoie au client une collection de livres immuable")
        void booksReturnedFromBookShelfIsImmutableForClient() {
            shelf.add(effectiveJava, codeComplete);
            List<Book> books = shelf.books();
            try {
                books.add(mythicalManMonth);
                fail(() -> "Impossible d'ajouter un livre à la liste immuable");
            } catch (Exception e) {
                assertTrue(e instanceof UnsupportedOperationException,
                        () -> "Devrait lever UnsupportedOperationException.");
            }
        }
    }

    /**
     * Tests pour vérifier le tri et l'arrangement des livres.
     */
    @Nested
    @DisplayName("Tri et arrangement")
    class ArrangementAndOrdering {

        @Test
        @DisplayName("La bibliothèque est organisée lexicographiquement par titre")
        void bookshelfArrangedByBookTitle() {
            shelf.add(effectiveJava, codeComplete, mythicalManMonth);
            List<Book> books = shelf.arrange();
            assertEquals(asList(codeComplete, effectiveJava, mythicalManMonth), books,
                    () -> "Les livres doivent être triés alphabétiquement par titre");
        }

        @Test
        @DisplayName("Les livres restent en ordre d'insertion après appel à arrange()")
        void booksInBookShelfAreInInsertionOrderAfterCallingArrange() {
            shelf.add(effectiveJava, codeComplete, mythicalManMonth);
            shelf.arrange();
            List<Book> books = shelf.books();
            assertEquals(asList(effectiveJava, codeComplete, mythicalManMonth), books,
                    () -> "Les livres doivent rester en ordre d'insertion");
        }

        @Test
        @DisplayName("Les livres sont triés selon les critères fournis par l'utilisateur (ordre inverse)")
        void bookshelfArrangedByUserProvidedCriteria() {
            shelf.add(effectiveJava, codeComplete, mythicalManMonth);
            List<Book> books = shelf.arrange(Comparator.<Book>naturalOrder().reversed());
            assertEquals(asList(mythicalManMonth, effectiveJava, codeComplete), books,
                    () -> "Les livres doivent être triés en ordre décroissant par titre");
        }
    }

    /**
     * Tests pour vérifier le regroupement des livres.
     */
    @Nested
    @DisplayName("Regroupement des livres")
    class GroupingBooks {

        @Test
        @DisplayName("Les livres sont regroupés par année de publication")
        void groupBooksInsideBookShelfByPublicationYear() {
            shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
            Map<Year, List<Book>> booksByPublicationYear = shelf.groupByPublicationYear();

            assertThat(booksByPublicationYear)
                    .containsKey(Year.of(2008))
                    .containsValue(asList(effectiveJava, cleanCode));
            assertThat(booksByPublicationYear)
                    .containsKey(Year.of(2004))
                    .containsValue(Collections.singletonList(codeComplete));
            assertThat(booksByPublicationYear)
                    .containsKey(Year.of(1975))
                    .containsValue(Collections.singletonList(mythicalManMonth));
        }

        @Test
        @DisplayName("Les livres sont regroupés selon les critères fournis par l'utilisateur (par auteur)")
        void groupBooksByUserProvidedCriteria() {
            shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
            Map<String, List<Book>> booksByAuthor = shelf.groupBy(Book::getAuthor);

            assertThat(booksByAuthor)
                    .containsKey("Joshua Bloch")
                    .containsValue(Collections.singletonList(effectiveJava));
            assertThat(booksByAuthor)
                    .containsKey("Steve McConnell")
                    .containsValue(Collections.singletonList(codeComplete));
            assertThat(booksByAuthor)
                    .containsKey("Frederick Phillips Brooks")
                    .containsValue(Collections.singletonList(mythicalManMonth));
            assertThat(booksByAuthor)
                    .containsKey("Robert C. Martin")
                    .containsValue(Collections.singletonList(cleanCode));
        }
    }
}