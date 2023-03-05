package com.pine.aliens;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Before;
import org.junit.Test;

public class AlienTests {
    Configuration configuration;
    ServiceRegistry serviceRegistry;
    SessionFactory sessionFactory;

    @Before
    public void setUp() {
        configuration = new Configuration().configure("META-INF/aliens/alien-hibernate.cfg.xml").addAnnotatedClass(Alien.class);
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Test
    public void testAddNewAlien() {
        // start a new hibernate session
        Session session = sessionFactory.openSession();

        // start a transaction on the session
        Transaction transaction = session.beginTransaction();

        // create a new alien object
        Alien alienx = new Alien();
        alienx.setAid(110);
        AlienName name = new AlienName();
        name.setFname("Jax");
        name.setLname("Rezoo");
        alienx.setAname(name);
        alienx.setColor("Purple");


        // add the alien to the database
        session.persist(alienx);

        // commit the update in the database
        transaction.commit();

        // close the session
        session.close();
    }

    @Test
    public void fetchOneAlien() {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Alien alien = null;

        alien = (Alien) session.get(Alien.class, 100);

        transaction.commit();

        session.close();

        System.out.println(alien);

    }
}
