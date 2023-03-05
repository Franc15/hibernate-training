package com.pine;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Teacher teacher = new Teacher();
        teacher.setFirstName("Tim");
        teacher.setLastName("Barnes");

        Course c1 = new Course();
        c1.setTitle("Spring 101");
        c1.setTeacher(teacher);

        Course c2 = new Course();
        c2.setTitle("Fundamentals of Software Engineering");
        c2.setTeacher(teacher);

        teacher.setCourses(List.of(c1, c2));

        teacher.getCourses().forEach(session::persist);

        session.persist(teacher);

        Teacher foundTeacher = session.get(Teacher.class, 1);
        System.out.println();
        System.out.println("Teacher Name: " + foundTeacher.getFirstName() + " " + foundTeacher.getLastName());
        System.out.println("-----------------------------------");
        foundTeacher.getCourses().forEach(course -> System.out.println(course.getTitle()));
        System.out.println();

        session.getTransaction().commit();

        session.close();
    }
}