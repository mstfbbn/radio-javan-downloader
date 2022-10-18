package com.radiojavan.downloader.controllers;

import com.radiojavan.downloader.models.ResponseModel;
import com.radiojavan.downloader.services.DownloaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("downloader")
public class DownloaderController {

    @Autowired
    private DownloaderService downloaderService;

    @GetMapping("file-url")
    public ResponseModel<String> getFileUrl(@RequestParam String path) {
        return new ResponseModel<>(downloaderService.getFileUrl(path));
    }
}
