package com.pine;

import com.pine.model.Author;
import com.pine.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Author author = new Author();
        author.setFirstName("Roald");
        author.setLastName("Dahl");
        author.setVersion(1);

        Book book1 = new Book();
        book1.setVersion(3);
        book1.setTitle("Charlie & the Chocolate Factory");

        Book book2 = new Book();
        book2.setVersion(2);
        book2.setTitle("Wombat & Fox");

        Set<Book> books = Set.of(book2,book1);

        author.setBooks(books);

//        Query q = em.createNativeQuery("");

        books.forEach(em::persist);
        em.persist(author);

        em.getTransaction().commit();

        em.close();
    }
}