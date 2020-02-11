package cn.edu.scau.employee.entity;

import lombok.Data;

import java.util.Date;

/**
 *
 * @author chen.jiale
 * @description 操作记录
 * @date 2019/12/22
 */
@Data
public class ManageRecord {

    private Long id;

    private String username;

    private String manageType;

    private String manageTable;

    private Date manageDate;

    private String serviceName;

    private String methodName;

    public static ManageRecord generate(String username, String manageType, String manageTable,
                                        String serviceName, String methodName){
        ManageRecord record = new ManageRecord();
        record.setUsername(username);
        record.setManageType(manageType);
        record.setManageTable(manageTable);
        record.setServiceName(serviceName);
        record.setMethodName(methodName);
        record.setManageDate(new Date());
        return record;
    }

}