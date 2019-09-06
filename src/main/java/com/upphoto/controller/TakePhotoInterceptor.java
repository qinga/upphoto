package com.upphoto.controller;

import com.upphoto.common.Const;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class TakePhotoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute(Const.CURRENT_USER);
        if (name != null) {
            return true;
        } else {
            PrintWriter writer = response.getWriter();
            writer.write("{\"status\":10,\"msg\":\"NOLOGIN\"}");
            writer.flush();
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
