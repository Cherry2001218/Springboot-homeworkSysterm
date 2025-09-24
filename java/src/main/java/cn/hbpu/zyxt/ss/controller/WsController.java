package cn.hbpu.zyxt.ss.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hbpu.zyxt.ss.entity.Chatmsg;
import cn.hbpu.zyxt.ss.service.ChatmsgService;

@RestController
@RequestMapping("/ws")
public class WsController {
		

	@Autowired
	ChatmsgService chatmsgS;
	@Autowired
	private SimpMessagingTemplate template;
	
	
	@MessageMapping("/chat")
	public void handleMsg(Chatmsg msg)
	{
		msg.setDate(LocalDateTime.now());
		chatmsgS.save(msg);
		
		template.convertAndSendToUser(msg.getToUser(),"/queue/chat",msg);
	}
}
