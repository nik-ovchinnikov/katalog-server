package com.niki.katalog.service;

import com.niki.katalog.DAO.ItemDAO;
import com.niki.katalog.DAO.ItemPictureRepository;
import com.niki.katalog.entity.Item;
import com.niki.katalog.entity.ItemPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class ItemService implements  IItemService{
    private ItemDAO dao;
    private ItemPictureServiceImpl itemPictureServiceImpl;
    private ItemPictureService itemPictureService;
    private FileStorageService fileStorageService;

    public ItemService(ItemDAO dao, ItemPictureService theItemPictureService, ItemPictureServiceImpl itemPictureServiceImpl, FileStorageService fileStorageService) {
        this.dao = dao;
        this.itemPictureServiceImpl = itemPictureServiceImpl;
        this.fileStorageService = fileStorageService;
        this.itemPictureService = theItemPictureService;
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
        System.out.println(item);
        dao.add(item);
    }

    @Override
    @Transactional
    public int getIdByKey(String key) {
        return dao.getIdByKey(key);
    }

    @Override
    @Transactional
    public void update(Item item) throws IOException {

        List<ItemPicture> itemPicturesNew = item.getItemPicture();

        //переименовать староые изменённые файлы фото
        for (ItemPicture itemPictureNew: itemPicturesNew) {
            itemPictureNew.setItem(item);
            if(itemPictureNew.getId() == 0) {
               itemPictureServiceImpl.addItemPicture(itemPictureNew);
            } else {
                itemPictureServiceImpl.updateItemPicture(itemPictureNew);
                if (!itemPictureNew.isToDelete()) {
                    String oldFileName = itemPictureNew.getPreviousName();
                    String newFileName = itemPictureNew.getName();

                    //переименовываем изменённый файл
                    File file = new File("../photoStorage/" + oldFileName);
                    file.createNewFile();
                    File newFile = new File("../photoStorage/" + newFileName);
                    file.renameTo(newFile);

                }else{
                    //удаляем файл
                    String oldFileName = itemPictureNew.getPreviousName();
                    File file = new File("../photoStorage/" + oldFileName);
                    file.createNewFile();
                    file.delete();

//                        System.out.println(itemPictureNew);
//                        itemPictureServiceImpl.delete(itemPictureNew);
//                        System.out.println("Попытка удалить файл");
                }
            }
        }
        //удалить те, которых нет в этом массиве

        itemPictureServiceImpl.deleteSQL();


        //удалить удалённые старые старые (id нет в новом массиве картинок)
        //1)создать массив из id
        //2)для каждного старого обхекта проверять наличик в созданном в п.1 массиве
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

    @Override
    @Transactional
    public boolean isExistByKey(String itemKey) {
        return dao.isExistByKey(itemKey);
    }
    @Override
    public boolean isExistByPhotoName(String photoName) {
        return dao.isExistByPhotoName(photoName);
    }
}
