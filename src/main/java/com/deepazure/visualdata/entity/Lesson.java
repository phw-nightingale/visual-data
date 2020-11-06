package com.deepazure.visualdata.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "lesson")
public class Lesson extends BaseEntity {

    @Column(name = "title", columnDefinition = "VARCHAR(45) NOT NULL COMMENT '课程名称'")
    private String title;

    @Column(name = "description", columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '课程描述'")
    private String description;

    @Column(name = "fault_ids", columnDefinition = "VARCHAR(1024) NOT NULL COMMENT '关联的故障ID'")
    private String faultIds;

    @Column(name = "training_time", columnDefinition = "INT DEFAULT 0 COMMENT '训练时长'")
    private Float trainingTime;

    @Transient
    private List<Fault> faults;

}
