package com.upphoto.controller;

import com.upphoto.common.Const;
import com.upphoto.common.ServerResponse;
import com.upphoto.entity.Custom;
import com.upphoto.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class CustomController {

    private final CustomService customService;

    @Autowired
    public CustomController(CustomService customService) {
        this.customService = customService;
    }

    @RequestMapping(value = "/customLogin", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse customLogin(Custom custom, HttpServletRequest request) {
        Custom backCustom = customService.customLogin(custom);
        // 数据库查询结果
        if (backCustom != null) {
            HttpSession session = request.getSession();
            session.setAttribute(Const.CURRENT_USER, custom.getUsername());
            ServerResponse response = ServerResponse.createBySuccessMessage("SUCCESS");
            return response;
        } else {
            ServerResponse response = ServerResponse.createByErrorCodeMessage(401, "EMPTY");
            return response;
        }
    }
}
