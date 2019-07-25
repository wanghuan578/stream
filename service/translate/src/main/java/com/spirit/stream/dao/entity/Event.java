package com.spirit.stream.dao.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resourceId;
    private String resourceName;
    private String fileFormat;
    @OneToMany(targetEntity=TranslateBizInfo.class, mappedBy = "event")
    private List<TranslateBizInfo> translateBizInfoList;
}
