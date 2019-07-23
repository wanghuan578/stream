package com.spirit.stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class TranslateApplication {

    @Value("${logging.path}")
    private String logPath;

    public static void main(String[] args) {
        SpringApplication.run(TranslateApplication.class, args);
    }

    @RequestMapping("/test")
    public String test() {
        return logPath;
    }

}
