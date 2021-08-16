package com.niki.katalog.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="itemtypes")
public class ItemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ITEM_TYPE_ID")
    private int itemTypeId;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;



    public ItemType(int id, String name, String description) {
        this.itemTypeId = id;
        this.name = name;
        this.description = description;
    }

    public ItemType() {
    }

    public int getId() {
        return itemTypeId;
    }

    public void setId(int id) {
        this.itemTypeId = id;
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
                "itemTypeId=" + itemTypeId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
