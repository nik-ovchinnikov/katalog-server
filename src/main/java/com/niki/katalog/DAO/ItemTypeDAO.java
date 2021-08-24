package com.niki.katalog.DAO;

import com.niki.katalog.entity.Item;
import com.niki.katalog.entity.ItemType;
import com.niki.katalog.entity.Storage;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
@Repository
public class ItemTypeDAO implements IITemTypeDAO {
    private EntityManager entityManager;

    @Autowired
    public ItemTypeDAO(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<ItemType> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<ItemType> theQuery =
                currentSession.createQuery("from ItemType");

        List<ItemType> itemTypes = theQuery.getResultList();

        return itemTypes;
    }

    @Override
    public void delete(String typeName) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from ItemType where name = :paramName");
        query.setParameter("paramName", typeName);
        ItemType itemTypeToDelete= (ItemType) query.getSingleResult();
        //Заменяеи на значение по умолчанию
        //Достаём предмет
        query = currentSession.createQuery("from Item where itemType.itemTypeId = :recievedId");
        query.setParameter("recievedId", itemTypeToDelete.getId());
        List<Item> items = query.getResultList();
        //На случай, если содержаться предметы данного типа
        if(items.size() != 0) {
            for(Item item : items) {
                //Достаём значение по умолчанию для типа и делаем ссылку
                query = currentSession.createQuery("from ItemType where id = -1");
                ItemType defaultType= (ItemType) query.getSingleResult();
                item.setItemType(defaultType);
                currentSession.update(item);
            }
        }

        currentSession.delete(itemTypeToDelete);
    }

    @Override
    public void add(ItemType itemType) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(itemType);
    }

    @Override
    public void update(ItemType newItemType) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.update(newItemType);
    }

    @Override
    public ItemType find(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        ItemType itemType = currentSession.get(ItemType.class, id);
        return itemType;
    }

    @Override
    public boolean isExistByName(String itemTypeName) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<ItemType> theQuery =
                currentSession.createQuery("from ItemType it where it.name = :curItemTypeName");
        theQuery.setParameter("curItemTypeName", itemTypeName);
        List<ItemType> itemTypes = theQuery.getResultList();
        if(itemTypes.size() > 0) {
           return true;
        }else {
           return false;
        }

    }
}

