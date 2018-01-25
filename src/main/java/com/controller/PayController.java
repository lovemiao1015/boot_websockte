package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Date;

/**
 * Created by skf
 * Date 2018/1/22.
 */
@Controller
public class PayController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping("/show/{id}")
    public void show(@PathVariable String id,String RespCode){
        System.out.println(id);
        if("00".equals(RespCode)) {
            messagingTemplate.convertAndSend("/topic/pullLogger", id);
        }
    }


    @RequestMapping("/pay/{orderId}")
    public String pay(@PathVariable String orderId, Model model){
        System.out.println(orderId);
        model.addAttribute("orderId",orderId);
        model.addAttribute("time",new Date());
        model.addAttribute("msg","Hello World!");
        return "pay";
    }
    @RequestMapping("/hello")
    public String hello(){

        return "hello";
    }
    @RequestMapping("/login")
    public String login(HttpSession session){
        session.setAttribute("name","zhangsan");
        session.setAttribute("user_id",88);
        return "login";
    }

    @RequestMapping("/test")
    public String hello1(){

        return "test";
    }
    @RequestMapping("/test1")
    public String hello2(){

        return "test1";
    }

    @MessageMapping("msg")
    @SendTo("/topic/one")
    public String pull(String msg){
        //传来String字符串，用户msg接受
        System.out.println(msg);
        return "hello-----"+msg;
    }

    @MessageMapping("msg-user")
    @SendToUser("/topic/two")
    public void pll(String msg,Principal principal ){
        System.out.println(msg);
        messagingTemplate.convertAndSendToUser("123","/topic/two",msg);
    }

    @RequestMapping("/user")
    public String ss(){

        return "user";
    }

}
