package com.deepazure.visualdata.controller;

import com.deepazure.visualdata.entity.Device;
import com.deepazure.visualdata.service.DeviceService;
import com.deepazure.visualdata.util.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/devices")
    private JsonResult find(Device device) {
        return JsonResult.success(deviceService.findAll(device));
    }

}
