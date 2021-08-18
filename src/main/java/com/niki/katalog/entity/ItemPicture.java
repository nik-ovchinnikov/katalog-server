package com.niki.katalog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "ITEM_PICTURES")
public class ItemPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_PICTURE_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @JoinColumn(name = "ITEM_KEY")
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
            )
    @JsonIgnore
    private Item item;

    @Column(name = "PATH")
    private String path;

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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ItemPicture(int id, String name, Item item, String path) {
        this.id = id;
        this.name = name;
        this.item = item;
        this.path = path;
    }

    public ItemPicture() {
    }

    @Override
    public String toString() {
        return "ItemPicture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", item=" + item +
                ", path='" + path + '\'' +
                '}';
    }
}
