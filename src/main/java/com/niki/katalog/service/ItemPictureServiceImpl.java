package com.niki.katalog.service;

import com.niki.katalog.DAO.ItemPictureRepository;
import com.niki.katalog.entity.ItemPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemPictureServiceImpl implements ItemPictureService{

    @Autowired
    private ItemPictureRepository itemPictureRepository;

    @Override
    @Transactional
    public ItemPicture addItemPicture(ItemPicture theItemPicture) {
        ItemPicture savedItemPicture = itemPictureRepository.save(theItemPicture);
        return savedItemPicture;
    }

    @Override
    @Transactional
    public void delete(int theId) {
        itemPictureRepository.deleteById(theId);
    }

    @Override
    @Transactional
    public List<ItemPicture> getByItemKey(Integer theItemKey) {
        return (List<ItemPicture>) itemPictureRepository.getByItemKey(theItemKey);
    }


    @Override
    @Transactional
    public ItemPicture editItemPicture(ItemPicture theItemPicture) {
        return itemPictureRepository.save(theItemPicture);
    }

    @Override
    @Transactional
    public List<ItemPicture> getAll() {
        return itemPictureRepository.findAll();
    }
}
