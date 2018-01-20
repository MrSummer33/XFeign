package com.xiaxi;

import com.xiaxi.xfeign.api.HelloApi;
import com.xiaxi.xfeign.api.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by BaoCai on 18/1/20.
 * Description:
 */
@RestController
public class HelloController {

    @Autowired
    private HelloApi helloApi;

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("hello/xfeign")
    public String helloXfeign(){
        return helloApi.hello("client",20);
    }

    @GetMapping("hello/user")
    public User helloUser(){
        User user = helloApi.user("client",20);
        return user;
    }

}
