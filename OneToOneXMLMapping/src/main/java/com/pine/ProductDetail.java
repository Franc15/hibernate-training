package com.pine;

import jakarta.persistence.*;

@Entity
@Table(name = "product_detail")
public class ProductDetail {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @Column(name = "part_number")
    private String partNumber;
    @Column(name = "dimension")
    private String dimension;
    @Column(name = "weight")
    private float weight;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "origin")
    private String origin;
    @OneToOne(mappedBy = "productDetail")
    private Product product;

    public ProductDetail() {
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
