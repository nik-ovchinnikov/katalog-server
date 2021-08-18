package com.niki.katalog.service;

import com.niki.katalog.entity.ItemPicture;

import java.util.List;

public interface ItemPictureService {
    ItemPicture addItemPicture(ItemPicture theItemPicture);
    void delete(int theId);
    List<ItemPicture> getByItemKey(Integer theItemKey);
    ItemPicture editItemPicture(ItemPicture itemPicture);
    List<ItemPicture> getAll();
}
