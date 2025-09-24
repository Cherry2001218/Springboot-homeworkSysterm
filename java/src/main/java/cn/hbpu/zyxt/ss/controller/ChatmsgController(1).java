package cn.hbpu.zyxt.ss.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hbpu.zyxt.ss.entity.Chatmsg;
import cn.hbpu.zyxt.ss.service.ChatmsgService;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hbpu
 * @since 2022-05-10
 */
@RestController
@RequestMapping("/ss/chatmsg")
public class ChatmsgController {
	@Autowired
	ChatmsgService chatmsgS;
	
	@GetMapping("/list")
	IPage<Chatmsg> list(Page<Chatmsg> page,Chatmsg msg)
	{
		
		QueryWrapper<Chatmsg> wrapper= new QueryWrapper<Chatmsg>();
		wrapper.eq("fromUser",msg.getFromUser()).eq("toUser",msg.getToUser()).or().eq("fromUser",msg.getToUser()).eq("toUser",msg.getFromUser());
		
		
		return chatmsgS.page(page,wrapper);
		
	}
	
	
}

