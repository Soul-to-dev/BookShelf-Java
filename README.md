#  Application de Gestion de Bibliothèque (JUnit 5 & AssertJ)

Ce projet Java implémente un système de gestion, de tri et d'organisation de livres au sein d'une bibliothèque (`BookShelf`). Il applique les concepts modernes de tests unitaires vus en cours.

##  Fonctionnalités Implémentées

### 1. Gestion de la Collection & Immutabilité
- Ajout flexible de livres via le mécanisme de **Varargs** Java.
- Encapsulation forte : la collection exposée au client est **immuable** (`Collections.unmodifiableList`).

### 2. Seconde Fonctionnalité : Tri Chronologique (Exercice 1)
- Tri des livres par date de publication via l'API Stream de Java.
- Validation automatique de la validité du tri à l'aide de la bibliothèque **AssertJ** (`isSortedAccordingTo`).

### 3. Troisième Fonctionnalité : Regroupement Dynamique
- Regroupement initial par année de publication (`groupByPublicationYear`).
- Refactorisation vers une architecture flexible basée sur l'interface fonctionnelle `Function<Book, K>`, permettant le regroupement selon n'importe quel critère dynamique (ex: par auteur) tout en évitant la duplication de code.

##  Technologies & Concepts de Test Utilisés

- JUnit 5 (Jupiter)** : Utilisation des annotations avancées `@Nested` pour structurer logiquement les tests, `@DisplayName` pour des rapports clairs en français, et `@Disabled` pour la gestion des tests ignorés sans altérer les statistiques.
- **AssertJ** : Utilisé pour la richesse et la fluidité de ses assertions de collections (`containsExactly`, `containsEntry`, `isSortedAccordingTo`).

## Exécution des Tests
Pour exécuter l'ensemble des tests de l'application sous IntelliJ IDEA :
1. Faites un clic droit sur le fichier ou le dossier `bookstoread`.
2. Sélectionnez **Run 'BookShelfSpec'**.
3. Constatez le succès de tous les indicateurs au vert (Zéro erreur, zéro warning).
