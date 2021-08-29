package com.niki.katalog.service;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FileStorageService {
    //адресс зранения файлов
    //его бы вынести в свойства глобальные, как и адрес БД
    private String storageRoot = "D:\\Workspace\\Projects\\Katalog\\photoStorage\\";
   // private String storageRoot = "E:\\Workspace\\Projects\\Katalog\\Picture\\";

    //Загрузка полученного файла в хранилище
    public void fileUpload(MultipartFile file) throws IOException {
        file.transferTo(new File(storageRoot + file.getOriginalFilename()));
        //Добавлять поиск последнего добавленного предмета по ключу
    }

    //Забирает файл по названию
    public MultipartFile getFileByName(String fileName) throws IOException {

        File file =new File(storageRoot + fileName);
        MultipartFile mpFile = new MockMultipartFile(
                file.getName(),
                new FileInputStream(file)
        );
        return mpFile;
    }

    public String getStorageRoot() {
        return storageRoot;
    }

    public void setStorageRoot(String storageRoot) {
        this.storageRoot = storageRoot;
    }
}
