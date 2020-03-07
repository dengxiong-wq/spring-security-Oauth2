package cn.tedu.oauth2.resource.mapper;

import cn.tedu.oauth2.resource.domain.TbContent;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.MyMapper;

@Mapper
public interface TbContentMapper extends MyMapper<TbContent> {
}