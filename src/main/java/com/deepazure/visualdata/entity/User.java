package com.deepazure.visualdata.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "username", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '用户名'")
    private String username;

    @Column(name = "password", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '密码'")
    private String password;

    @Column(name = "avatar", columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '头像'")
    private String avatar;

    @Column(name = "access_token", columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '令牌'")
    private String accessToken;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "expire", columnDefinition = "DATETIME DEFAULT NULL COMMENT '令牌过期时间'")
    private Date expire;

    @Column(name = "role", columnDefinition = "VARCHAR(10) DEFAULT 'NONE' COMMENT '角色, ADMIN, TEACHER, STUDENT'")
    private String role;

}
