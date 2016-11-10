package com.myapp.entity;

import java.io.Serializable;

/**
 * Created by 徐晓飞 on 2016/10/8.
 */
public class DataResult implements Serializable
{
    //规定数据返回的格式
    private int status;
    private String msg;
    private Object data;

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }
}


