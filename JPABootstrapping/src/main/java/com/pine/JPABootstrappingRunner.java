package com.pine;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

public class JPABootstrappingRunner {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        // add new author and books
//        addNewValues(entityManager);

        EntityGraph<Author> entityGraph = entityManager.createEntityGraph(Author.class);
        entityGraph.addAttributeNodes("books");
        TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a", Author.class)
                .setHint("javax.persistence.fetchgraph", entityGraph);
        List<Author> authors = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        for (Author author : authors) {
            System.out.println();
            System.out.println("Author Name: " + author.getFirstName() + " " + author.getLastName());
            System.out.println("-----------------------------------");
            System.out.println("He wrote the books " + author.getBooks().stream()
                    .map(b -> b.getTitle())
                    .collect(Collectors.joining(", ")));
        }
    }

    public static void addNewValues(EntityManager entityManager) {
        Book book1 = new Book("Adventures of Huckleberry Finn");
        Book book2 = new Book("Adventures of Tom Sawyer");

        List<Book> books = List.of(book1, book2);

        Author author = new Author("Mark", "Twain", books);

        author.setBooks(books);

        books.forEach(entityManager::persist);
        entityManager.persist(author);
    }
}