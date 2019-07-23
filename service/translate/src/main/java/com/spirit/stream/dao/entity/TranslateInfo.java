package com.spirit.stream.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_translate_info")
public class TranslateInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String srcBiteRate;
    private String destBiteRates;
    private String srcFileName;
    private String destFileNames;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean isProportion;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 2)
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSrcBiteRate() {
        return srcBiteRate;
    }

    public void setSrcBiteRate(String srcBiteRate) {
        this.srcBiteRate = srcBiteRate;
    }

    public String getDestBiteRates() {
        return destBiteRates;
    }

    public void setDestBiteRates(String destBiteRates) {
        this.destBiteRates = destBiteRates;
    }

    public String getSrcFileName() {
        return srcFileName;
    }

    public void setSrcFileName(String srcFileName) {
        this.srcFileName = srcFileName;
    }

    public String getDestFileNames() {
        return destFileNames;
    }

    public void setDestFileNames(String destFileNames) {
        this.destFileNames = destFileNames;
    }

    public boolean isProportion() {
        return isProportion;
    }

    public void setProportion(boolean proportion) {
        isProportion = proportion;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
