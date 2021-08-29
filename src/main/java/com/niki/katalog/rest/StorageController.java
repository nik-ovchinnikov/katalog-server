package com.niki.katalog.rest;

import com.niki.katalog.entity.Storage;
import com.niki.katalog.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storage")
@CrossOrigin
public class StorageController {

  private StorageService service;

  @Autowired
  public StorageController(StorageService theService) {
      service = theService;
  }

  @GetMapping("/getAll")
  public List<Storage> getStorages() {
    return service.findAll();
  }

  @GetMapping("/getById/{storageId}")
  public Storage getStorages(@PathVariable int storageId) {
    return service.find(storageId);
  }

  @PostMapping("/addStorage")
  public Storage addStorage(@RequestBody Storage storage) {
    storage.setId(0);
    service.add(storage);
    return storage;
  }

  @PutMapping("/updateStorage")
  public Storage updateStorage(@RequestBody Storage newStorage) {
    System.out.println(newStorage);
    service.update(newStorage);
    return newStorage;
  }

  @DeleteMapping("/deleteStorage/{storageName}")
  public void deleteStorage(@PathVariable String storageName) {
    service.delete(storageName);
  }

  @GetMapping("/isExist/{itemStorageName}")
  public boolean isExistType(@PathVariable String itemStorageName) {
    return  service.isExistByName(itemStorageName);
  }

}
