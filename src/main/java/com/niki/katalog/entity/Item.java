package com.niki.katalog.entity;

import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import java.io.File;
import java.sql.Blob;
import java.util.List;

@Entity
@Table(name="items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_path")
    private String imgPaths;

    @Column(name = "key")
    private String key;

    @Column(name = "income_date")
    private String incomeDate;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "storage_name")
    private Storage storage;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "type_name")
    private ItemType itemType;


    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "item"
            )
    private List<ItemPicture> itemPicture;

    public Item(int id, String name, String description, String imgPaths, String key, String incomeDate, Storage storage, ItemType itemType, List<ItemPicture> itemPicture) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgPaths = imgPaths;
        this.key = key;
        this.incomeDate = incomeDate;
        this.storage = storage;
        this.itemType = itemType;
        this.itemPicture = itemPicture;
    }


    public Item() {
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

    public String getImgPaths() {
        return imgPaths;
    }

    public void setImgPaths(String imgPaths) {
        this.imgPaths = imgPaths;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(String incomeDate) {
        this.incomeDate = incomeDate;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public List<ItemPicture> getItemPicture() {
        return itemPicture;
    }

    public void setItemPicture(List<ItemPicture> itemPicture) {
        this.itemPicture = itemPicture;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imgPaths='" + imgPaths + '\'' +
                ", key='" + key + '\'' +
                ", incomeDate='" + incomeDate + '\'' +
                ", storage=" + storage.getName() +
                ", itemType=" + itemType.getName() +
                '}';
    }
}
