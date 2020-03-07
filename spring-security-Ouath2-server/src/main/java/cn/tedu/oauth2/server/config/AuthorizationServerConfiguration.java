package cn.tedu.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
//拿到用户授权码，和token


//配置类
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Primary //此注解会覆盖默认配置
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    //表示token令牌走数据库
    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource());
    }
    //表示客户端信息走数据库
    @Bean
    public ClientDetailsService jdbcClientDetails(){
        return new JdbcClientDetailsService(dataSource());
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //测试模式，授权码放在内存中
//        clients
//                .inMemory()
//                .withClient("client")   //clientId客户端ID
//                .secret(passwordEncoder.encode("secret"))            //secret安全码
//                .authorizedGrantTypes("authorization_code") //授权模式  简单模式，密码模式，客户端模式，授权码模式
//                .scopes("app")           //授权范围，谁获得权限
//                .redirectUris("http://www.baidu.com");

       //生产环境，客户端信息走数据库拿
        clients.withClientDetails(jdbcClientDetails());
    }

    //token
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.tokenStore(tokenStore());
    }

    //    public static void main(String[] args) {
    //测试拿到secret的BC加密结果
//        System.out.println(new BCryptPasswordEncoder().encode("serect"));
//    }

}
