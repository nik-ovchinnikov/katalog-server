package com.niki.katalog.service;

import com.niki.katalog.DAO.ItemTypeDAO;
import com.niki.katalog.DAO.StorageDAO;
import com.niki.katalog.entity.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemTypeService implements IItemTypeService{
    private ItemTypeDAO dao;

    @Autowired
    public ItemTypeService(ItemTypeDAO theDao){
        dao = theDao;
    }

    @Override
    @Transactional
    public List<ItemType> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public void delete(String typeName) {
        dao.delete(typeName);
    }

    @Override
    @Transactional
    public void add(ItemType itemType) {
        dao.add(itemType);
    }

    @Override
    @Transactional
    public void update(ItemType itemType) {
        dao.update(itemType);
    }


    @Override
    @Transactional
    public ItemType find(int id) {
        return dao.find(id);
    }
}
