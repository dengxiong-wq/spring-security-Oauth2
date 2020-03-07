package cn.tedu.oauth2.server.service;

import cn.tedu.oauth2.server.domain.TbUser;

public interface TbUserService{
    //Oauth2系统时先通过查询用户名存在与否，再验证密码，防止sql注入
    TbUser getByUserName(String username);

}
