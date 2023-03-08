package com.pine;

import com.pine.model.Cat;
import com.pine.model.Kitten;
import com.pine.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

//        Cat cat = new Cat();
//        cat.setColor("brown");
//        cat.setName("Fritz");
//        cat.setSex('M');
//
//        Kitten k1 = new Kitten();
//        k1.setColor("brown");
//        k1.setName("Boob");
//        k1.setSex('F');
//
//        Kitten k2 =  new Kitten();
//        k2.setColor("yellow");
//        k2.setName("Sweety");
//        k2.setSex('F');
//
//        Set<Kitten> kittens = Set.of(k1, k2);
//
//        cat.setKittens(kittens);
//
//        kittens.forEach(session::persist);
//        session.persist(cat);

        List<Object[]> kittens = session.createQuery("select k.name, c.color from Kitten k inner join Cat c where c.id = k.cat.id").list();
        for (Object[] kitten : kittens) {
            System.out.println(kitten[0]);
            System.out.println(kitten[1]);
        }
        tx.commit();

        sessionFactory.close();
    }
}