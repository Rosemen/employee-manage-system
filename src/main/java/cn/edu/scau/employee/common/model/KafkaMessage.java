package cn.edu.scau.employee.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chen.jiale
 * @Description kafka消息
 * @date 2020/2/9 13:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaMessage implements Serializable {

    private static final long serialVersionUID = 2611905171145147639L;

    private String from;

    private String data;

    private Date createTime;

    public static KafkaMessage generateMessage(String from, String data) {
        KafkaMessage message = new KafkaMessage();
        message.setFrom(from);
        message.setData(data);
        message.setCreateTime(new Date());
        return message;
    }
}
