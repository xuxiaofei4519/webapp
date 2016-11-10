package com.myapp.dao.user;

import com.myapp.entity.USalt;
import com.myapp.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by 徐晓飞 on 2016/10/8.
 */
@Repository
public interface UserDao
{
    public User findByUserName(String userName);
    public void istSalt(USalt salt);
    public void istUser(User User);
    public String getSalt(String uid);
}