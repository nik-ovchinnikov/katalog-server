package com.niki.katalog.DAO;

import com.niki.katalog.entity.DeletedItem;
import com.niki.katalog.entity.Item;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ItemDAO implements IItemDAO{

    private EntityManager entityManager;

    @Autowired
    public ItemDAO(EntityManager theEntityManager){entityManager = theEntityManager;}

    @Override
    public List<Item> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Item> theQuery =
                currentSession.createQuery("from Item");

        List<Item> items = theQuery.getResultList();

        return items;
    }

    @Override
    public void delete(String itemKey) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from Item where key = :paramName");
        query.setParameter("paramName", itemKey);
        Item item = (Item) query.getSingleResult();
        System.out.println(item);
        //Запись в таблицу для удалённых
        DeletedItem deletedItem = new DeletedItem();
            deletedItem.setId(item.getId());
            deletedItem.setDescription(item.getDescription());
            deletedItem.setImgPaths(item.getImgPaths());
            deletedItem.setIncomeDate(item.getIncomeDate());
            deletedItem.setKey(item.getKey());
            deletedItem.setName(item.getName());
            deletedItem.setStorageName(item.getStorageName());
            //deletedItem.setTypeName(item.getItemType());
            deletedItem.setTypeName("Test###");
        currentSession.save(deletedItem);

        currentSession.delete(item);
    }

    @Override
    public void add(Item item) {
        Session currentSession = entityManager.unwrap(Session.class);
        System.out.println(item);
//        System.out.println(item.getItemType().getN);

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
        System.out.println(item);
        return item.getId();
    }

    @Override
    public Item find(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Item item = currentSession.get(Item.class, id);
        return item;
    }

    @Override
    public List<Item> getItemsByStorage(String storageName) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from Item where StorageName= :paramName");
        query.setParameter("paramName", storageName);
        List<Item> items = query.getResultList();
        System.out.println(items);
        return items;
    }

    @Override
    public List<Item> getItemsByType(String typeName) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from Item where itemType.name = :paramName");
        query.setParameter("paramName", typeName);
        List<Item> items = query.getResultList();
        System.out.println(items);
        return items;
    }
}
