package com.deepazure.visualdata.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "train_history")
public class TrainHistory extends BaseEntity {

    @Column(name = "machine_id", columnDefinition = "INT DEFAULT NULL COMMENT '设备ID'")
    private Integer machineId;

    @Column(name = "user_id", columnDefinition = "BIGINT DEFAULT NULL COMMENT '学院ID'")
    private Long userId;

    @Column(name = "student_name", columnDefinition = "VARCHAR(45) DEFAULT NULL COMMENT '学员姓名'")
    private String studentName;

    @Column(name = "course_id", columnDefinition = "BIGINT DEFAULT 0 COMMENT '课程ID'")
    private Long courseId;

    @Column(name = "course_title", columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '课程标题'")
    private String courseTitle;

    @Column(name = "course_defined_fault_ids", columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '课程定义的故障ID序列'")
    private String courseDefinedFaultIds;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "course_create_time", columnDefinition = "DATETIME DEFAULT NULL COMMENT '课程创建时间'")
    private Date courseCreateTime;

    @Column(name = "used_time", columnDefinition = "INT DEFAULT 0 COMMENT '训练所用时间'")
    private Integer usedTime;

    @Column(name = "find_faults_ids", columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '发现的故障ID序列'")
    private String findFaultsIds;

    @Column(name = "missing_faults_ids", columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '未发现的故障ID序列'")
    private String missingFaultsIds;

    @Column(name = "score", columnDefinition = "INT DEFAULT 0 COMMENT '训练得分'")
    private Integer score;

    @Column(name = "reason", columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '评语'")
    private String reason;

}
