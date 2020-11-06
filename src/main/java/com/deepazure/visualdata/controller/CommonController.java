package com.deepazure.visualdata.controller;

import com.deepazure.visualdata.bo.ServerPack;
import com.deepazure.visualdata.config.WebSocketServer;
import com.deepazure.visualdata.util.JsonResult;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@ControllerAdvice
public class CommonController {

    private final Gson gson;

    public CommonController(Gson gson) {
        this.gson = gson;
    }

    @GetMapping("/")
    public JsonResult index() {
        return JsonResult.success();
    }

    @GetMapping("/401")
    public JsonResult _401() {
        return JsonResult.error(401, "用户认证失败");
    }

    @GetMapping("/403")
    public JsonResult _403() {
        return JsonResult.error(403, "用户禁止访问");
    }

    @GetMapping("/404")
    public JsonResult _404() {
        return JsonResult.error(404, "未知请求");
    }

    @GetMapping("/500")
    public JsonResult _500() {
        return JsonResult.error(500, "服务器错误");
    }

    @PostMapping("/websocket/send/{sid}")
    public JsonResult send(@RequestParam("data") String data, @PathVariable String sid) {
        ServerPack pack = gson.fromJson(data, ServerPack.class);
        WebSocketServer.sendInfo(gson.toJson(pack), sid);
        return JsonResult.success("发送成功");
    }

    @GetMapping("/websocket/online")
    public JsonResult getOnline() {
        WebSocketServer.sendOnlineInfo();
        return JsonResult.success();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public JsonResult exceptionHandler(IllegalArgumentException e) {
        log.error(e.getMessage());
        return JsonResult.error(e.getMessage());
    }
}
