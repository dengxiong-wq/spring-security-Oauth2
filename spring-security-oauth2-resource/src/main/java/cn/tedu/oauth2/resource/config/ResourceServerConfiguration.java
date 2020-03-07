package cn.tedu.oauth2.resource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//授权地址


//资源服务器
@Configuration
@EnableResourceServer
//全局方法拦截器
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //资源权限设置
        http.authorizeRequests()
                .antMatchers("/").hasAnyAuthority("SystemContent")
                .antMatchers("/view/**").hasAnyAuthority("SystemContenView");

    }
}
