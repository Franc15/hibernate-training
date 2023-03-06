package com.pine.main;

import com.pine.model.Employee;
import com.pine.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

public class HQLExamples {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        // HQL Example - get all employees
        Transaction tx = session.beginTransaction();
        Query<?> query = session.createQuery("from Employee");
        List<Employee> employees = (List<Employee>) query.getResultList();
        for(Employee emp : employees){
            System.out.println("List of Employees::"+emp.getId()+","+emp.getAddress().getCity());
        }

        // HQL Example - get employee with id
        query = session.createQuery("from Employee where id=:id");
        query.setParameter("id", 3);
        Employee emp = (Employee) query.uniqueResult();
        System.out.println("Employee Name="+emp.getName()+", City="+emp.getAddress().getCity());

        // HQL Pagination example
        query = session.createQuery("from Employee");
        query.setFirstResult(0);
        query.setFetchSize(2);
        employees = (List<Employee>) query.list();
        for(Employee emp4 : employees){
            System.out.println("Paginated Employees::"+emp4.getId()+","+emp4.getAddress().getCity());
        }

        //HQL Update Employee
        query = session.createQuery("update Employee set name= :name where id= :id");
        query.setParameter("name", "Pankaj Kumar");
        query.setParameter("id", 1);
        int result = query.executeUpdate();
        System.out.println("Employee Update Status="+result);

        //HQL Delete Employee, we need to take care of foreign key constraints too
        query = session.createQuery("delete from Address where id= :id");
        query.setParameter("id", 4);
        result = query.executeUpdate();
        System.out.println("Address Delete Status="+result);

        query = session.createQuery("delete from Employee where id= :id");
        query.setParameter("id", 4);
        result = query.executeUpdate();
        System.out.println("Employee Delete Status="+result);

        //HQL Aggregate function examples
        query = session.createQuery("select sum(salary) from Employee");
        double sumSalary = (Double) query.uniqueResult();
        System.out.println("Sum of all Salaries= "+sumSalary);

        //HQL join examples
        query = session.createQuery("select e.name, a.city from Employee e "
                + "INNER JOIN e.address a");
        List<Object[]> list = (List<Object[]>) query.list();
        for(Object[] arr : list){
            System.out.println(Arrays.toString(arr));
        }

        //HQL group by and like example
        query = session.createQuery("select e.name, sum(e.salary), count(e)"
                + " from Employee e where e.name like '%i%' group by e.name");
        List<Object[]> groupList = (List<Object[]>) query.list();
        for(Object[] arr : groupList){
            System.out.println(Arrays.toString(arr));
        }

        //HQL order by example
        query = session.createQuery("from Employee e order by e.id desc");
        employees = (List<Employee>) query.list();
        for(Employee emp3 : employees){
            System.out.println("ID Desc Order Employee::"+emp3.getId()+","+emp3.getAddress().getCity());
        }

        tx.rollback();

        sessionFactory.close();
    }
}