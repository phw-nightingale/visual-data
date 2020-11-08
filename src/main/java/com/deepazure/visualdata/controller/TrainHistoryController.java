package com.deepazure.visualdata.controller;

import com.deepazure.visualdata.entity.TrainHistory;
import com.deepazure.visualdata.service.TrainHistoryService;
import com.deepazure.visualdata.util.JsonResult;
import com.deepazure.visualdata.util.Pager;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrainHistoryController {

    private final TrainHistoryService trainHistoryService;

    public TrainHistoryController(TrainHistoryService trainHistoryService) {
        this.trainHistoryService = trainHistoryService;
    }

    @GetMapping("/train-histories/page")
    public JsonResult page(TrainHistory example, Pager pager) {
        return JsonResult.success(trainHistoryService.page(example, pager));
    }

    @GetMapping("/train-histories")
    public JsonResult list(TrainHistory example) {
        return JsonResult.success(trainHistoryService.findAll(example));
    }

    @GetMapping("/train-history")
    public JsonResult one(TrainHistory example) {
        return JsonResult.success(trainHistoryService.getOne(example));
    }

    @GetMapping("/train-histories/{id}")
    public JsonResult id(@PathVariable Long id) {
        return JsonResult.success(trainHistoryService.getOne(id));
    }

    @PostMapping("/train-histories")
    @PutMapping("/train-histories")
    public JsonResult save(TrainHistory newHistory) {
        return JsonResult.success(trainHistoryService.save(newHistory));
    }

    @DeleteMapping("/train-histories/{id}")
    public JsonResult remove(@PathVariable Long id) {
        trainHistoryService.delete(id);
        return JsonResult.success();
    }

}
