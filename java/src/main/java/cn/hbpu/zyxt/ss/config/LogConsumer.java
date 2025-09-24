package cn.hbpu.zyxt.ss.config;

import java.time.LocalDateTime;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hbpu.zyxt.ss.entity.Log;
import cn.hbpu.zyxt.ss.entity.User;
import cn.hbpu.zyxt.ss.service.LogService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
@RocketMQMessageListener(topic = "login-log",consumerGroup = "news-consumer-1")
public class LogConsumer implements RocketMQListener<User> {

	@Autowired
	LogService logS;
	@Override
	public void onMessage(User u) {
		// Tc'nODO Auto-generated method stub
		log.info("Receive message: "+u);
		Log log = new Log();
		log.setOperation("登录");
		log.setUid(u.getUid());
		log.setTime(LocalDateTime.now());
		logS.save(log);
	}

}
