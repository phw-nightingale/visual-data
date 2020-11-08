package com.deepazure.visualdata.service;

import com.deepazure.visualdata.config.WebSocketServer;
import com.deepazure.visualdata.entity.Lesson;
import com.deepazure.visualdata.entity.TrainHistory;
import com.deepazure.visualdata.entity.User;
import com.deepazure.visualdata.repository.LessonRepository;
import com.deepazure.visualdata.repository.TrainHistoryRepository;
import com.deepazure.visualdata.repository.UserRepository;
import com.deepazure.visualdata.util.Message;
import com.deepazure.visualdata.util.Observer;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class TrainHistoryServiceImpl extends BaseServiceImpl<TrainHistory> implements TrainHistoryService, Observer {

    private final TrainHistoryRepository trainHistoryRepository;

    private final UserRepository userRepository;

    private final LessonRepository lessonRepository;

    private final WebSocketServer server;

    private final Gson gson;

    public TrainHistoryServiceImpl(TrainHistoryRepository trainHistoryRepository, UserRepository userRepository, LessonRepository lessonRepository, WebSocketServer server, Gson gson) {
        this.trainHistoryRepository = trainHistoryRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.server = server;
        this.gson = gson;
        this.onTrainHistoryMessage();
    }

    @Override
    public JpaRepository<TrainHistory, Long> getRepository() {
        return trainHistoryRepository;
    }


    @Override
    public void beforeSave(TrainHistory item) {
        if (StringUtils.isEmpty(item.getUserId())) {
            throw new IllegalArgumentException("user_id: 数据不完整");
        }
        if (StringUtils.isEmpty(item.getCourseDefinedFaultIds())) {
            throw new IllegalArgumentException("course_def_faults_ids: 数据不完整");
        }
        if (item.getCourseId() == null) {
            throw new IllegalArgumentException("course_id: 数据不完整");
        }
        if (item.getUsedTime() == null) {
            throw new IllegalArgumentException("used_time: 数据不完整");
        }

        User stu = userRepository.findById(item.getUserId()).orElse(null);
        if (stu == null) {
            throw new IllegalArgumentException("user_id: 找不到此用户");
        }
        item.setStudentName(stu.getUsername());
        Lesson lesson = lessonRepository.findById(item.getCourseId()).orElse(null);
        if (lesson == null) {
            throw new IllegalArgumentException("course_id: 找不到此课程");
        }
        item.setCourseTitle(lesson.getTitle());
    }

    private void onTrainHistoryMessage() {
        server.register(this);
    }

    @Override
    public void update(Message msg) {
    }

    @Override
    public void update(String msg) {
        log.info(msg);
        try {
            var history = gson.fromJson(msg, TrainHistory.class);
            log.info(history.toString());
            if (history.getCourseId() != null) {
                trainHistoryRepository.save(history);
            }
        } catch (JsonSyntaxException e) {
            log.warn(e.getMessage());
        }
    }
}
