package com.niki.katalog.entity;

import javax.persistence.*;

@Entity
@Table(name = "deleted_storages")
public class DeletedItem {

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

    @Column(name = "storage_name")
    private String StorageName;

    @Column(name = "type_name")
    private String typeName;

    public DeletedItem(String name, String description, String imgPaths, String key, String incomeDate, String storageName, String typeName) {
        this.name = name;
        this.description = description;
        this.imgPaths = imgPaths;
        this.key = key;
        this.incomeDate = incomeDate;
        StorageName = storageName;
        this.typeName = typeName;
    }

    public DeletedItem() {
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

    public String getStorageName() {
        return StorageName;
    }

    public void setStorageName(String storageName) {
        StorageName = storageName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
                ", StorageName='" + StorageName + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
