package com.deepazure.visualdata.controller;

import com.deepazure.visualdata.entity.Fault;
import com.deepazure.visualdata.service.FaultService;
import com.deepazure.visualdata.util.JsonResult;
import com.deepazure.visualdata.util.Pager;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaultController {

    private final FaultService faultService;

    public FaultController(FaultService faultService) {
        this.faultService = faultService;
    }

    @GetMapping("/faults/list")
    public JsonResult page(Fault example, Pager pager) {
        Page<Fault> page = faultService.page(example, pager);
        return JsonResult.success(page);
    }

    @GetMapping("/faults/ids/in")
    public JsonResult findById(@RequestParam String ids) {
        return JsonResult.success(faultService.findByIds(ids));
    }

    @GetMapping("/faults")
    public JsonResult list(Fault example) {
        return JsonResult.success(faultService.findAll(example));
    }

    @GetMapping("/faults/component-name/{name}")
    public JsonResult componentNamesLike(@PathVariable String name) {
        return JsonResult.success(faultService.findDistinctComponentNamesLike(name));
    }

    @GetMapping("/faults/component-names")
    public JsonResult componentNames() {
        return JsonResult.success(faultService.findDistinctComponentNamesLike(""));
    }

}
