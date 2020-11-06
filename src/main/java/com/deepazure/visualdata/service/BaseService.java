package com.deepazure.visualdata.service;

import com.deepazure.visualdata.entity.BaseEntity;
import com.deepazure.visualdata.util.Pager;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    T save(T item);

    List<T> save(List<T> items);

    void delete(Long id);

    void deleteAll(List<Long> ids);

    T findById(Long id);

    List<T> findByIds(List<Long> ids);

    List<T> findByIds(String ids);

    T getOne(Long id);

    T getOne(T exam);

    List<T> findAll(T exam);

    Page<T> page(T exam, Pager pager);

}
