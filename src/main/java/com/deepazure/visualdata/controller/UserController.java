package com.deepazure.visualdata.controller;

import com.deepazure.visualdata.entity.User;
import com.deepazure.visualdata.service.UserService;
import com.deepazure.visualdata.util.JsonResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/login")
    public JsonResult login(User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    @GetMapping("/users/info")
    public JsonResult info(@RequestHeader("access_token") String accessToken) {
        return userService.login(accessToken);
    }

    @GetMapping("/users/list")
    public JsonResult list() {
        return JsonResult.success();
    }

    @GetMapping("/users")
    public JsonResult find(User user) {
        return JsonResult.success(userService.findAll(user));
    }

    @PostMapping("/users/logout")
    public JsonResult logout(@RequestHeader("access_token") String accessToken) {
        return userService.logout(accessToken);
    }

    @DeleteMapping("/users/{id}")
    public JsonResult remove(@PathVariable Long id) {
        userService.delete(id);
        return JsonResult.success();
    }

    @PostMapping("/users")
    public JsonResult add(User student) {
        return JsonResult.success(userService.save(student));
    }

    @PutMapping("/users")
    public JsonResult patch(User student) {
        return JsonResult.success(userService.save(student));
    }

}
