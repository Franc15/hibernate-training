package com.pine;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class Main {
    public static void main(String[] args) {
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
//        SessionFactory sessionFactory = new MetadataSources(serviceRegistry).addAnnotatedClass(Author.class)
//                .buildMetadata()
//                .buildSessionFactory();
//        Session session = sessionFactory.openSession();
        System.out.println("Hello world");
    }
}