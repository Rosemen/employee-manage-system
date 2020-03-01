package cn.edu.scau.employee.config.ftp;

import cn.edu.scau.common.util.FtpUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chen.jiale
 * @Description ftp配置类
 * @date 2020/2/29 20:18
 */
@Configuration
public class FtpConfig {

    @ConfigurationProperties(prefix = "ftp")
    @Bean
    public FtpUtil ftpUtil() {
        return new FtpUtil();
    }
}
