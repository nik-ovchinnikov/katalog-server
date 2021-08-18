package com.niki.katalog.rest;

import com.niki.katalog.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
}

