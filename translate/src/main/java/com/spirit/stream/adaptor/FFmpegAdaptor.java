package com.spirit.stream.adaptor;

import com.spirit.common.Exception.MainStageException;
import com.spirit.common.constant.ResultType;
import com.spirit.stream.dao.entity.TranslateBizInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.io.IOException;
import static com.spirit.common.constant.ResultType.PARAMS_DISMATCH;

@Slf4j
@Service
public class FFmpegAdaptor {

    public int translate(TranslateBizInfo info) throws MainStageException {

        String command = StringUtils.join(new String [] {
                "ffmpeg",
                "-y",
                "-i",
                info.getSrcFilePath(),
                "-vf",
                "scale="+info.getOutBiteRate(),
                info.getOutFileName()
        }, " ");

        log.info("fork process command ---->: " + command);

        Runtime rt = null;

        try {
            rt = Runtime.getRuntime();
            Process proc = rt.exec(command);
            proc.getOutputStream().close();
            proc.getInputStream().close();
            proc.getErrorStream().close();

            int exitValue = proc.waitFor();

            if (0 != exitValue) {
                log.error("fork ffmpeg failed. error code: " + exitValue);
                throw new MainStageException("1000", String.valueOf(exitValue));
            }
            else {
                log.info("ffmpeg translate succeed.");
            }
        }
        catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new MainStageException(PARAMS_DISMATCH);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new MainStageException(PARAMS_DISMATCH);
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
