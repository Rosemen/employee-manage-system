package cn.edu.scau.employee.common.enums;

import lombok.Getter;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/22 0:17
 */
public enum LeaveTypeEnum {

    SICK_LEAVE("病假", 1),

    COMPASSIONATE_LEAVE("事假", 2);

    @Getter
    private String msg;

    @Getter
    private int code;

    LeaveTypeEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public static LeaveTypeEnum get(int code) {
        LeaveTypeEnum[] values = LeaveTypeEnum.values();
        for (LeaveTypeEnum value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new RuntimeException("不支持状态值: " + code);
    }
}
