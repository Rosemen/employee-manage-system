package cn.edu.scau.employee.common.enums;

import lombok.Getter;

/**
 * @author chen.jiale
 * @Description
 * @date 2020/2/19 23:27
 */
public enum LeaveStatusEnum {

    WAIT_HANDLE("待处理", 0),

    AGREE("同意", 1),

    DISAGREE("不同意", 2);

    LeaveStatusEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    @Getter
    private String msg;

    @Getter
    private int code;

    public static LeaveStatusEnum get(int code) {
        LeaveStatusEnum[] values = LeaveStatusEnum.values();
        for (LeaveStatusEnum value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new RuntimeException("不支持状态值: " + code);
    }
}
