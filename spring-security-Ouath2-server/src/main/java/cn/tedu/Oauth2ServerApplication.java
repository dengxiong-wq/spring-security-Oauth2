package cn.tedu;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.tedu.oauth2.server.mapper")
public class Oauth2ServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class,args);
    }
}
