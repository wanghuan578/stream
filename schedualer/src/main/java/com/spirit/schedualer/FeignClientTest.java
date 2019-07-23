package com.spirit.schedualer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "translate")
public interface FeignClientTest {

    @RequestMapping("/upload")
    String upload(@RequestParam("name") String name);
}
