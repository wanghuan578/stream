package com.spirit.stream.adaptor;


import com.spirit.stream.dao.entity.TranslateBizInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class FFmpegAdaptor {

    public int translateCode(TranslateBizInfo info) {

        Process process = null;
        int exitValue = 0;

        try {
            process = Runtime.getRuntime().exec("ffmpeg -i apple_1280x720.MP4 -vf scale=640:480 1.mp4 -hide_banner");
            exitValue = process.waitFor();
            if (0 != exitValue) {
                log.error("call shell failed. error code is :" + exitValue);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int pullAndPushStream() {
        return 0;
    }

    public int record() {
        return 0;
    }
}
