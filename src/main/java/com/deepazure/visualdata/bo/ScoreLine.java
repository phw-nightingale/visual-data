package com.deepazure.visualdata.bo;

import com.deepazure.visualdata.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class ScoreLine implements Serializable {

    private Long studentId;
    private User student;
    private List<Integer> scores;

}
