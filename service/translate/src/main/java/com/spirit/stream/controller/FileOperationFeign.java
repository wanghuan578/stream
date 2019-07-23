package com.spirit.stream.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FileOperationFeign {
    @RequestMapping("/upload")
    public String upload(@RequestParam("name") String name){
        log.info("TranslateApplication: upload");
        return name + " upload";
    }
}
