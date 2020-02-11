package cn.edu.scau.employee.config.kafka;

import cn.edu.scau.common.util.JsonUtil;
import cn.edu.scau.common.util.ObjectUtil;
import cn.edu.scau.employee.common.model.KafkaMessage;
import cn.edu.scau.employee.dao.ManageRecordDao;
import cn.edu.scau.employee.entity.ManageRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author chen.jiale
 * @Description kafka消息消费者
 * @date 2020/2/9 13:46
 */
@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private ManageRecordDao manageRecordDao;

    @KafkaListener(topics = {"employee_manage_record"})
    public void consume(ConsumerRecord<?, ?> record, Acknowledgment ack) {
        if (!ObjectUtil.isEmpty(record.value())) {
            KafkaMessage message = JsonUtil.jsonToObject((String) record.value(), KafkaMessage.class);
            logger.info("[消费者消费] 消息内容: {}", message);
            String data = message.getData();
            if (!StringUtils.isEmpty(data)) {
                manageRecordDao.add(JsonUtil.jsonToObject(data, ManageRecord.class));
            }
        }
        ack.acknowledge();
    }

}
