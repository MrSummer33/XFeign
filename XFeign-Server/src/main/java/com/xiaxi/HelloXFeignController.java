package com.xiaxi;

import com.xiaxi.xfeign.api.HelloApi;
import com.xiaxi.xfeign.api.User;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by BaoCai on 18/1/20.
 * Description:
 */
@RestController
public class HelloXFeignController implements HelloApi {

    @Override
    public String hello(String name, int age) {
        return "hello client , i am server : " + name + " : " + age;
    }

    @Override
    public User user(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age) {
        return new User(name,age);
    }
}
