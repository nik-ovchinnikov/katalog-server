package com.niki.katalog.DAO;

import com.niki.katalog.entity.Storage;

import java.util.List;

public interface IStorageDAO {
    public List<Storage> findAll();

    public void delete(String key);

    public void add(Storage Object);

    public void update(Storage newObject);

    public Storage find(int id);

    boolean isExistByName(String itemTypeName);
}
