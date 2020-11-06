package com.deepazure.visualdata.bo;

import com.deepazure.visualdata.entity.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerPack {

    private Integer status;
    private String msg;
    private String to;
    private Command command;
    private Lesson lesson;

}
