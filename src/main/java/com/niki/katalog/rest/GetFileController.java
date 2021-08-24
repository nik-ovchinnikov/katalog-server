package com.niki.katalog.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.niki.katalog.service.FileStorageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
@CrossOrigin
public class GetFileController {

    @Autowired
    FileStorageService service;

    @PostMapping("/addFile")
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        service.fileUpload(file);
    }

    @GetMapping("/getFiles")
    public String getSmth() {
       System.out.println(324234);
       return "Hello!!!";
    }

//    @GetMapping(
//        value = "/getFile/{fileName}",
//        produces = MediaType.IMAGE_JPEG_VALUE
//    )
//    public byte[] getFile(@PathVariable String fileName) throws IOException {
//        MultipartFile mpFile = service.getFileByName(fileName);
//        System.out.println(mpFile.getBytes());
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        System.out.println(baos);
//        baos.write(mpFile.getBytes());
//        byte[] array = baos.toByteArray();
//        System.out.println(array.length);
//        return array;
//    }
}

