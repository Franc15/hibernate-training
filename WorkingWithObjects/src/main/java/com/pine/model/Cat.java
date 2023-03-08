package com.pine.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private char sex;
    private String color;
    @OneToMany(mappedBy = "cat")
    private Set<Kitten> kittens;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<Kitten> getKittens() {
        return kittens;
    }

    public void setKittens(Set<Kitten> kittens) {
        this.kittens = kittens;
        this.kittens.forEach(kitten -> kitten.setCat(this));
    }
}
