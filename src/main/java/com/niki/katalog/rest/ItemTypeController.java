package com.niki.katalog.rest;


import com.niki.katalog.entity.ItemType;
import com.niki.katalog.entity.ItemType;
import com.niki.katalog.entity.Storage;
import com.niki.katalog.service.ItemTypeService;
import com.niki.katalog.service.ItemTypeService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemType")
@CrossOrigin
public class ItemTypeController {
    private ItemTypeService service;

    @Autowired
    public ItemTypeController(ItemTypeService theService) {
        service = theService;
    }

    @PostMapping("/addItemType")
    public ItemType addItemType(@RequestBody ItemType itemType) {
        itemType.setId(0);
        service.add(itemType);
        return itemType;
    }

    @GetMapping("/getAll")
    public List<ItemType> getItemTypes() {
        return service.findAll();
    }

    @PutMapping("/updateItemType")
    public ItemType updateItemType(@RequestBody ItemType newItemType) {
        service.update(newItemType);
        return newItemType;
    }

    @DeleteMapping("/deleteItemType/{itemTypeName}")
    public void deleteStorage(@PathVariable String itemTypeName) {
        service.delete(itemTypeName);
    }

    @GetMapping("/getById/{itemTypeId}")
    public ItemType getItemType(@PathVariable int itemTypeId) {
        return service.find(itemTypeId);
    }

    @GetMapping("/isExist/{itemTypeName}")
    public boolean isExistType(@PathVariable String itemTypeName) {
        return  service.isExistByName(itemTypeName);
    }

}
