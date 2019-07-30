package com.spirit.common.entity;

import lombok.Data;

@Data
public class TranslateInfo {
    private Long id;
    private String srcFilePath;
    private String srcFileName;
    private String outBiteRate;
    private String outFileName;
    private String uploadFilePath;
    private boolean isProportion;
    private Integer status;
    private String fileFormat;
}
