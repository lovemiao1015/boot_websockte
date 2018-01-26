package com.controller;

import com.vo.EncoderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by skf
 * Date 2018/1/22.
 */
@Controller
public class PayController {

    private static Logger logger = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping("/show/{id}")
    public void show(@PathVariable String id,String RespCode){
        System.out.println(id);
        if("00".equals(RespCode)) {
            messagingTemplate.convertAndSend("/topic/pullLogger", id);
        }
    }
    @RequestMapping("/show/show/{id}")
    public String  showshow(@PathVariable String id,String RespCode,Model model ){
        model.addAttribute("id",id);
        if("00".equals(RespCode)) {
            return "detail";
        }
        return "detail1";
    }


    @RequestMapping("/pay/{orderId}")
    public void pay(@PathVariable String orderId, Model model, HttpServletResponse response) throws IOException {
        System.out.println(orderId);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddsshhmm");//20180101153625
        String orderNum = format.format(new Date());
        String transTime = format.format(new Date());
        String signature = "acqID=99020344&backURL=http://106.14.248.249:8080/show/show/"+orderId+"&charSet=UTF-8&frontURL=http://ecomerce.allpayx.com/show/show/show/"+orderId+"&goodsInfo=thanks&merID=000000000000015&merReserve=test&orderAmount=0.01&orderCurrency=USD&orderNum="+orderNum+"&paymentSchema=WX&signType=sha256&transTime="+transTime+"&transType=PURC&version=VER0000000022f2c77e3718c47cfb47a89a6fbc9d361";
        signature = EncoderUtil.Encrypt(signature,"SHA-256");
        String str = "https://mchapi.allpayx.com/testjspay?acqID=99020344&backURL=http://106.14.248.249:8080/show/show/"+orderId+"&charSet=UTF-8&frontURL=http://ecomerce.allpayx.com/show/show/show/"+orderId+"&goodsInfo=thanks&merID=000000000000015&merReserve=test&orderAmount=0.01&orderCurrency=USD&signature="+signature+"&orderNum="+orderNum+"&paymentSchema=WX&signType=sha256&transTime="+transTime+"&transType=PURC&version=VER000000002";
        response.sendRedirect(str);
        logger.info("重定向了！！！！");
//        return "pay";
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
