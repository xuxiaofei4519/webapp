package com.myapp.service;

import com.myapp.entity.DataResult;

import java.security.NoSuchAlgorithmException;

/**
 * Created by 徐晓飞 on 2016/10/8.
 */
public interface UserService
{
    public DataResult checkLogin(String userId,String password) throws NoSuchAlgorithmException;
    public DataResult Regist(String uname,String upassword,String ubirth,String uemail);
}
