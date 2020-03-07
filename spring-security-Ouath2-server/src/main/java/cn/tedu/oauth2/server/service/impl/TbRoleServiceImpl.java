package cn.tedu.oauth2.server.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tedu.oauth2.server.mapper.TbRoleMapper;
import cn.tedu.oauth2.server.service.TbRoleService;
@Service
public class TbRoleServiceImpl implements TbRoleService{

    @Resource
    private TbRoleMapper tbRoleMapper;

}
