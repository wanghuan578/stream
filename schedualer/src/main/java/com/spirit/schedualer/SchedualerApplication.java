package com.spirit.schedualer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableFeignClients
@Slf4j
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class SchedualerApplication {

    @Autowired
    FeignClientTest feignClientTest;

    public static void main(String[] args) {
        SpringApplication.run(SchedualerApplication.class, args);
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam("name") String name) {
        log.info("SchedualerApplication: upload, fileName: {}", name);
        return feignClientTest.upload(name);
    }
}
