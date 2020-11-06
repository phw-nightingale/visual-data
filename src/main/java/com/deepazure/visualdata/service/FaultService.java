package com.deepazure.visualdata.service;

import com.deepazure.visualdata.entity.Fault;

import java.util.List;

public interface FaultService extends BaseService<Fault> {

    List<String> findDistinctComponentNamesLike(String name);

}
