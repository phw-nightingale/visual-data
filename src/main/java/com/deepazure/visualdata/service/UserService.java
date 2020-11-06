package com.deepazure.visualdata.service;

import com.deepazure.visualdata.entity.User;
import com.deepazure.visualdata.util.JsonResult;

public interface UserService extends BaseService<User> {

    JsonResult login(String username, String password);

    JsonResult login(String accessToken);

    User findByAccessToken(String accessToken);

    JsonResult logout(String accessToken);
}
