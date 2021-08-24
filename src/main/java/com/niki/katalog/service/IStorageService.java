package com.niki.katalog.service;


import com.niki.katalog.entity.Storage;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IStorageService {
    public List<Storage> findAll();

    public void delete(String deleteStoragekey);

    public void add(Storage storage);

    public void update(Storage storage);

    public Storage find(int id);

    @Transactional
    boolean isExistByName(String itemTypeName);
}
