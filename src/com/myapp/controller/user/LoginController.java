package com.myapp.controller.user;

import com.myapp.entity.DataResult;
import com.myapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 徐晓飞 on 2016/10/20.
 */
@Controller
@RequestMapping("/user")
public class LoginController
{
    @Resource
    private UserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
    public DataResult execute(String username, String password) throws NoSuchAlgorithmException
    {

        DataResult result = userService.checkLogin(username, password);
        return result;
    }
}
