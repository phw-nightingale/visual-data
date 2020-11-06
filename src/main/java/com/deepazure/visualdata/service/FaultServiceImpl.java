package com.deepazure.visualdata.service;

import com.deepazure.visualdata.entity.Fault;
import com.deepazure.visualdata.repository.FaultRepository;
import com.deepazure.visualdata.util.JsonResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaultServiceImpl extends BaseServiceImpl<Fault> implements FaultService {

    private final FaultRepository faultRepository;

    public FaultServiceImpl(FaultRepository faultRepository) {
        this.faultRepository = faultRepository;
    }

    @Override
    public JpaRepository<Fault, Long> getRepository() {
        return faultRepository;
    }

    @Override
    public List<String> findDistinctComponentNamesLike(String name) {
        return faultRepository.findComponentNameLikeDistinct(name);
    }
}
