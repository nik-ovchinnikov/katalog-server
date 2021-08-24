package com.niki.katalog.DAO;

import com.niki.katalog.entity.ItemType;

import java.util.List;

public interface IITemTypeDAO {
    public List<ItemType> findAll();

    public void delete(String typeName);

    public void add(ItemType Object);

    public void update(ItemType newObject);

    public ItemType find(int id);

    boolean isExistByName(String itemTypeName);
}
