package com.deepazure.visualdata.service;

import com.deepazure.visualdata.entity.Lesson;
import com.deepazure.visualdata.repository.LessonRepository;
import com.deepazure.visualdata.util.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl extends BaseServiceImpl<Lesson> implements LessonService {

    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public JpaRepository<Lesson, Long> getRepository() {
        return lessonRepository;
    }

    @Override
    public void update(Message msg) {

    }

    @Override
    public void update(String msg) {

    }
}
