package cn.edu.scau.employee.config.shiro;

import lombok.Data;

/**
 *
 * @author chen.jiale
 * @description shiro url配置项
 * @date 2019/11/14 19:32
 */
@Data
public class ShiroProperties {
    private String loginUrl;

    private String logoutUrl;

    private String indexUrl;

    private String allUrl;

    private String webjarsUrl;

    private String swaggerUiUrl;

    private String swaggerResourceUrl;

    private String apiDocsUrl;

    private String authcToken;

    private String anon;

    private String permission;

}
