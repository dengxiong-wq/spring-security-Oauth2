package cn.tedu.oauth2.server.mapper;

import cn.tedu.oauth2.server.domain.TbRole;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.MyMapper;

@Mapper
public interface TbRoleMapper extends MyMapper<TbRole> {
}