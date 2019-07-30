package com.spirit.stream.dao.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "event")
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resourceId;
    private String resourceName;
    @OneToMany(targetEntity= TranslateBizInfo.class, mappedBy = "event")
    private List<TranslateBizInfo> translateBizInfoList;
}
