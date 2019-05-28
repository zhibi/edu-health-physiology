package edu.health;


import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 执笔
 * @date 2019/5/22 14:17
 */
@SpringBootApplication
@MapperScan(basePackages = "edu.health.elderly.mapper")
@ComponentScan({"zhibi", "edu"})
public class HealthElderlyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(HealthElderlyApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
