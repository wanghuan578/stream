package com.spirit.common.web.response.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class PageEntity<T> implements Serializable {

    private static final long serialVersionUID = 3824411723873609481L;

    private Integer page;
    private Integer size;
    private Long total;
    private Integer pageNum;
    private List<T> load;
}
