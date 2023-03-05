package com.pine;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class ProductsManager {
    public static void main(String[] args) {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // creates a new product
        Product product = new Product();
        product.setName("Civic");
        product.setDescription("Comfortable, fuel-saving car");
        product.setPrice(20000);

        // creates product detail
        ProductDetail detail = new ProductDetail();
        detail.setPartNumber("ABCDEFGHIJKL");
        detail.setDimension("2,5m x 1,4m x 1,2m");
        detail.setWeight(1000);
        detail.setManufacturer("Honda Automobile");
        detail.setOrigin("Japan");

        // sets the bidirectional association
        product.setProductDetail(detail);
        detail.setProduct(product);

        session.persist(product);

        Product product1 = (Product) session.get(Product.class, 1);
        System.out.println("Product Name: " + product1.getName());
        System.out.println("Product Description: " + product1.getDescription());
        System.out.println("Product Price: " + product1.getPrice());
        System.out.println("--------------------------------------");

        System.out.println("===============DETAILS================");
        ProductDetail detail1 = product1.getProductDetail();
        System.out.println("Part Number: " + detail1.getPartNumber());
        System.out.println("Dimensions: " + detail1.getDimension());
        System.out.println("Weight: " + detail1.getWeight());
        System.out.println("Manufacturer: " + detail1.getManufacturer());
        System.out.println("Origin: " + detail1.getOrigin());
        System.out.println();

        session.getTransaction().commit();

        session.close();
    }
}