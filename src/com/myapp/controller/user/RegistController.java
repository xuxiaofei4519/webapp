package com.myapp.controller.user;

import com.myapp.entity.DataResult;
import com.myapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by 徐晓飞 on 2016/10/20.
 */
@Controller
@RequestMapping("/user")
public class RegistController
{
    @Resource
    private UserService userService;

    @RequestMapping("/regist.do")
    @ResponseBody
    public DataResult execute(String uname, String upassword, String ubirth,String uemail ){
        DataResult result = userService.Regist(uname,upassword,ubirth,uemail);
        return result;
    }
}
