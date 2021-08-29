package com.niki.katalog.entity;

import javax.persistence.*;

@Entity
@Table(name="itemtypes")
public class ItemType {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name= "ID")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    public ItemType(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ItemType() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ItemType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
