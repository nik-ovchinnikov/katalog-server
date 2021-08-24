package com.niki.katalog.service;

import com.niki.katalog.entity.ItemPicture;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemPictureService {
    ItemPicture addItemPicture(ItemPicture theItemPicture);
    void delete(ItemPicture ip);
    List<ItemPicture> getByItemId(int theItemId);

    @Transactional
    List<ItemPicture> getByItemIdWithDetach(int theItemId);

    ItemPicture editItemPicture(ItemPicture itemPicture);
    List<ItemPicture> getAll();
    ItemPicture getById(int theId);

    ItemPicture updateItemPicture(ItemPicture theItemPicture);

    void saveOldNames(int theItemId);

    void deleteSQL();
}
