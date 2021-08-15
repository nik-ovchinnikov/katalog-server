package com.niki.katalog.entity;

import javax.persistence.*;

@Entity
@Table(name="storages")

public class Storage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  @Column(name="name")
  private String name;

  @Column(name="description")
  private String description;


  public Storage(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public Storage() {
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
    return "Storage{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
  }
}
