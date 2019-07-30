package com.spirit.common.entity;

import lombok.Data;
import java.util.List;

@Data
public class Event {
    private Long id;
    private String resourceId;
    private String resourceName;
    private List<TranslateInfo> translateBizInfoList;
}
