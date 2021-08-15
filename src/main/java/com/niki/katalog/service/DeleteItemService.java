package com.niki.katalog.service;

import com.niki.katalog.DAO.DeletedItemDAO;
import com.niki.katalog.DAO.ItemDAO;
import com.niki.katalog.entity.DeletedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeleteItemService implements IDeletedItemService{
    private DeletedItemDAO dao;

    @Autowired
    public DeleteItemService(DeletedItemDAO theDao) {
        dao = theDao;
    }

    @Override
    @Transactional
    public List<DeletedItem> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public void delete(String itemKey) {
        dao.delete(itemKey);
    }

    @Override
    @Transactional
    public void add(DeletedItem item) {
        dao.add(item);
    }

    @Override
    @Transactional
    public int getIdByKey(String key) {
        System.out.println("123412");
        return dao.getIdByKey(key);
    }

    @Override
    @Transactional
    public void update(DeletedItem item) {
        dao.update(item);
    }


    @Override
    @Transactional
    public DeletedItem find(int id) {
        return dao.find(id);
    }

    @Override
    @Transactional
    public List<DeletedItem> getItemsByStorage(String storageName) {
        return dao.getDeletedItemsByStorage(storageName);
    }

    @Override
    @Transactional
    public List<DeletedItem> getItemsByType(String typeName) {
        return dao.getDeletedItemsByType(typeName);
    }
}
