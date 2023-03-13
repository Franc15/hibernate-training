package com.pine.main;

import com.pine.model.Address;
import com.pine.model.Employee;
import com.pine.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class NativeSQLExamples {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        // Get all Employees
        Transaction tx = session.beginTransaction();
//        Query<Object[]> query = session.createNativeQuery("select e.emp_id, emp_name, emp_salary,address_line1, city," +
//                "\tzipcode from Employee e, Address a where a.emp_id=e.emp_id");
//        List<Object[]> rows = query.list();
//        for (Object[] row : rows) {
//            Employee emp = new Employee();
//            emp.setId(Long.parseLong(row[0].toString()));
//            emp.setName(row[1].toString());
//            emp.setSalary(Double.parseDouble(row[2].toString()));
//            Address address = new Address();
//            address.setAddressLine1(row[3].toString());
//            address.setCity(row[4].toString());
//            address.setZipcode(row[5].toString());
//            emp.setAddress(address);
//            System.out.println(emp);
//        }

        Query<Employee> q = session.createQuery("SELECT emp from Employee emp join fetch emp.address addr", Employee.class);
        List<Employee> employees = q.list();
        employees.forEach(System.out::println);


        tx.commit();
        sessionFactory.close();
    }
}
