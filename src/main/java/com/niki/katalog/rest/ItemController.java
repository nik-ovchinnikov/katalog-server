package com.niki.katalog.rest;

import com.niki.katalog.entity.Item;
import com.niki.katalog.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {
    private ItemService service;

    @Autowired
    public ItemController(ItemService theService) {
        service = theService;
    }

    @PostMapping("/addItem")
    public Item addItem(@RequestBody Item item) {
        System.out.println("Success");
        item.setId(0);
        service.add(item);
        return item;
    }

    @GetMapping("/getAll")
    public List<Item> getItems() {
        return service.findAll();
    }

    @PutMapping("/updateItem")
    public Item updateItem(@RequestBody Item newItem) {
       // String newItemKey = newItem.getKey();
//        int id= service.getIdByKey(newItemKey);
//        newItem.setId(id);
        System.out.println(newItem);
        service.update(newItem);
        return newItem;
    }

    @DeleteMapping("/deleteItem/{itemKey}")
    public void deleteStorage(@PathVariable String itemKey) {
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

}
