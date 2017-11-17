package com.liuyibo.part.controller;

import com.liuyibo.part.dao.GoodsRepository;
import com.liuyibo.part.entity.Goods;
import com.liuyibo.part.utils.JPushUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JPushController {
    @RequestMapping("/sendMessage")
    public @ResponseBody String  sendMessage(String message){
        JPushUtil.sendMessage(message);
        return "success";
    }
}
