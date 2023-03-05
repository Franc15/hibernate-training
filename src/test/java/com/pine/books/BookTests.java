package com.pine.books;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.Before;
import org.junit.Test;

public class BookTests {
    EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    @Test
    public void testWithJPA(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // create a new book and add it to the database
        Book book = new Book();
        book.setTitle("Wombat & Fox");
        entityManager.persist(book);

        // commit to database
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void test() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(serviceRegistry).addAnnotatedClass(Book.class)
                .buildMetadata()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // create a new book and add it to the database
        Book book = new Book();
        book.setTitle("Wombat & Fox");
        session.persist(book);

        session.getTransaction().commit();
        session.close();

    }
}
