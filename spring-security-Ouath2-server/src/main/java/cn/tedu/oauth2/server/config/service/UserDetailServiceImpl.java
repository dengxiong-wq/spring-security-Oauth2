package cn.tedu.oauth2.server.config.service;

import cn.tedu.oauth2.server.domain.TbPermission;
import cn.tedu.oauth2.server.domain.TbUser;
import cn.tedu.oauth2.server.service.TbPermissionService;
import cn.tedu.oauth2.server.service.TbUserService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

//自定义用户信息   返回用户名  密码  权限
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private TbUserService tbUserService;

    @Autowired
    private TbPermissionService tbPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //先查用户存在不
        TbUser tbUser = tbUserService.getByUserName(username);
        //创建一个表存储该用户权限
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        if(tbUser!=null){
            //这一步拿到用户的权限,这是给系统看的权限，所以数据库中设置有一个英文和一个中文名称
            List<TbPermission> tbPermissions = tbPermissionService.selectByUserId(tbUser.getId());
            for(TbPermission tP:tbPermissions){
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(tP.getEnname());
                grantedAuthorities.add(grantedAuthority);
            }
        }
        //认证，授权
        return new User(tbUser.getUsername(),tbUser.getPassword(),grantedAuthorities);
    }
}
