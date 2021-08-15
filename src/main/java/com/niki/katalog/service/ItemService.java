package com.niki.katalog.service;

import com.niki.katalog.DAO.ItemDAO;
import com.niki.katalog.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService implements  IItemService{
    private ItemDAO dao;

    @Autowired
    public ItemService(ItemDAO theDao) {
       dao = theDao;
    }

    @Override
    @Transactional
    public List<Item> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public void delete(String itemKey) {
        dao.delete(itemKey);
    }

    @Override
    @Transactional
    public void add(Item item) {
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
    public void update(Item item) {
        dao.update(item);
    }


    @Override
    @Transactional
    public Item find(int id) {
        return dao.find(id);
    }

    @Override
    @Transactional
    public List<Item> getItemsByStorage(String storageName) {
        return dao.getItemsByStorage(storageName);
    }

    @Override
    @Transactional
    public List<Item> getItemsByType(String typeName) {
        return dao.getItemsByType(typeName);
    }
}
