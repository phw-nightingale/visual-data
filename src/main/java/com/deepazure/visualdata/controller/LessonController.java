package com.deepazure.visualdata.controller;

import com.deepazure.visualdata.entity.Lesson;
import com.deepazure.visualdata.service.LessonService;
import com.deepazure.visualdata.util.JsonResult;
import com.deepazure.visualdata.util.Pager;
import org.springframework.web.bind.annotation.*;

@RestController
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/lessons/list")
    public JsonResult page(Lesson example, Pager pager) {
        return JsonResult.success(lessonService.page(example, pager));
    }

    @GetMapping("/lessons")
    public JsonResult list(Lesson example) {
        return JsonResult.success(lessonService.findAll(example));
    }

    @PostMapping("/lessons")
    public JsonResult add(Lesson lesson) {
        return JsonResult.success(lessonService.save(lesson));
    }

    @DeleteMapping("/lessons/{id}")
    public JsonResult remove(@PathVariable Long id) {
        lessonService.delete(id);
        return JsonResult.success();
    }

    @PutMapping("/lessons/{id}")
    public JsonResult update(@PathVariable Long id, Lesson lesson) {
        lesson.setId(id);
        return JsonResult.success(lessonService.save(lesson));
    }
}
