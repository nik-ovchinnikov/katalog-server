package com.niki.katalog.DAO;

import com.niki.katalog.entity.ItemPicture;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemPictureRepositoryImpl{
    private EntityManager entityManger;

    @Autowired
    public ItemPictureRepositoryImpl(EntityManager entityManger) {
        this.entityManger = entityManger;
    }

    public void updateItemPicture(ItemPicture theItemPicture) {

        System.out.println("========######" + theItemPicture);
        Session currentSession = entityManger.unwrap(Session.class);
        currentSession.update(theItemPicture);

    }

    public List<ItemPicture> getByItemIdWithDetach (int theItemId) {
        Session currentSession = entityManger.unwrap(Session.class);
        Query<ItemPicture> query = currentSession.createQuery("from ItemPicture ip where ip.item.id = :theItemId");
        query.setParameter("theItemId", theItemId);
        List<ItemPicture> itemPicturesList = query.getResultList();
        for(ItemPicture ip: itemPicturesList) {
           currentSession.detach(ip);
        }
        currentSession.close();
        return itemPicturesList;
    }

    public List<ItemPicture> getByItemId (int theItemId) {
        Session currentSession = entityManger.unwrap(Session.class);
        Query<ItemPicture> query = currentSession.createQuery("from ItemPicture ip where ip.item.id = :theItemId");
        query.setParameter("theItemId", theItemId);
        List<ItemPicture> itemPicturesList = query.getResultList();
        return itemPicturesList;
    }

    public void delete(ItemPicture ip) {

        System.out.println(123123123);
        Session currentSession = entityManger.unwrap(Session.class);
        entityManger.remove(ip);
    }

    public void deleteSQLbyID() {

        Session currentSession = entityManger.unwrap(Session.class);
        Query<ItemPicture> query = currentSession.createQuery("delete from ItemPicture ip where ip.toDelete = :deleteStatus ");
        query.setParameter("deleteStatus", true);
        query.executeUpdate();
    }
}
