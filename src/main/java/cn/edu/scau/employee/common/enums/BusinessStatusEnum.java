package cn.edu.scau.employee.common.enums;

import cn.edu.scau.common.enums.QuarterEnum;
import lombok.Getter;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/19 23:27
 */
public enum BusinessStatusEnum {

    WAIT_HANDLE("待处理", 0),

    AGREE("同意", 1),

    DISAGREE("不同意", 2);

    BusinessStatusEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    @Getter
    private String msg;

    @Getter
    private int code;

    public static BusinessStatusEnum get(Integer code) {
        BusinessStatusEnum[] values = BusinessStatusEnum.values();
        for (BusinessStatusEnum value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new RuntimeException("不支持状态值: " + code);
    }
}
