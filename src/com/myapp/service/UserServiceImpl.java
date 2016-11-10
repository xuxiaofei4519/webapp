package com.myapp.service;

import com.myapp.dao.user.UserDao;
import com.myapp.entity.DataResult;
import com.myapp.entity.USalt;
import com.myapp.entity.User;
import com.myapp.util.EncryptionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;



/**
 * Created by 徐晓飞 on 2016/10/8.
 */
@Service
public class UserServiceImpl implements UserService
{
    @Resource
    private UserDao userDao;

    public DataResult checkLogin(String username, String password) throws NoSuchAlgorithmException
    {
        DataResult result = new DataResult();
        User user = userDao.findByUserName(username);
        if (user == null)
        {
            result.setMsg("你输入的用户名错误");
            result.setStatus(1);
            return result;
        }

        //如果用户名不为null说明用户名输入正确，比对密码，用加密算法
        String have_pwd = user.getU_password();
        String uid = user.getU_id();
        String salt = userDao.getSalt(uid);
        if (!EncryptionUtil.checkPasswd(uid,have_pwd,salt))
        {
            result.setStatus(2);
            result.setMsg("你输入的密码不正确");
            return result;
        }
        result.setStatus(0);
        result.setMsg("用户名密码正确");
        return result;
    }

    @Override
    public DataResult Regist(String uname, String upassword, String ubirth,String uemail)
    {

        DataResult result = new DataResult();
        //检测用户名是否被占用
        User has_user = userDao.findByUserName(uname);
        if (has_user != null){
            result.setStatus(1);
            result.setMsg("用户名已经被占用");
            return result;
        }
        //将password双层加盐加密
        String pwd = EncryptionUtil.toPasswd(upassword);
        String salt = pwd.substring(0,2);
        String md5pwd = pwd.substring(2,26);
        String id = EncryptionUtil.CteateId();

        //获取当前系统时间
        Timestamp creatime = new Timestamp(System.currentTimeMillis());
        User user = new User();
        //为user表创建主键ID
        user.setU_id(id);
        user.setU_name(uname);
        user.setU_password(md5pwd);
        user.setU_birth(ubirth);
        user.setU_email(uemail);
        user.setU_create_time(creatime);
        System.out.println(ubirth);
        userDao.istUser(user);

        //将盐放入盐表
        USalt usalt = new USalt();
        usalt.setU_id(id);
        usalt.setU_salt(salt);
        userDao.istSalt(usalt);

        result.setMsg("注册成功");
        result.setStatus(0);
        return result;
    }

}
