package com.deepazure.visualdata.repository;

import com.deepazure.visualdata.entity.Fault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaultRepository extends JpaRepository<Fault, Long> {

    @Query("SELECT DISTINCT fault.componentName FROM Fault fault WHERE fault.componentName LIKE %:name%")
    List<String> findComponentNameLikeDistinct(@Param("name") String name);

    List<String> findDistinctByComponentNameLike(String componentName);

}
