package cn.tedu.oauth2.server.service.impl;

import cn.tedu.oauth2.server.domain.TbUser;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tedu.oauth2.server.mapper.TbUserMapper;
import cn.tedu.oauth2.server.service.TbUserService;
import tk.mybatis.mapper.entity.Example;

@Service
public class TbUserServiceImpl implements TbUserService{

    @Resource
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser getByUserName(String username) {
        Example example = new Example(TbUser.class);
        //相当于sql语句中的查询条件  where
        //createCriteria 将查询条件通过Java对象进行模块化封装
        example.createCriteria().andEqualTo("username",username);
        return tbUserMapper.selectOneByExample(example);
    }
}
