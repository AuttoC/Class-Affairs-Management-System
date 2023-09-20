package com.ks.service;
import com.ks.bean.User;
import com.ks.mapper.UserMapper;
import com.ks.util.*;
import org.apache.ibatis.session.SqlSession;

/**
 *
 */
public class LoginService {
    public User login(String uname, String upwd) {
        User user=new User();
        if(StringUtil.isEmpty(uname)){
            user.setuId(-1);
            return user;
        }

        SqlSession session=GetSqlSession.createsqlsession();
        UserMapper loginMapper=session.getMapper(UserMapper.class);
        user=loginMapper.queryUserByName(uname);

        if(user==null){
            User u=new User();
            u.setuId(-1);
            return u;
        }
        if(!user.getuPwd().equals(upwd)){
            System.out.println("p:"+user.getuPwd()+"  "+upwd);
            user.setuId(0);
            return user;
        }


        return  user;
    }
}