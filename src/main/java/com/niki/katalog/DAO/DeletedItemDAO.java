package com.niki.katalog.DAO;

import com.niki.katalog.entity.DeletedItem;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DeletedItemDAO implements IDeletedItemDAO{
    private EntityManager entityManager;

    @Autowired
    public DeletedItemDAO(EntityManager theEntityManager){entityManager = theEntityManager;}

    @Override
    public List<DeletedItem> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<DeletedItem> theQuery =
                currentSession.createQuery("from DeletedItem");

        List<DeletedItem> items = theQuery.getResultList();

        return items;
    }

    @Override
    public void delete(String itemKey) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from DeletedItem where key = :paramName");
        query.setParameter("paramName", itemKey);
        DeletedItem item = (DeletedItem) query.getSingleResult();
        System.out.println(item);
        currentSession.delete(item);
    }

    @Override
    public void add(DeletedItem item) {
        Session currentSession = entityManager.unwrap(Session.class);
        System.out.println(item);
        currentSession.save(item);
    }

    @Override
    public void update(DeletedItem item) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.update(item);

    }

    @Override
    public int getIdByKey(String key) {
        System.out.println("Has come");
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from DeletedItem where key = :paramName");
        query.setParameter("paramName", key);
        DeletedItem item = (DeletedItem) query.getSingleResult();
        System.out.println(item);
        return item.getId();
    }

    @Override
    public DeletedItem find(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        DeletedItem item = currentSession.get(DeletedItem.class, id);
        return item;
    }

    @Override
    public List<DeletedItem> getDeletedItemsByStorage(String storageName) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from DeletedItem where StorageName= :paramName");
        query.setParameter("paramName", storageName);
        List<DeletedItem> items = query.getResultList();
        System.out.println(items);
        return items;
    }

    @Override
    public List<DeletedItem> getDeletedItemsByType(String typeName) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from DeletedItem where typeName = :paramName");
        query.setParameter("paramName", typeName);
        List<DeletedItem> items = query.getResultList();
        System.out.println(items);
        return items;
    }
}
