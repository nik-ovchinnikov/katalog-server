package com.niki.katalog.DAO;

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
    Storage storage = (Storage) query.getSingleResult();
    currentSession.delete(storage);
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
}
