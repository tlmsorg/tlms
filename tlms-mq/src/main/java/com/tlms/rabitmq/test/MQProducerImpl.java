package com.tlms.rabitmq.test;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MQProducerImpl implements MQProducer{

	@Autowired
    private AmqpTemplate amqpTemplate;
	@Value("${icbc_mq_apply.exchange}")
	private String exchange;
	@Value("${icbc_mq_apply.routingKey}")
	private String routingKey;

    private final static Logger LOGGER = Logger.getLogger(MQProducerImpl.class);
    /* (non-Javadoc)
     * @see com.stnts.tita.rm.api.mq.MQProducer#sendDataToQueue(java.lang.String, java.lang.Object)
     */
    @Override
    public void sendDataToQueue(String queueKey, Object object) {
        try {
//        	amqpTemplate.convertAndSend(exchange, routingKey, object);
            amqpTemplate.convertAndSend(queueKey, object);
//            amqpTemplate.convertAndSend(object);
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

}
