package com.niki.katalog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileStorageService {
    //адресс зранения файлов
    //его бы вынести в свойства глобальные, как и адрес БД
    private String storageRoot = "D:\\Workspace\\Projects\\Katalog\\photoStorage\\";

    //Загрузка полученного файла в хранилище
    public void fileUpload(MultipartFile file) throws IOException {
        file.transferTo(new File(storageRoot + file.getOriginalFilename()));
        //Добавлять поиск последнего добавленного предмета по ключу
    }

    public String getStorageRoot() {
        return storageRoot;
    }

    public void setStorageRoot(String storageRoot) {
        this.storageRoot = storageRoot;
    }
}
