package cn.edu.scau.employee.common.util;

import cn.edu.scau.common.util.JsonUtil;
import cn.edu.scau.employee.common.model.KafkaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


/**
 * @author chen.jiale
 * @Description 消息工具
 * @date 2020/2/10 21:17
 */
@Component
public class MessageUtil {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    public void sendMessage(String from, Object data, String topic) {
        KafkaMessage kafkaMessage = KafkaMessage.generateMessage(from, JsonUtil.objectToJson(data));
        String message = JsonUtil.objectToJson(kafkaMessage);
        logger.info("[发送消息] 消息来源:{} 目标队列:{} 消息内容:{}", from, topic, message);
        kafkaTemplate.send(topic, message);
        logger.info("[消息发送完成]");
    }
}
