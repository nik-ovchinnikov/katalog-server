package com.niki.katalog.DAO;

import com.niki.katalog.entity.DeletedItem;
import com.niki.katalog.entity.Item;

import java.util.List;

public interface IItemDAO {
    public List<Item> findAll();

    public void delete(String itemKey);

    void add(Item Object);

    void update(Item item);

    int getIdByKey(String key);

    public Item find(int id);

    List<Item> getItemsByStorage(String storageName);

    List<Item> getItemsByType(String typeName);

    boolean isExistByKey(String itemKey);
}
