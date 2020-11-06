package com.deepazure.visualdata.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Getter
@Setter
@Entity
@Table(name = "device")
public class Device extends BaseEntity {

    @Column(name = "name", columnDefinition = "VARCHAR(45) DEFAULT NULL COMMENT '设备名称'")
    private String name;

    @Column(name = "no", columnDefinition = "VARCHAR(45) DEFAULT NULL COMMENT '设备号'")
    private String no;

    @Column(name = "status", columnDefinition = "VARCHAR(45) DEFAULT 'OFFLINE' COMMENT '设备状态，离线，在线'")
    private String status;

    @Column(name = "user_id", columnDefinition = "BIGINT DEFAULT NULL COMMENT '当前登录该设备用户'")
    private Long userId;

    @Transient
    private String username;

    @Transient
    private User user;

}
