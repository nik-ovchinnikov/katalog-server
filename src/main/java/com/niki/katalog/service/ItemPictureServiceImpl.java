package com.niki.katalog.service;

import com.niki.katalog.DAO.ItemPictureRepository;
import com.niki.katalog.DAO.ItemPictureRepositoryImpl;
import com.niki.katalog.entity.ItemPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemPictureServiceImpl implements ItemPictureService{


    private ItemPictureRepository itemPictureRepository;
    private ItemPictureRepositoryImpl itemPictureRepositoryImpl;

    @Autowired
    public ItemPictureServiceImpl(ItemPictureRepository itemPictureRepository, ItemPictureRepositoryImpl itemPictureRepositoryImpl) {
        this.itemPictureRepository = itemPictureRepository;
        this.itemPictureRepositoryImpl = itemPictureRepositoryImpl;
    }

    @Override
    @Transactional
    public ItemPicture addItemPicture(ItemPicture theItemPicture1) {
        //устанавливается место хранения
        theItemPicture1.setPath("D:\\Workspace\\Projects\\Katalog\\photoStorage\\");

        ItemPicture savedItemPicture = itemPictureRepository.save(theItemPicture1);
        return savedItemPicture;
    }

    @Override
    @Transactional
    public void delete(ItemPicture ip) {
        itemPictureRepositoryImpl.delete(ip);
    }

    @Override
    @Transactional
    public List<ItemPicture> getByItemId(int theItemId) {
        return (List<ItemPicture>) itemPictureRepositoryImpl.getByItemId(theItemId);
    }


    @Override
    @Transactional
    public List<ItemPicture> getByItemIdWithDetach(int theItemId) {
        return (List<ItemPicture>) itemPictureRepositoryImpl.getByItemIdWithDetach(theItemId);
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

    @Override
    public ItemPicture getById(int theId) {
        return itemPictureRepository.getById(theId);
    }

    @Override
    public ItemPicture updateItemPicture(ItemPicture theItemPicture) {
        System.out.println("++++++++++++++" + theItemPicture);
        itemPictureRepositoryImpl.updateItemPicture(theItemPicture);

        return theItemPicture;
    }

    @Override
    public void saveOldNames(int theItemId) {
       List<ItemPicture> itemPictures = getByItemId(theItemId);

       for (ItemPicture ip: itemPictures) {
           ip.setPreviousName(ip.getName());
           updateItemPicture(ip);
       }
    }

    @Override
    public void deleteSQL() {
       itemPictureRepositoryImpl.deleteSQLbyID();
    }
}
