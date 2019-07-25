package com.spirit.stream.dao.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "translate_biz_info")
public class TranslateBizInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String outBiteRate;
    private String outFileName;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean isProportion;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 2)
    private Integer status;
    @ManyToOne(targetEntity=Event.class)
    @JoinColumn(name="event_id",referencedColumnName="id")
    private Event event;
}