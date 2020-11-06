package com.deepazure.visualdata.interceptor;

import com.deepazure.visualdata.entity.User;
import com.deepazure.visualdata.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AccessTokenInterceptor implements HandlerInterceptor {

    private final UserService userService;

    public AccessTokenInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader("access_token");
        // log.info(accessToken);
        if (StringUtils.isEmpty(accessToken)) {
            response.sendRedirect("/401");
            return false;
        }
        User user = userService.findByAccessToken(accessToken);
        // log.info(user == null ? "" : user.toString());
        if (user == null) {
            response.sendRedirect("/403");
            return false;
        }
        request.setAttribute("CurrentUser", user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
