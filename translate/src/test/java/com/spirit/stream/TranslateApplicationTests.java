package com.spirit.stream;



import com.alibaba.fastjson.JSON;
import com.spirit.common.entity.Event;
import com.spirit.stream.mq.upload.UploadSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TranslateApplicationTests {

    @Autowired
    private UploadSender uploadSender;

    @Test
    public void putQueue() {
        Event e = new Event();
        e.setResourceId("123");
        uploadSender.send(JSON.toJSONString(e, true));
    }

}
