import com.pine.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HibernateTests {
    ServiceRegistry serviceRegistry;
    SessionFactory sessionFactory;
    Session session;

    @Before
    public void setUp() {
        serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void testGetTeacherAndCourses() {
        System.out.println("Running testGetTeacherAndCourses...");
        session = sessionFactory.openSession();
        session.beginTransaction();

        Teacher foundTeacher = session.get(Teacher.class, 1);
        System.out.println();
        System.out.println("Teacher Name: " + foundTeacher.getFirstName() + " " + foundTeacher.getLastName());
        System.out.println("-----------------------------------");
        foundTeacher.getCourses().forEach(course -> System.out.println(course.getTitle()));
        System.out.println();

        Assert.assertNotNull(foundTeacher.getCourses());

        session.getTransaction().commit();
        session.close();
    }

    @After
    public void tearDown() {
        sessionFactory.close();
    }
}
