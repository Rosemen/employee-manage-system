package cn.edu.scau.employee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author chen.jiale
 * @Description 主启动类
 * @date 2019/12/21 20:02
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.edu.scau.employee.mapper")
@EnableSwagger2
@EnableScheduling
public class EmployeeApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }
}
