package com.niki.katalog.service;

import com.niki.katalog.entity.DeletedItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IDeletedItemService {
    public List<DeletedItem> findAll();

    public void delete(String itemKey);

    public void add(DeletedItem storage);

    public int getIdByKey(String key);

    public void update(DeletedItem item);

    public DeletedItem find(int id);

    List<DeletedItem> getItemsByStorage(String storageName);

    @Transactional
    List<DeletedItem> getItemsByType(String typeName);
}
