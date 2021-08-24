package com.niki.katalog.DAO;

import com.niki.katalog.entity.Item;
import com.niki.katalog.entity.ItemType;
import com.niki.katalog.entity.Storage;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import org.hibernate.query.Query;

@Repository
public class StorageDAO implements IStorageDAO {

  private EntityManager entityManager;

  @Autowired
  public StorageDAO(EntityManager theEntityManager) {
    entityManager = theEntityManager;
  }

  @Override
  public List<Storage> findAll() {

    Session currentSession = entityManager.unwrap(Session.class);
    Query<Storage> theQuery =
            currentSession.createQuery("from Storage");

    List<Storage> storages = theQuery.getResultList();

    return storages;
  }

  @Override
  public void delete(String storageName) {
    Session currentSession = entityManager.unwrap(Session.class);
    Query query = currentSession.createQuery("from Storage where name = :paramName");
    query.setParameter("paramName", storageName);
    Storage recievedStorage = (Storage) query.getSingleResult();
    //Заменяеи на значение по умолчанию
    //Достаём предмет
    query = currentSession.createQuery("from Item where storage.id = :recievedStorageId");
    query.setParameter("recievedStorageId", recievedStorage.getId());
    List<Item> items = query.getResultList();
    //На случай, если содержаться предметы в данном хранилище
    if(items.size() != 0) {
      for(Item item : items) {
        //Достаём значение по умолчанию для хранилища и делаем ссылку
        query = currentSession.createQuery("from Storage  where id = -1");
        Storage defaultStorage = (Storage) query.getSingleResult();
        item.setStorage(defaultStorage);
        currentSession.update(item);
      }
    }
    //Удаляем хранилище
    currentSession.delete(recievedStorage);
  }

  @Override
  public void add(Storage storage) {
    Session currentSession = entityManager.unwrap(Session.class);
    currentSession.save(storage);
  }

  @Override
  public void update(Storage newStorage) {
    Session currentSession = entityManager.unwrap(Session.class);
    currentSession.update(newStorage);
    System.out.println("Success!!!");
  }

  @Override
  public Storage find(int id) {
    Session currentSession = entityManager.unwrap(Session.class);
    Storage storage = currentSession.get(Storage.class, id);
    return storage;
  }

  @Override
  public boolean isExistByName(String itemPlaceName) {
    Session currentSession = entityManager.unwrap(Session.class);
    Query<ItemType> theQuery =
            currentSession.createQuery("from Storage it where it.name = :curItemPlaceName");
    theQuery.setParameter("curItemPlaceName", itemPlaceName);
    List<ItemType> itemTypes = theQuery.getResultList();
    if(itemTypes.size() > 0) {
      return true;
    }else {
      return false;
    }

  }
}
