package com.deepazure.visualdata.service;

import com.deepazure.visualdata.entity.BaseEntity;
import com.deepazure.visualdata.util.Pager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    public abstract JpaRepository<T, Long> getRepository();

    @Override
    public T save(T item) {
        beforeSave(item);
        item = getRepository().saveAndFlush(item);
        return item;
    }

    @Override
    public List<T> save(List<T> items) {
        beforeSave(items);
        return getRepository().saveAll(items);
    }

    @Override
    public void delete(Long id) {
        beforeDelete(id);
        getRepository().deleteById(id);
    }

    @Override
    public void deleteAll(List<Long> ids) {
        List<T> items = new ArrayList<>();
        for (Long id : ids) {
            BaseEntity entity = new BaseEntity();
            entity.setId(id);
            items.add((T) entity);
        }
        beforeDelete(items);
        getRepository().deleteInBatch(items);
    }

    @Override
    public T findById(Long id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public List<T> findByIds(List<Long> ids) {
        return getRepository().findAllById(ids);
    }

    @Override
    public List<T> findByIds(String ids) {
        String[] strs = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (String str : strs) {
            Long id = Long.parseLong(str);
            idList.add(id);
        }
        return findByIds(idList);
    }

    @Override
    public T getOne(Long id) {
        return getRepository().getOne(id);
    }

    @Override
    public T getOne(T exam) {
        return getRepository().findOne(Example.of(exam)).orElse(null);
    }

    @Override
    public List<T> findAll(T exam) {
        return getRepository().findAll(Example.of(exam));
    }

    @Override
    public Page<T> page(T exam, Pager pager) {
        return getRepository().findAll(Example.of(exam), PageRequest.of(pager.getPage(), pager.getSize()));
    }

    public void beforeSave(T item) {
    }

    public void beforeSave(List<T> items) {
    }

    public void beforeDelete(Long id){}

    public void beforeDelete(List<T> items) {}
}
