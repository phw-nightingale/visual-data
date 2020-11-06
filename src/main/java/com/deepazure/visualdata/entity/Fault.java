package com.deepazure.visualdata.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "fault")
public class Fault extends BaseEntity {

    @Column(name = "transport_department", columnDefinition = "VARCHAR(45) DEFAULT NULL COMMENT '机车部门'")
    private String transportDepartment;

    @Column(name = "expect_steps", columnDefinition = "VARCHAR(45) DEFAULT NULL COMMENT '预计步数'")
    private String expectSteps;

    @Column(name = "component_name", columnDefinition = "VARCHAR(45) DEFAULT NULL COMMENT '部件名称'")
    private String componentName;

    @Column(name = "code_number", columnDefinition = "VARCHAR(45) DEFAULT NULL COMMENT '编号'")
    private String codeNumber;

    @Column(name = "unreal_actor_name", columnDefinition = "VARCHAR(45) DEFAULT NULL COMMENT 'UE4物体对应名称'")
    private String unrealActorName;

    @Column(name = "unreal_actor_child_name", columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '子组件名称'")
    private String unrealActorChildName;

    @Column(name = "fault_name", columnDefinition = "VARCHAR(45) DEFAULT NULL COMMENT '故障名称'")
    private String faultName;

    @Column(name = "performance_type", columnDefinition = "VARCHAR(45) DEFAULT NULL COMMENT '表现形式'")
    private String performanceType;

    @Column(name = "unreal_fault_name", columnDefinition = "VARCHAR(45) DEFAULT NULL COMMENT 'UE4故障名称'")
    private String unrealFaultName;

    @Column(name = "material_name", columnDefinition = "VARCHAR(45) DEFAULT NULL COMMENT '材质名称'")
    private String materialName;

    @Column(name = "material_id", columnDefinition = "VARCHAR(45) DEFAULT NULL COMMENT '材质ID'")
    private String materialId;

    @Column(name = "perset_fault_name", columnDefinition = "VARCHAR(45) DEFAULT NULL COMMENT '预设故障名称'")
    private String presetFaultName;

}
