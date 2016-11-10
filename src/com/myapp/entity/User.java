package com.myapp.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by 徐晓飞 on 2016/10/8.
 */
public class User implements Serializable
{
    private String u_id;
    private String u_name;
    private String u_password;
    private String u_birth;
    private Timestamp u_create_time;
    private String u_email;

    public String getU_birth()
    {
        return u_birth;
    }

    public void setU_birth(String u_birth)
    {
        this.u_birth = u_birth;
    }

    public String getU_email()
    {
        return u_email;
    }

    public void setU_email(String u_email)
    {
        this.u_email = u_email;
    }

    public String getU_id()
    {
        return u_id;
    }

    public void setU_id(String u_id)
    {
        this.u_id = u_id;
    }

    public String getU_name()
    {
        return u_name;
    }

    public void setU_name(String u_name)
    {
        this.u_name = u_name;
    }

    public String getU_password()
    {
        return u_password;
    }

    public void setU_password(String u_password)
    {
        this.u_password = u_password;
    }



    public void setU_sex(String u_birth)
    {
        this.u_birth = u_birth;
    }

    public Timestamp getU_create_time()
    {
        return u_create_time;
    }

    public void setU_create_time(Timestamp u_create_time)
    {
        this.u_create_time = u_create_time;
    }
}
