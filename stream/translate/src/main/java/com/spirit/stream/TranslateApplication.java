package com.spirit.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TranslateApplication {

    public static void main(String[] args) {
        SpringApplication.run(TranslateApplication.class, args);
    }

    @RequestMapping("/test")
    public String test() {
        return "success";
    }

}
