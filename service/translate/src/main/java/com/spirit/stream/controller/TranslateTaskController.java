package com.spirit.stream.controller;

import com.spirit.stream.dao.entity.Event;
import com.spirit.stream.service.EventTranslateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class TranslateTaskController {

    @Autowired
    private EventTranslateService eventTranslateService;

    @RequestMapping("/upload")
    public String upload(@RequestParam("name") String name){
        log.info("TranslateApplication: upload");
        return name + " upload";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addTask(@RequestBody Event event){
        eventTranslateService.add(event);
    }

//    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
//    public String remove(@PathVariable Long id){
//        translateService.delete(id);
//        return "OK";
//    }
//
//    @RequestMapping(value = "/modify", method = RequestMethod.POST)
//    public String modify(@RequestBody TranslateInfo info){
//        translateService.update(info);
//        return "OK";
//    }
}
