package com.deepazure.visualdata.repository;

import com.deepazure.visualdata.entity.TrainHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainHistoryRepository extends JpaRepository<TrainHistory, Long> {

    Page<TrainHistory> findByStudentNameLike(String stuName, Pageable pageable);

    @Query("SELECT his.score FROM TrainHistory his WHERE his.userId = :userId")
    List<Integer> findScoresByUserId(@Param("userId") Long userId);
}
