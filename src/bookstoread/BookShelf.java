package bookstoread;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Représente une étagère contenant une collection de livres.
 * Permet d'ajouter, lister, trier et regrouper des livres.
 */
public class BookShelf {

    // Utilisation de 'final' pour éliminer tout warning de modification de référence dans l'IDE
    private final List<Book> books = new ArrayList<>();

    /**
     * Ajoute un ou plusieurs livres à l'étagère.
     * Supporte les varargs pour accepter aucun, un ou plusieurs livres.
     */
    public void add(Book... booksToAdd) {
        if (booksToAdd != null) {
            books.addAll(Arrays.asList(booksToAdd));
        }
    }

    /**
     * Retourne la liste des livres dans l'ordre d'insertion.
     * La liste retournée est immuable pour protéger l'état interne.
     */
    public List<Book> books() {
        return Collections.unmodifiableList(books);
    }

    /**
     * Retourne une nouvelle liste triée alphabétiquement par titre (ordre naturel).
     * La liste originale n'est pas modifiée.
     */
    public List<Book> arrange() {
        return arrange(Comparator.naturalOrder());
    }

    /**
     * Retourne une nouvelle liste triée selon le comparateur fourni par l'utilisateur.
     * La liste originale n'est pas modifiée.
     */
    public List<Book> arrange(Comparator<Book> criteria) {
        return books.stream()
                .sorted(criteria)
                .collect(Collectors.toList());
    }

    /**
     * Troisième fonctionnalité (Refactorisée) : Regroupe les livres par année de publication.
     * Réutilise la méthode générique groupBy() pour éviter la duplication de code.
     */
    public Map<Year, List<Book>> groupByPublicationYear() {
        return this.groupBy(book -> Year.of(book.getPublishedOn().getYear()));
    }

    /**
     * Troisième fonctionnalité : Regroupe les livres selon un critère fourni par l'utilisateur.
     * Utilise explicitement LinkedHashMap pour garantir la stabilité de l'ordre dans les tests unitaires.
     *
     * @param fx La fonction de regroupement (ex: Book::getAuthor)
     * @param <K> Le type de la clé de regroupement
     */
    public <K> Map<K, List<Book>> groupBy(Function<Book, K> fx) {
        return books.stream()
                .collect(Collectors.groupingBy(
                        fx,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));
    }
}