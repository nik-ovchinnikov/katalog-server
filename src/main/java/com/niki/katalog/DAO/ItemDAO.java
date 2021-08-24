package com.niki.katalog.DAO;

import com.niki.katalog.entity.DeletedItem;
import com.niki.katalog.entity.Item;
import com.niki.katalog.entity.ItemPicture;
import com.niki.katalog.entity.ItemType;
import com.niki.katalog.service.FileStorageService;
import com.niki.katalog.service.ItemPictureService;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.File;
import java.util.List;

@Repository
public class ItemDAO implements IItemDAO{

    private EntityManager entityManager;
    private ItemPictureService itemPictureService;
    private FileStorageService fileStorageService;

    @Autowired
    public ItemDAO(
            EntityManager theEntityManager,
            ItemPictureService theItemPictureService,
            FileStorageService theFileStorageService
    ){
        entityManager = theEntityManager;
        itemPictureService = theItemPictureService;
        fileStorageService = theFileStorageService;
    }

    @Autowired
    public ItemPictureRepository itemPictureRepository;

    @Override
    public List<Item> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Item> theQuery =
                currentSession.createQuery("from Item");

        List<Item> items = theQuery.getResultList();

        //для каждого предмета привяжем ItemPicture
        for(Item item: items) {

            List<ItemPicture> itemPicturesToSend= itemPictureService.getByItemId(item.getId());
            for (ItemPicture itemPicture: itemPicturesToSend) {
                //Добавляем набор картинок
                    item.getItemPicture().add(itemPicture);
            }
        }
        
        return items;
    }

    @Override
    public void delete(String itemKey) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from Item where key = :paramName");
        query.setParameter("paramName", itemKey);
        Item item = (Item) query.getSingleResult();

        //Запись в таблицу для удалённых
        DeletedItem deletedItem = new DeletedItem();
            deletedItem.setId(item.getId());
            deletedItem.setDescription(item.getDescription());
            deletedItem.setIncomeDate(item.getIncomeDate());
            deletedItem.setKey(item.getKey());
            deletedItem.setName(item.getName());
            deletedItem.setStorageName(item.getStorage().getName());
            deletedItem.setTypeName(item.getItemType().getName());
        currentSession.save(deletedItem);

        //Удаляем картинки и данные о картинках
        List<ItemPicture> itemPicturesToDelete = itemPictureService.getByItemId(item.getId());
        for (ItemPicture itemPicture: itemPicturesToDelete) {
            //удаляеи данные о картинках
            itemPictureService.delete(itemPicture);

            //удаляем картинки
            File fileToDelete = new File(fileStorageService.getStorageRoot(), itemPicture.getName());
            fileToDelete.delete();
        }

        currentSession.delete(item);
    }

    @Override
    public void add(Item item) {
        Session currentSession = entityManager.unwrap(Session.class);
        //Запись данных о картинках
        for (ItemPicture picture: item.getItemPicture()) {
            picture.setItem(item);
            itemPictureService.addItemPicture(picture);
        }
        currentSession.save(item);
    }

    @Override
    public void update(Item item) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.update(item);

    }

    @Override
    public int getIdByKey(String key) {
        System.out.println("Has come");
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from Item where key = :paramName");
        query.setParameter("paramName", key);
        Item item = (Item) query.getSingleResult();

        return item.getId();
    }

    @Override
    public Item find(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Item item = currentSession.get(Item.class, id);

        //Забираем данные о фотографиях предмета
        List<ItemPicture> itemPicturesToReturn = itemPictureService.getByItemId(item.getId());
        item.setItemPicture(itemPicturesToReturn);

        return item;
    }

    @Override
    public List<Item> getItemsByStorage(String storageName) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from Item where storage.name= :paramName");
        query.setParameter("paramName", storageName);
        List<Item> items = query.getResultList();

        //Забрать данные о фотографиях для каждого предмета
        for (Item item: items) {
            List<ItemPicture> itemPicturesToReturn = itemPictureService.getByItemId(item.getId());
            item.setItemPicture(itemPicturesToReturn);
        }
        //Вернуть фотографии

        return items;
    }

    @Override
    public List<Item> getItemsByType(String typeName) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from Item where itemType.name = :paramName");
        query.setParameter("paramName", typeName);
        List<Item> items = query.getResultList();

        //Забрать данные о фотографиях для каждого предмета
        for (Item item: items) {
            List<ItemPicture> itemPicturesToReturn = itemPictureService.getByItemId(item.getId());
            item.setItemPicture(itemPicturesToReturn);
        }
        //Вернуть фотографии

        return items;
    }


    @Override
    public boolean isExistByKey(String itemKey) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Item> theQuery =
                currentSession.createQuery("from Item it where it.key = :curItemKey");
        theQuery.setParameter("curItemKey", itemKey);
        List<Item> items = theQuery.getResultList();
        if(items.size() > 0) {
            return true;
        }else {
            return false;
        }

    }

    public boolean isExistByPhotoName(String photoName) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<ItemPicture> theQuery =
                currentSession.createQuery("from ItemPicture ip where ip.name= :curPhotoName");
        theQuery.setParameter("curPhotoName", photoName);
        List<ItemPicture> itemPictures = theQuery.getResultList();
        if(itemPictures.size() > 0) {
            return true;
        }else {
            return false;
        }

    }
}
