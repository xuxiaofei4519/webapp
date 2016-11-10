package com.myapp.util;

import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Random;
import java.util.UUID;


/**
 * Created by 徐晓飞 on 2016/10/8.
 */
public class EncryptionUtil
{
    private static MessageDigest md;
    private static BASE64Encoder b64Encoder;

    static {
        try
        {
            md = MessageDigest.getInstance("MD5","SUN");
            b64Encoder = new BASE64Encoder();
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        } catch (NoSuchProviderException e)
        {
            e.printStackTrace();
        }

    }
    //往数据库添加主键id的方法
    public static String CteateId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    public static boolean checkPasswd(String inputPasswd,String storePasswd,String salt)
    {
        boolean ok = false;

        try
        {
            byte[] saltBys = salt.getBytes("utf-8");
            String inPwd = toPasswd(inputPasswd,saltBys);
            String pwd = inPwd.substring(2,26);
            ok = pwd.equals(storePasswd);
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            ok = false;
        }

        return ok;
    }

    public static String toPasswd(String inputpassword){
        byte[] salt = getSaltOfASCII(2);
        return toPasswd(inputpassword,salt);

    }

    //双层加盐加密
    public static String toPasswd(String inputpassword,byte[] salt){
        String pwd = "";

        try
        {
            //将输入的密码先加密
            md.reset();
            md.update(inputpassword.getBytes("utf-8"));
            byte[] bys = md.digest();
            String md_inputpwd = b64Encoder.encode(bys);
            System.out.println(md_inputpwd);

            //加盐之后再次加密
            md.reset();
            md.update(salt);
            md.update(md_inputpwd.getBytes("utf-8"));
            byte[] bys1 = md.digest();
            //这里pwd的结构是盐的字符串+双层加盐加密后的字符串。
            // 相当于pwd有盐的本来的字符，也有盐和加密后的密码的字符，
            //方便后期将pwd字符串切割，将盐放入数据库
            pwd += (char)salt[0];
            pwd += (char)salt[1];
            pwd += b64Encoder.encode(bys1);
//            System.out.println(pwd.substring(0,2));

        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            pwd = "";
        }
        return pwd;


    }

    public static byte[] getSaltOfASCII(int len){
        byte[] salt = new  byte[len];
        Random random = new Random();
        for (int i = 0; i < len ; i++)
        {
            salt[i] = (byte) ((random.nextInt('~'-'!')+'!') & 0x007f);
        }
        return salt;
    }

    @Test
    public void t1() throws UnsupportedEncodingException
    {
        String pwd = "123456";
        String salt = "F4";
        String pwd1 = null;
        for (int i = 0; i < 5 ; i++)
        {
            byte[] s = salt.getBytes("Utf-8");
            String p = EncryptionUtil.toPasswd(pwd,s);
            System.out.println(p);


        }
    }

}
