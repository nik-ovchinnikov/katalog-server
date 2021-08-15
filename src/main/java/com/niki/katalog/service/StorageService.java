package com.niki.katalog.service;

import com.niki.katalog.DAO.StorageDAO;
import com.niki.katalog.entity.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StorageService implements IStorageService {

    private StorageDAO dao;

    @Autowired
    public StorageService(StorageDAO theDao){
        dao = theDao;
    }

    @Override
    @Transactional
    public List<Storage> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public void delete(String storageKey) {
       dao.delete(storageKey);
    }

    @Override
    @Transactional
    public void add(Storage storage) {
        dao.add(storage);
    }

    @Override
    @Transactional
    public void update(Storage storage) {
        dao.update(storage);
    }


    @Override
    @Transactional
    public Storage find(int id) {
        return dao.find(id);
    }
}
