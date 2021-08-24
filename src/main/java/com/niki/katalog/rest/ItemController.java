package com.niki.katalog.rest;

import com.niki.katalog.entity.Item;
import com.niki.katalog.entity.ItemPicture;
import com.niki.katalog.service.ItemPictureService;
import com.niki.katalog.service.ItemPictureServiceImpl;
import com.niki.katalog.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {
    private ItemService service;
    private ItemPictureService itemPictureService;
    private ItemPictureServiceImpl itemPictureServiceImpl;

    @Autowired
    public ItemController(ItemService service, ItemPictureService itemPictureService, ItemPictureServiceImpl itemPictureServiceImpl) {
        this.service = service;
        this.itemPictureService = itemPictureService;
        this.itemPictureServiceImpl = itemPictureServiceImpl;
    }

    @PostMapping("/addItem")
    public Item addItem(@RequestBody Item item) {
        item.setId(0);
        service.add(item);
        return item;
    }

    @GetMapping("/getAll")
    public List<Item> getItems() {
        return service.findAll();
    }

    @PutMapping("/updateItem")
    public Item updateItem(@RequestBody Item newItem) throws IOException {
        service.update(newItem);
        return newItem;
    }

    @DeleteMapping("/deleteItem/{itemKey}")
    public void deleteItem(@PathVariable String itemKey) {
        service.delete(itemKey);
    }

    @GetMapping("/getById/{itemId}")
    public Item getItem(@PathVariable int itemId) {
        return service.find(itemId);
    }

    @GetMapping("getItemsByStorage/{storageName}")
    public List<Item> getItemByStorage(@PathVariable String storageName) {
       return service.getItemsByStorage(storageName);
    }

    @GetMapping("getItemsByType/{typeName}")
    public List<Item> getItemByType(@PathVariable String typeName) {
        return service.getItemsByType(typeName);
    }

    @GetMapping("/keyIsExist/{itemKey}")
    public boolean keyIsExistType(@PathVariable String itemKey) {
        return  service.isExistByKey(itemKey);
    }

    @GetMapping("/photoNameIsExist/{photoName}")
    public boolean photoNameIsExistType(@PathVariable String photoName) {
        return  service.isExistByPhotoName(photoName);
    }

}
