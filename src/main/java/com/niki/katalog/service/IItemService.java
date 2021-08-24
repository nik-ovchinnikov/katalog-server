package com.niki.katalog.service;

import com.niki.katalog.entity.Item;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IItemService {
    public List<Item> findAll();

    public void delete(String itemKey);

    public void add(Item storage);

    public int getIdByKey(String key);

    public void update(Item item) throws IOException;

    public Item find(int id);

    List<Item> getItemsByStorage(String storageName);

    @Transactional
    List<Item> getItemsByType(String typeName);

    @Transactional
    boolean isExistByKey(String itemKey);

    boolean isExistByPhotoName(String photoName);
}
