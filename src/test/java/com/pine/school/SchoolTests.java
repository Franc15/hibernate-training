package com.pine.school;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.util.List;
import java.util.Optional;

public class SchoolTests {
    SessionFactory sessionFactory;
    Session session;
    @Before
    public void setUp() {
        sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println("SessionFactory created");
    }

    @Test
    public void testCreateNewTeacher() {
        System.out.println("Running testCreateNewTeacher...");
        session = sessionFactory.openSession();
        session.beginTransaction();

        Teacher teacher = new Teacher();
        teacher.setLastName("Doe");
        teacher.setFirstName("John");

        Teacher teacher1 = new Teacher("Tim", "Barnes");
        Teacher teacher2 = new Teacher("Lucy", "Darren");

        session.persist(teacher);
        session.persist(teacher1);
        session.persist(teacher2);

        session.getTransaction().commit();

        session.close();

    }

    @Test
    @Ignore
    public void testAddTeacherAndCourse() {
        System.out.println("Running testAddTeacherAndCourse...");
        session = sessionFactory.openSession();
        session.beginTransaction();

        Teacher teacher = new Teacher("Tim", "Barnes");

        Assert.assertTrue(teacher.getId().equals(1L));
        Assert.assertTrue(teacher.getLastName().equals("Barnes"));

        teacher.getCourses().add(new Course("Math 101"));

        session.persist(teacher);

        session.close();
    }

    @Test
    public void testGetTeacher() {
        System.out.println("Running testGetTeacher...");
        session = sessionFactory.openSession();
        session.beginTransaction();

        Teacher teacher = new Teacher("Bill", "Gates");

        List<Course> courses = List.of(
                new Course("PHP Fundamentals"),
                new Course("Spring Boot 101")
        );

        teacher.setCourses(courses);

        session.persist(teacher);

        Assert.assertNotNull(teacher);

        session.getTransaction().commit();

        session.close();
    }

    @Test
    @Ignore
    public void testCreateNewCourseForTeacher1() {
        System.out.println("Running testCreateNewCourse...");
        session = sessionFactory.openSession();
        session.beginTransaction();

        Teacher foundTeacher = session.get(Teacher.class, 2L);

        Course course = new Course("Java 101");
        Course course1 = new Course("Spring Fundamentals");

        foundTeacher.setCourses(List.of(course1, course));

        session.persist(foundTeacher);

        session.getTransaction().commit();

        session.close();

    }

    @After
    public void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            System.out.println("SessionFactory closed");
        }
    }
}
