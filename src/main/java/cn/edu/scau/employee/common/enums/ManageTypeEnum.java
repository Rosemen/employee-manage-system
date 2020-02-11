package cn.edu.scau.employee.common.enums;

import lombok.Getter;


/**
 * @author chen.jiale
 * @Description 数据库操作类型
 * @date 2020/2/9 12:15
 */
public enum ManageTypeEnum {

    INSERT("插入", 1),

    DELETE("删除", 2),

    UPDATE("修改", 3),

    QUERY("查询", 4);

    @Getter
    private String msg;

    @Getter
    private int code;

    ManageTypeEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public static ManageTypeEnum get(int code) {
        ManageTypeEnum[] values = ManageTypeEnum.values();
        for (ManageTypeEnum manageTypeEnum : values) {
            if (manageTypeEnum.getCode() == code) {
                return manageTypeEnum;
            }
        }
        return null;
    }

}
