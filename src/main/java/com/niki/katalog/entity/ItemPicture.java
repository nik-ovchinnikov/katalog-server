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

    @Column(name = "PREVIOUS_NAME")
    private String previousName;

    @Column(name = "TO_DELETE")
    private boolean toDelete;

    @JoinColumn(name = "ITEM_ID")
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REFRESH}
            )
    @JsonIgnore
    private Item item;

    @Column(name = "PATH")
    private String path;

//    private File photo = null;
//
//    public File getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(File photo) {
//        this.photo = photo;
//    }
//


    public boolean isToDelete() {
        return toDelete;
    }

    public void setToDelete(boolean toDelete) {
        this.toDelete = toDelete;
    }

    public String getPreviousName() {
        return previousName;
    }

    public void setPreviousName(String previousName) {
        this.previousName = previousName;
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

    public ItemPicture(int id, String name, String previousName, boolean toDelete, Item item, String path) {
        this.id = id;
        this.name = name;
        this.previousName = previousName;
        this.toDelete = toDelete;
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
                ", previousName='" + previousName + '\'' +
                ", toDelete=" + toDelete +
                ", path='" + path + '\'' +
                '}';
    }
}
