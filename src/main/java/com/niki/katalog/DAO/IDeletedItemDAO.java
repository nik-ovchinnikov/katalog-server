package com.niki.katalog.DAO;

import com.niki.katalog.entity.DeletedItem;

import java.util.List;

public interface IDeletedItemDAO {
    List<DeletedItem> findAll();

    void delete(String itemKey);

    void add(DeletedItem item);

    void update(DeletedItem item);

    int getIdByKey(String key);

    DeletedItem find(int id);

    List<DeletedItem> getDeletedItemsByStorage(String storageName);

    List<DeletedItem> getDeletedItemsByType(String typeName);
}
