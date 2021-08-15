package com.niki.katalog.entity;

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

    @OneToMany(mappedBy = "itemType", cascade = CascadeType.ALL)
    private List<Item> items;

    public List<Item> getItem() {
        return items;
    }

    public void setItem(List<Item> item) {
        this.items = item;
    }


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
        return "\nItemType{" +
                "id=" + itemTypeId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", Item ='" + items + '\'' +
                '}';
    }
}
