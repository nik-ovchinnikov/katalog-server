package com.niki.katalog.service;

import com.niki.katalog.entity.ItemType;

import java.util.List;

public interface IItemTypeService {
    public List<ItemType> findAll();

    public void delete(String typeName);

    public void add(ItemType storage);

    public void update(ItemType storage);

    public ItemType find(int id);
}
